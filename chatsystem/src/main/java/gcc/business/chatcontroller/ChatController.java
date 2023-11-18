package gcc.business.chatcontroller;

import gcc.business.message.Message;
import gcc.business.user.User;
import gcc.business.user.UserList;
import gcc.dataobject.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import static gcc.business.user.UserList.*;

@Controller
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public") //返回值会被发送到该频道
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

        //把信息存入chatMessage集合
        Message.contentList.add(chatMessage);

        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public") //返回值会被发送到该频道
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) { //添加用户进入
        //默认会员
        User user = new User(chatMessage.getSender(), true);
        //添加用户
        userList.add(user);

        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        return chatMessage;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(username);
            chatMessage.setContent("离开了聊天室");
            //去除userList集合中离开聊天室的角色
            if (UserList.getUsernameList().contains(username)){
                userList.remove(UserList.getUserByUsername(username));
            }
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}

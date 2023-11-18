package gcc.business.htmlcontroller;

import gcc.business.message.Message;
import gcc.business.user.User;
import gcc.dataobject.ChatMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HtmlController {



    @RequestMapping("/room")
    public String gotoRoom(){
        return "room";
    }

    @RequestMapping("/initMessage")
    public @ResponseBody List<ChatMessage> initMessage() {

        return Message.getChatMessageList();
    }

    @RequestMapping("/checkRepeatedName")
    public @ResponseBody String checkRepeatedName(@RequestParam String username) {

        if (User.getUserList().contains(username))
            return "false";

        return "success";
    }
}
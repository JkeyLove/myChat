package gcc.business.message;

import gcc.dataobject.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private static String content;

    public static List<ChatMessage> contentList = new ArrayList<>();

    public static List<ChatMessage> getChatMessageList(){
        return contentList;
    }

}
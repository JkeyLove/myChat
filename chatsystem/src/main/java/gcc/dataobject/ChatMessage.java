package gcc.dataobject;

public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        if(content == null){
            content = "";
        }
        return content;
    }

    public void setContent(String content) {
        if(content == null){
            content = "";
        }
        this.content = content;
    }
}

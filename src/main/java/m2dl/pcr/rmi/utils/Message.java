package m2dl.pcr.rmi.utils;

import java.io.Serializable;

public class Message implements Serializable {

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    private String sender;
    private String content;

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "<" + sender + "> " + content;
    }
}

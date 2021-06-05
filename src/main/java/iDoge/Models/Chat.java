package iDoge.Models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Chat {
    private static Logger log = LogManager.getLogger(Chat.class.getName());

    private String user1;
    private String user2;
    private ArrayList<Bark_Comment> sentMessages;
    private ArrayList<Bark_Comment> recievedMessages;
    private Integer unseenMessages;

    public Chat() {
    }

    public Chat(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.sentMessages = new ArrayList<>();
        this.recievedMessages = new ArrayList<>();
        this.unseenMessages = 0;
        log.info("Directory creation " + new File("Database/Chats/" + user1).mkdirs());
    }

    public String getUser1() {
        return user1;
    }

    public String getUser2() {
        return user2;
    }

    public ArrayList<Bark_Comment> getSentMessages() {
        return sentMessages;
    }

    public ArrayList<Bark_Comment> getRecievedMessages() {
        return recievedMessages;
    }

    public int getUnseenMessages() {
        return unseenMessages;
    }

    public void setUnseenMessages(Integer unseenMessages) {
        this.unseenMessages = unseenMessages;
    }

    public void saveChat() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("Database/Chats/" + this.user1 + "/" + this.user2+ ".json"), this);
        log.info("Chat saved");
    }

    public static Chat loadChat(String username1, String username2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("File opened");
        log.info("Chat loaded");
        return objectMapper.readValue(new File("Database/Chats/" + username1 + "/" + username2 + ".json"), Chat.class);
    }
}

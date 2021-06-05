package iDoge.Models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class User {
    private static Logger log = LogManager.getLogger(User.class.getName());
    private String username;
    private boolean deleted;

    public User() {
    }

    public User(String username) {
        this.username = username;
        this.deleted = false;
    }

    public String getUsername() {
        return username;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void saveUser() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("Database/Users/" + this.getUsername() + ".json"), this);
        log.info("User saved");
    }

    public static User loadUser(String username) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("User loaded");
        log.info("File opened");
        return objectMapper.readValue(new File("Database/Users/" + username + ".json"), User.class);
    }

    public static boolean listContainsUser(ArrayList<User> list, User user){
        for (User user2: list) if(user.getUsername().equals(user2.getUsername())) return true;
        return false;
    }
}

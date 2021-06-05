package iDoge.Models;

import com.fasterxml.jackson.databind.ObjectMapper;
import iDoge.Logic.Other.ConsoleColors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Profile {
    private static Logger log = LogManager.getLogger(Profile.class.getName());
    private User user;
    private String name;
    private String password;
    private String birthday;
    private String email;
    private String phone;
    private String bio;
    private String ID;
    private String lastseen;
    private boolean activated;
    private boolean isOnline;
    private boolean isPublic;
    private String lastSeen$Online; //Everyone/No one/Followings
    private ArrayList<Bark_Comment> barksList;
    private ArrayList<User> followersList;
    private ArrayList<User> followingsList;
    private ArrayList<User> blackList;
    private ArrayList<User> muteList;
    private ArrayList<Follow_Request> sentFollowRequests;
    private ArrayList<Follow_Request> recievedFollowRequests;
    private ArrayList<String> notifications;
    private ArrayList<Bark_Comment> savedMessages;
    private HashSet<User> spamList;
    private HashMap<String, ArrayList<User>> packs;

    public Profile() {
    }

    public Profile(User user, String name, String password, String email) {
        this.user = user;
        this.name = name;
        this.password = password;
        this.email = email;
        this.ID = user.getUsername()+email+name;
        this.isOnline = true;
        this.activated = true;
        this.isPublic = true;
        this.lastSeen$Online = "No one";
        this.lastseen = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.barksList = new ArrayList<>();
        this.followersList = new ArrayList<>();
        this.followingsList = new ArrayList<>();
        this.blackList = new ArrayList<>();
        this.muteList = new ArrayList<>();
        this.sentFollowRequests = new ArrayList<>();
        this.recievedFollowRequests = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.savedMessages = new ArrayList<>();
        this.spamList = new HashSet<>();
        this.packs = new HashMap<>();
    }

    public void resetProfile() {
        this.name = "";
        this.password = "";
        this.email = "";
        this.ID = user.getUsername()+email+name;
        this.isOnline = false;
        this.activated = true;
        this.isPublic = true;
        this.lastSeen$Online = "No one";
        this.lastseen = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.followersList = new ArrayList<>();
        this.followingsList = new ArrayList<>();
        this.blackList = new ArrayList<>();
        this.muteList = new ArrayList<>();
        this.sentFollowRequests = new ArrayList<>();
        this.recievedFollowRequests = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.savedMessages = new ArrayList<>();
        this.spamList = new HashSet<>();
        this.packs = new HashMap<>();
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLastSeen$Online() {
        return lastSeen$Online;
    }

    public void setLastSeen$Online(String lastSeen$Online) {
        this.lastSeen$Online = lastSeen$Online;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLastseen() {
        return lastseen;
    }

    public void setLastseen(String lastseen) {
        this.lastseen = lastseen;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Bark_Comment> getBarksList() {
        return barksList;
    }

    public ArrayList<User> getFollowersList() {
        return followersList;
    }

    public ArrayList<User> getFollowingsList() {
        return followingsList;
    }

    public ArrayList<User> getBlackList() {
        return blackList;
    }

    public ArrayList<User> getMuteList() {
        return muteList;
    }

    public ArrayList<Follow_Request> getSentFollowRequests() {
        return sentFollowRequests;
    }

    public ArrayList<Follow_Request> getRecievedFollowRequests() {
        return recievedFollowRequests;
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public ArrayList<Bark_Comment> getSavedMessages() {
        return savedMessages;
    }

    public void saveProfile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("Database/Profiles/" + this.user.getUsername() + ".json"), this);
        log.info("Profile saved");
    }

    public HashSet<User> getSpamList() {
        return spamList;
    }

    public static Profile loadProfile(String username) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("File opened");
        log.info("Profile loaded");
        return objectMapper.readValue(new File("Database/Profiles/" + username + ".json"), Profile.class);
    }

    public HashMap<String, ArrayList<User>> getPacks() {
        return packs;
    }

    public void showInfo(){
        System.out.println(ConsoleColors.PURPLE_BOLD + "Your info:");
        log.info("info loaded");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "Name: " + this.getName());
        System.out.println(ConsoleColors.BLUE_BOLD + "Username: " + this.user.getUsername());
        System.out.println(ConsoleColors.BLUE_BOLD + "Password: " + this.getPassword());
        System.out.println(ConsoleColors.BLUE_BOLD + "Birthday: " + this.getBirthday());
        System.out.println(ConsoleColors.BLUE_BOLD + "Email: " + this.getEmail());
        System.out.println(ConsoleColors.BLUE_BOLD + "Phone number: " + this.getPhone());
        System.out.println(ConsoleColors.BLUE_BOLD + "Bio: " + this.getBio());
        if (this.isActivated()) System.out.println(ConsoleColors.BLUE_BOLD + "Activation: " + "Activated");
        else System.out.println(ConsoleColors.BLUE_BOLD + "Activation: " + "Disabled");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
    }


}

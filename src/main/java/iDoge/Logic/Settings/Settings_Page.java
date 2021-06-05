package iDoge.Logic.Settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import iDoge.Models.Bark_Comment;
import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.User;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static iDoge.Logic.Messenger.AfterMessengerPages.AfterPacksPage.Send_Message_Page.sendMessageToList;
import static iDoge.Logic.Other.Main_Menu.loadMainMenu;
import static iDoge.Logic.Other.Sign_in.start;
import static iDoge.Models.Profile.loadProfile;
import static iDoge.Logic.Settings.AfterSettingsPages.Privacy_Settings_Page.loadPrivacySettingsPage;

public class Settings_Page {
    private static Logger log = LogManager.getLogger(Settings_Page.class.getName());
    public static void loadSettingsPage(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "SETTINGS PAGE");
        log.info("SETTINGS PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter section number to enter:");
        log.info("Enter section number to enter:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Privacy settings");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Sign out");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Delete account");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                loadPrivacySettingsPage(profile);
                break;
            } else if (i.equals("2")){
                profile.setOnline(false);
                profile.setLastseen(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                profile.getUser().saveUser();
                profile.saveProfile();
                System.out.println(ConsoleColors.YELLOW_BOLD + profile.getUser().getUsername() + " Signed out!!!");
                log.info(profile.getUser().getUsername() + " Signed out!!!");
                System.out.println();
                start();
                break;
            } else if (i.equals("3")) {
                HashSet<User> userSett = new HashSet<>();
                userSett.addAll(profile.getFollowersList());
                userSett.addAll(profile.getFollowingsList());
                sendMessageToList(profile, new ArrayList<>(userSett), new Bark_Comment(profile.getUser().getUsername(), "[ACCOUNT DELETED]"));
                File dir = new File("Database/Users/");
                File[] directoryListing = dir.listFiles();
                if (directoryListing != null) {
                    for (File child : directoryListing) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        User user2 = objectMapper.readValue(child, User.class);
                        log.info("File opened");
                        if (!profile.getUser().getUsername().equals(user2.getUsername())){
                            userSett.add(user2);
                        }
                        log.info("File closed");
                    }
                }
                for (User myUser: userSett) {
                    Profile myProfile = loadProfile(myUser.getUsername());
                    myProfile.getFollowersList().removeIf(user -> user.getUsername().equals(profile.getUser().getUsername()));
                    myProfile.getFollowingsList().removeIf(user -> user.getUsername().equals(profile.getUser().getUsername()));
                    myProfile.getBlackList().removeIf(user -> user.getUsername().equals(profile.getUser().getUsername()));
                    myProfile.getMuteList().removeIf(user -> user.getUsername().equals(profile.getUser().getUsername()));
                    myProfile.getSpamList().removeIf(user -> user.getUsername().equals(profile.getUser().getUsername()));
                    for (String key: myProfile.getPacks().keySet()) {
                        myProfile.getPacks().get(key).removeIf(user -> user.getUsername().equals(profile.getUser().getUsername()));
                    }
                    myProfile.getSentFollowRequests().removeIf(req -> req.getReciever().getUsername().equals(profile.getUser().getUsername()));
                    myProfile.getRecievedFollowRequests().removeIf(req -> req.getSender().getUsername().equals(profile.getUser().getUsername()));
                    myProfile.saveProfile();
                }
                profile.getUser().setDeleted(true);
                profile.getUser().saveUser();
                profile.resetProfile();
                profile.saveProfile();
                System.out.println(ConsoleColors.YELLOW_BOLD + profile.getUser().getUsername() + " Account deleted!!!");
                log.info(profile.getUser().getUsername() + " Account deleted!!!");
                System.out.println();
                start();
                break;
            } else if (i.equals("0")){
                loadMainMenu(profile);
                break;
            }else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
                System.out.println();
            }
        }
    }
}

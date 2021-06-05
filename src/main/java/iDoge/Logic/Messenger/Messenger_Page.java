package iDoge.Logic.Messenger;

import iDoge.Logic.Messenger.AfterMessengerPages.Saved_Messages_Pages;
import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.User;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

import static iDoge.Logic.Messenger.AfterMessengerPages.Chat_List_Page.loadChatListPage;
import static iDoge.Logic.Messenger.AfterMessengerPages.Packs_Page.loadPacksPage;
import static iDoge.Logic.Other.Main_Menu.loadMainMenu;
import static iDoge.Models.Profile.loadProfile;

public class Messenger_Page {
    private static Logger log = LogManager.getLogger(Messenger_Page.class.getName());

    public static void loadMessengerPage(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "MESSENGER PAGE");
        log.info("MESSENGER PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter section number to enter:");
        log.info("Enter section number to enter:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Saved Messages");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Chats");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Packs");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                Saved_Messages_Pages.loadSavedMessagesPage(profile);
                break;
            } else if (i.equals("2")){
                loadChatListPage(profile);
                break;
            } else if (i.equals("3")){
                loadPacksPage(profile);
                break;
            } else if (i.equals("0")){
                loadMainMenu(profile);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
            }
        }
    }

    public static boolean isNotBlaclisted(String username1, String username2) throws IOException {
        Profile profile1 = loadProfile(username1);
        Profile profile2 = loadProfile(username2);
        log.info("isNotBlaclisted checked");
        for (User user: profile1.getBlackList()) if (user.getUsername().equals(profile2.getUser().getUsername())) return false;
        for (User user: profile2.getBlackList()) if (user.getUsername().equals(profile1.getUser().getUsername())) return false;
        return true;
    }

    public static boolean canCommunicate(String username1, String username2) throws IOException {
        Profile profile1 = loadProfile(username1);
        Profile profile2 = loadProfile(username2);
        log.info("Communication permission checked");
        for (User user: profile1.getFollowersList()) if (user.getUsername().equals(profile2.getUser().getUsername())) return true;
        for (User user: profile2.getFollowersList()) if (user.getUsername().equals(profile1.getUser().getUsername())) return true;
        return false;
    }

}

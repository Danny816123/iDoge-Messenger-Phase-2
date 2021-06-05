package iDoge.Logic.Profile.AfterProfilePages;

import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static iDoge.Models.User.loadUser;
import static iDoge.Logic.Profile.Personal_Profile_Page.loadPersonalProfilePage;
import static iDoge.Logic.Profile.Profile_Page.loadProfile_Page;
import static iDoge.Models.Profile.loadProfile;

public class Lists_Page {
    private static Logger log = LogManager.getLogger(Lists_Page.class.getName());
    public static void loadListsPage(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "LISTS PAGE");
        log.info("LISTS PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter list number to enter:");
        log.info("Enter list number to enter:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Followers");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Followings");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Blacklist");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                System.out.println(ConsoleColors.PURPLE_BOLD + "FOLLOWERS PAGE");
                log.info("FOLLOWERS PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Enter username to view profile or press enter to go back:");
                log.info("Enter username to view profile or press enter to go back:");
                System.out.println();
                ArrayList<String> usernameList = new ArrayList<>();
                for (int j = 1; j <= profile.getFollowersList().size(); j++){
                    if (loadProfile(profile.getFollowersList().get(j - 1).getUsername()).isActivated()) {
                        System.out.println(ConsoleColors.BLUE_BOLD + profile.getFollowersList().get(j - 1).getUsername());
                        System.out.println();
                        usernameList.add(profile.getFollowersList().get(j - 1).getUsername());
                    }
                }
                while (true) {
                    String uu = beholder.nextLine();
                    if (usernameList.contains(uu)) {
                        loadPersonalProfilePage(profile, uu);
                        break;
                    } else if (uu.equals("")) {
                        loadListsPage(profile);
                        break;
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                        log.error("Invalid command... :(");
                        System.out.println();
                    }
                }
                break;
            } else if (i.equals("2")){
                System.out.println(ConsoleColors.PURPLE_BOLD + "FOLLOWINGS PAGE");
                log.info("FOLLOWINGS PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Enter username to view profile or press enter to go back:");
                log.info("Enter username to view profile or press enter to go back:");
                System.out.println();
                ArrayList<String> usernameList = new ArrayList<>();
                for (int j = 1; j <= profile.getFollowingsList().size(); j++){
                    if (loadProfile(profile.getFollowingsList().get(j - 1).getUsername()).isActivated()) {
                        System.out.println(ConsoleColors.BLUE_BOLD + profile.getFollowingsList().get(j - 1).getUsername());
                        System.out.println();
                        usernameList.add(profile.getFollowingsList().get(j - 1).getUsername());
                    }
                }
                while (true) {
                    String uu = beholder.nextLine();
                    if (usernameList.contains(uu)) {
                        loadPersonalProfilePage(profile, uu);
                        break;
                    } else if (uu.equals("")) {
                        loadListsPage(profile);
                        break;
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                        log.error("Invalid command... :(");
                        System.out.println();
                    }
                }
                break;
            } else if (i.equals("3")){
                System.out.println(ConsoleColors.PURPLE_BOLD + "BLACKLIST PAGE");
                log.info("BLACKLIST PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Enter username to remove from Blacklist or press enter to go back::");
                log.info("Enter username to view profile or press enter to go back:");
                System.out.println();
                ArrayList<String> usernameList = new ArrayList<>();
                for (int j = 1; j <= profile.getBlackList().size(); j++){
                    if (loadProfile(profile.getFollowersList().get(j - 1).getUsername()).isActivated()) {
                        System.out.println(ConsoleColors.BLUE_BOLD + profile.getBlackList().get(j - 1).getUsername());
                        System.out.println();
                        usernameList.add(profile.getBlackList().get(j - 1).getUsername());
                    }
                }
                while (true) {
                    String uu = beholder.nextLine();
                    if (usernameList.contains(uu)) {
                        profile.getBlackList().remove(loadUser(uu));
                        profile.saveProfile();
                        break;
                    } else if (uu.equals("")) {
                        loadListsPage(profile);
                        break;
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                        log.error("Invalid command... :(");
                        System.out.println();
                    }
                }
                break;
            } else if (i.equals("0")){
                loadProfile_Page(profile);
                break;
            }else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
                System.out.println();
            }
        }
    }
}

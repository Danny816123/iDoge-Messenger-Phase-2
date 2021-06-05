package iDoge.Logic.Explorer;

import com.fasterxml.jackson.databind.ObjectMapper;
import iDoge.Models.Bark_Comment;
import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

import static iDoge.Logic.Other.Main_Menu.loadMainMenu;
import static iDoge.Logic.Profile.Personal_Profile_Page.loadPersonalProfilePage;
import static iDoge.Logic.Timeline.Pages.AfterTimelinePages.Your_Barks_Comments_Page2.loadYourBarksCommentsPage2;

public class Explorer_Page {
    private static Logger log = LogManager.getLogger(Explorer_Page.class.getName());
    public static void loadExplorerPage(Profile profile) throws IOException {
        HashSet<Bark_Comment> postSet = new HashSet<>();
        HashSet<String> usernameSet = new HashSet<>();
        File dir = new File("Database/Profiles/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                ObjectMapper objectMapper = new ObjectMapper();
                Profile profile1 = objectMapper.readValue(child, Profile.class);
                log.info("File opened");
                if (profile1.isActivated() && !profile1.getUser().isDeleted()) {
                    usernameSet.add(profile1.getUser().getUsername());
                    if (profile1.isPublic()) postSet.addAll(profile1.getBarksList());
                }
                log.info("File closed");
            }
        }
        ArrayList<Bark_Comment> postList = new ArrayList<>(postSet);
        ArrayList<String> usernameList = new ArrayList<>(usernameSet);
        Collections.shuffle(postList);
        log.info("Posts shuffled");
        System.out.println(ConsoleColors.PURPLE_BOLD + "EXPLORER PAGE");
        log.info("EXPLORER PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter command number to execute:");
        log.info("Enter command number to execute:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Search users");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Explore Barks");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                System.out.println(ConsoleColors.PURPLE_BOLD + "EXPLORE USERS");
                log.info("EXPLORE USERS");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Enter username to view profile or press enter to go back:");
                log.info("Enter username to view profile or press enter to go back:");
                System.out.println();
                while (true) {
                    String uu = beholder.nextLine();
                    if (usernameList.contains(uu)) {
                        loadPersonalProfilePage(profile, uu);
                        break;
                    } else if (uu.equals("")) {
                        loadExplorerPage(profile);
                        break;
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD + "User not found... :(");
                        log.error("User not found... :(");
                        System.out.println();
                    }
                }
                break;
            } else if (i.equals("2")){
                loadYourBarksCommentsPage2(profile, profile, postList, new ArrayList<>(), postList, 0);
                break;
            } else if (i.equals("0")){
                loadMainMenu(profile);
                break;
            }else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
            }
        }

    }
}

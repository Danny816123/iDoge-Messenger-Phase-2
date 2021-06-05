package iDoge.Logic.Profile.AfterProfilePages;

import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Profile;
import iDoge.Logic.Profile.Profile_Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;


public class Info_Page {
    private static Logger log = LogManager.getLogger(Info_Page.class.getName());
    public static void showInfo(Profile profile) throws IOException {
        profile.showInfo();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String kk = beholder.nextLine();
            if (kk.equals("0")){
                Profile_Page.loadProfile_Page(profile);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
                System.out.println();
            }
        }
    }

    public static void editInfo(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "Edit your info:");
        log.info("Edit your info:");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter section number to enter:");
        log.info("Enter section number to enter:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Edit name");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Edit birthday");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Edit email");
        System.out.println(ConsoleColors.BLUE_BOLD + "4) Edit phone number");
        System.out.println(ConsoleColors.BLUE_BOLD + "5) Edit bio");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                System.out.println(ConsoleColors.PURPLE_BOLD + "EDIT NAME PAGE");
                log.info("EDIT NAME PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new name or press Enter to go back:");
                log.info("Write your new name or press Enter to go back:");
                System.out.println();
                String ii = beholder.nextLine();
                if (!ii.equals("")) {
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Name edited!!!");
                    log.info("Name edited!!!");
                    System.out.println();
                    profile.setName(ii);
                    profile.saveProfile();
                }
            } else if (i.equals("2")){
                System.out.println(ConsoleColors.PURPLE_BOLD + "EDIT BIRTHDAY PAGE");
                log.info("EDIT BIRTHDAY PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new birthday or press Enter to go back:");
                log.info("Write your new birthday or press Enter to go back:");
                System.out.println();
                String ii = beholder.nextLine();
                if (!ii.equals("")) {
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Birthday edited!!!");
                    log.info("Birthday edited!!!");
                    System.out.println();
                    profile.setBirthday(ii);
                    profile.getUser().saveUser();
                    profile.saveProfile();
                }
            } else if (i.equals("3")){
                System.out.println(ConsoleColors.PURPLE_BOLD + "EDIT EMAIL PAGE");
                log.info("EDIT EMAIL PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new email or press Enter to go back:");
                log.info("Write your new email or press Enter to go back:");
                System.out.println();
                String ii = beholder.nextLine();
                if (!ii.equals("")) {
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Email edited!!!");
                    log.info("Email edited!!!");
                    System.out.println();
                    profile.setEmail(ii);
                    profile.getUser().saveUser();
                    profile.saveProfile();
                }
            } else if (i.equals("4")){
                System.out.println(ConsoleColors.PURPLE_BOLD + "EDIT PHONE NUMBER PAGE");
                log.info("EDIT PHONE NUMBER PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new phone number or press Enter to go back:");
                log.info("Write your new phone number or press Enter to go back:");
                System.out.println();
                String ii = beholder.nextLine();
                if (!ii.equals("")) {
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Phone number edited!!!");
                    log.info("Phone number edited!!!");
                    System.out.println();
                    profile.setPhone(ii);
                    profile.getUser().saveUser();
                    profile.saveProfile();
                }
            } else if (i.equals("5")){
                System.out.println(ConsoleColors.PURPLE_BOLD + "EDIT BIO PAGE");
                log.info("EDIT BIO PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new bio or press Enter to go back:");
                log.info("Write your new bio or press Enter to go back:");
                System.out.println();
                String ii = beholder.nextLine();
                if (!ii.equals("")) {
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Bio edited!!!");
                    log.info("Bio edited!!!");
                    System.out.println();
                    profile.setBio(ii);
                    profile.getUser().saveUser();
                    profile.saveProfile();
                }
            } else if (i.equals("0")){
                Profile_Page.loadProfile_Page(profile);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
                System.out.println();
            }
        }
    }
}

package iDoge.Logic.Settings.AfterSettingsPages;

import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

import static iDoge.Logic.Settings.Settings_Page.loadSettingsPage;

public class Privacy_Settings_Page {
    private static Logger log = LogManager.getLogger(Privacy_Settings_Page.class.getName());
    public static void loadPrivacySettingsPage(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "PRIVACY SETTINGS PAGE");
        log.info("PRIVACY SETTINGS PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter section number to enter:");
        log.info("Enter section number to enter:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Public/Private settings");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Last seen & Online settings");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Activation settings");
        System.out.println(ConsoleColors.BLUE_BOLD + "4) Password settings");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                System.out.println(ConsoleColors.PURPLE_BOLD + "PUBLIC/PRIVATE SETTINGS PAGE");
                log.info("PUBLIC/PRIVATE SETTINGS PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Write 1 to set public or 2 to set private or press Enter to go back:");
                log.info("Write 1 to set public or 2 to set private or press Enter to go back:");
                System.out.println();
                String ii = beholder.nextLine();
                if (!ii.equals("")) {
                    while (true) {
                        if (ii.equals("1")) {
                            profile.setPublic(true);
                            System.out.println();
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Account set public!!!");
                            log.info("Account set public!!!");
                            System.out.println();
                            break;
                        } else if (ii.equals("2")) {
                            profile.setPublic(false);
                            System.out.println();
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Account set private!!!");
                            log.info("Account set private!!!");
                            System.out.println();
                            break;
                        } else {
                            System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                            log.error("Invalid command... :(");
                            System.out.println();
                        }
                    }
                    profile.getUser().saveUser();
                    profile.saveProfile();
                }
            } else if (i.equals("2")){
                System.out.println(ConsoleColors.PURPLE_BOLD + "LAST SEEN & ONLINE SETTINGS PAGE");
                log.info("LAST SEEN & ONLINE SETTINGS PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Write 1 to show to everyone or 2 to show to no one or 3 to show to followings or press Enter to go back:");
                log.info("Write 1 to show to everyone or 2 to show to no one or 3 to show to followings or press Enter to go back:");
                System.out.println();
                String ii = beholder.nextLine();
                if (!ii.equals("")) {
                    while (true) {
                        if (ii.equals("1")) {
                            profile.setLastSeen$Online("Everyone");
                            System.out.println();
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Last seen & Online setting set for everyone!!!");
                            log.info("Last seen & Online setting set for everyone!!!");
                            System.out.println();
                            break;
                        } else if (ii.equals("2")) {
                            profile.setLastSeen$Online("No one");
                            System.out.println();
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Last seen & Online setting set for no one!!!");
                            log.info("Last seen & Online setting set for no one!!!");
                            System.out.println();
                            break;
                        } else if (ii.equals("3")) {
                            profile.setLastSeen$Online("Followings");
                            System.out.println();
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Last seen & Online setting set for followings!!!");
                            log.info("Last seen & Online setting set for followings!!!");
                            System.out.println();
                            break;
                        } else {
                            System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                            log.error("Invalid command... :(");
                            System.out.println();
                        }
                    }
                    profile.getUser().saveUser();
                    profile.saveProfile();
                }
            } else if (i.equals("3")) {
                System.out.println(ConsoleColors.PURPLE_BOLD + "ACTIVATION SETTINGS PAGE");
                log.info("ACTIVATION SETTINGS PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Write 1 to activate or 2 to deactivate or press Enter to go back:");
                log.info("Write 1 to activate or 2 to deactivate or press Enter to go back:");
                System.out.println();
                String ii = beholder.nextLine();
                if (!ii.equals("")) {
                    while (true) {
                        if (ii.equals("1")) {
                            System.out.println();
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Account activated!!!");
                            log.info("Account activated!!!");
                            System.out.println();
                            profile.setActivated(true);
                            break;
                        } else if (ii.equals("2")) {
                            System.out.println();
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Account deactivated!!!");
                            log.info("Account deactivated!!!");
                            System.out.println();
                            profile.setActivated(false);
                            break;
                        } else {
                            System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                            log.error("Invalid command... :(");
                            System.out.println();
                        }
                    }
                    profile.getUser().saveUser();
                    profile.saveProfile();
                }
            } else if (i.equals("4")) {
                System.out.println(ConsoleColors.PURPLE_BOLD + "EDIT PASSWORD PAGE");
                log.info("EDIT PASSWORD PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new password or press Enter to go back:");
                log.info("Write your new password or press Enter to go back:");
                System.out.println();
                String ii = beholder.nextLine();
                if (!ii.equals("")) {
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Password edited!!!");
                    log.info("Password edited!!!");
                    System.out.println();
                    profile.setPassword(ii);
                    profile.saveProfile();
                }
            } else if (i.equals("0")){
                loadSettingsPage(profile);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
                System.out.println();
            }
        }
    }
}

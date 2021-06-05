package iDoge.Graphics.Other;

import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

import static iDoge.Logic.Explorer.Explorer_Page.loadExplorerPage;
import static iDoge.Logic.Messenger.Messenger_Page.loadMessengerPage;
import static iDoge.Logic.Profile.Profile_Page.loadProfile_Page;
import static iDoge.Logic.Settings.Settings_Page.loadSettingsPage;
import static iDoge.Logic.Timeline.Timeline_Page.loadTimelinePage;

public class Main_Menu {
    private static Logger log = LogManager.getLogger(Main_Menu.class.getName());
    public static void loadMainMenu(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "MAIN MENU");
        log.info("MAIN MENU");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter section number to enter:");
        log.info("Enter section number to enter:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Profile");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Timeline");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Explorer");
        System.out.println(ConsoleColors.BLUE_BOLD + "4) Messenger");
        System.out.println(ConsoleColors.BLUE_BOLD + "5) Settings");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                loadProfile_Page(profile);
                break;
            } else if (i.equals("2")){
                loadTimelinePage(profile);
                break;
            } else if (i.equals("3")){
                loadExplorerPage(profile);
                break;
            } else if (i.equals("4")){
                loadMessengerPage(profile);
                break;
            } else if (i.equals("5")){
                loadSettingsPage(profile);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
            }
        }
    }
}

package iDoge.Logic.Profile.AfterProfilePages;

import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Follow_Request;
import iDoge.Models.Profile;
import iDoge.Logic.Profile.AfterProfilePages.AfterNotificationsPage.Received_Follow_Request_Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static iDoge.Logic.Profile.Profile_Page.loadProfile_Page;

public class Notifications_Page {
    private static Logger log = LogManager.getLogger(Notifications_Page.class.getName());
    public static void loadNotificationsPage(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "NOTIFICATIONS PAGE");
        log.info("NOTIFICATIONS PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter request number to enter:");
        log.info("Enter request number to enter:");
        System.out.println();
        System.out.println(ConsoleColors.GREEN_BOLD + "System notifications:");
        for (String notif: profile.getNotifications()) {
            System.out.println(ConsoleColors.GREEN_BOLD + notif);
        }
        System.out.println();
        System.out.println(ConsoleColors.GREEN_BOLD + "Sent follow requests:");
        for (Follow_Request req: profile.getSentFollowRequests()) {
            System.out.println(ConsoleColors.GREEN_BOLD + req.getReciever().getUsername() + ": " + req.getStatus());
        }
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "Received follow requests:");
        for (int i = 1; i <= profile.getRecievedFollowRequests().size(); i++){
            Follow_Request req = profile.getRecievedFollowRequests().get(i - 1);
            System.out.println(ConsoleColors.BLUE_BOLD + i + ") " + req.getSender().getUsername());
        }
        ArrayList<String> numbersList = new ArrayList<>();
        for (int i = 1; i <= profile.getRecievedFollowRequests().size(); i++) numbersList.add(String.valueOf(i));
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (numbersList.contains(i)){
                Received_Follow_Request_Page.loadReceivedNotificationPage(profile, profile.getRecievedFollowRequests().get(Integer.parseInt(i) - 1));
                break;
            } else if (i.equals("0")){
                loadProfile_Page(profile);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
                System.out.println();
            }
        }
    }
}

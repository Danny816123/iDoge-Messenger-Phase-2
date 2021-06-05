package iDoge.Logic.Profile.AfterProfilePages.AfterNotificationsPage;

import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Follow_Request;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

import static iDoge.Logic.Profile.AfterProfilePages.Notifications_Page.loadNotificationsPage;
import static iDoge.Models.Profile.loadProfile;

public class Received_Follow_Request_Page {
    private static Logger log = LogManager.getLogger(Received_Follow_Request_Page.class.getName());
    public static void loadReceivedNotificationPage(Profile profile, Follow_Request req) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "RECEIVED FOLLOW REQUEST FROM" + req.getSender().getUsername() + " PAGE");
        log.info("RECEIVED FOLLOW REQUEST FROM" + req.getSender().getUsername() + " PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter command number to execute:");
        log.info("Enter command number to execute:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Accept");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Reject and inform");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Reject and don't inform");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        Profile senderProfile = loadProfile(req.getSender().getUsername());
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")) {
                profile.getRecievedFollowRequests().remove(req);
                senderProfile.getSentFollowRequests().remove(req);
                req.setStatus("Accepted");
                senderProfile.getSentFollowRequests().add(req);
                profile.getFollowersList().add(senderProfile.getUser());
                senderProfile.getFollowingsList().add(profile.getUser());
                profile.saveProfile();
                senderProfile.saveProfile();
                loadNotificationsPage(profile);
                System.out.println(ConsoleColors.YELLOW_BOLD + "Follow request accepted");
                log.info("Follow request accepted");
                break;
            } else if (i.equals("2")) {
                profile.getRecievedFollowRequests().remove(req);
                senderProfile.getSentFollowRequests().remove(req);
                req.setStatus("Rejected");
                senderProfile.getSentFollowRequests().add(req);
                profile.saveProfile();
                senderProfile.saveProfile();
                loadNotificationsPage(profile);
                System.out.println(ConsoleColors.YELLOW_BOLD + "Follow request rejected and informed");
                log.info("Follow request rejected and informed");
                break;
            } else if (i.equals("3")) {
                profile.getRecievedFollowRequests().remove(req);
                profile.saveProfile();
                loadNotificationsPage(profile);
                System.out.println(ConsoleColors.YELLOW_BOLD + "Follow request rejected and didn't inform");
                log.info("Follow request rejected and didn't inform");
                break;
            } else if (i.equals("0")) {
                loadNotificationsPage(profile);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
            }
        }
    }
}

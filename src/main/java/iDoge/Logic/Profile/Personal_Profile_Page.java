package iDoge.Logic.Profile;

import iDoge.Models.Bark_Comment;
import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Follow_Request;
import iDoge.Models.User;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

import static iDoge.Logic.Messenger.AfterMessengerPages.AfterPacksPage.Send_Message_Page.loadSendMessagePage;
import static iDoge.Logic.Messenger.Messenger_Page.isNotBlaclisted;
import static iDoge.Logic.Other.Main_Menu.loadMainMenu;
import static iDoge.Models.User.listContainsUser;
import static iDoge.Models.User.loadUser;
import static iDoge.Models.Profile.loadProfile;

public class Personal_Profile_Page {
    private static Logger log = LogManager.getLogger(Personal_Profile_Page.class.getName());
    public static void loadPersonalProfilePage(Profile profile, String username) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "PERSONAL PROFILE PAGE");
        log.info("PERSONAL PROFILE PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter command number to execute:");
        log.info("Enter command number to execute:");
        System.out.println();
        User thatUser = loadUser(username);
        Profile theirProfile = loadProfile(username);
        System.out.println(ConsoleColors.GREEN_BOLD + theirProfile.getName());
        System.out.println(ConsoleColors.GREEN_BOLD + username);
        if (listContainsUser(theirProfile.getFollowersList(), profile.getUser())){
            if (theirProfile.getLastSeen$Online().equals("Everyone")){
                System.out.println(ConsoleColors.GREEN_BOLD + theirProfile.getLastseen());
            } else if (theirProfile.getLastSeen$Online().equals("Followings")){
                if (listContainsUser(theirProfile.getFollowingsList(), profile.getUser())){
                    System.out.println(ConsoleColors.GREEN_BOLD + theirProfile.getLastseen());
                } else {
                    System.out.println(ConsoleColors.GREEN_BOLD + "Last seen recently...");
                }
            } else {
                System.out.println(ConsoleColors.GREEN_BOLD + "Last seen recently...");
            }
            System.out.println(ConsoleColors.GREEN_BOLD + "Following...");
        } else {
            System.out.println(ConsoleColors.GREEN_BOLD + "Last seen recently...");
            System.out.println(ConsoleColors.GREEN_BOLD + "Not following...");
        }
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Follow/send follow request or unfollow user");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Send message");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Block user");
        System.out.println(ConsoleColors.BLUE_BOLD + "4) Report user");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (!thatUser.isDeleted()) {
                if (i.equals("1")) {
                    if (!listContainsUser(theirProfile.getFollowersList(), profile.getUser())) {
                        if (isNotBlaclisted(username, profile.getUser().getUsername())) {
                            if (theirProfile.isPublic()) {
                                theirProfile.getFollowersList().add(profile.getUser());
                                profile.getFollowingsList().add(thatUser);
                                theirProfile.getNotifications().add(profile.getUser().getUsername() + " started following you!!!");
                                theirProfile.saveProfile();
                                profile.saveProfile();
                                System.out.println(ConsoleColors.YELLOW_BOLD + "User followed!!!");
                                log.info("User followed!!!");
                            } else {
                                Follow_Request req = new Follow_Request(profile.getUser(), thatUser);
                                theirProfile.getRecievedFollowRequests().add(req);
                                profile.getSentFollowRequests().add(req);
                                theirProfile.saveProfile();
                                profile.saveProfile();
                                System.out.println(ConsoleColors.YELLOW_BOLD + "Follow request sent!!!");
                                log.info("Follow request sent!!!");
                            }
                        } else {
                            System.out.println(ConsoleColors.RED_BOLD + "Blacklist error... :(");
                            log.error("Blacklist error... :(");
                        }
                    } else {
                        theirProfile.getFollowersList().remove(profile.getUser());
                        profile.getFollowingsList().remove(thatUser);
                        theirProfile.getNotifications().add(profile.getUser().getUsername() + " stopped following you!!!");
                        theirProfile.saveProfile();
                        profile.saveProfile();
                        System.out.println(ConsoleColors.YELLOW_BOLD + "User unfollowed!!!");
                        log.info("User unfollowed!!!");
                    }
                    System.out.println();
                } else if (i.equals("2")) {
                    if (listContainsUser(theirProfile.getFollowersList(), profile.getUser())) {
                        if (isNotBlaclisted(username, profile.getUser().getUsername())) {
                            System.out.println(ConsoleColors.PURPLE_BOLD + "WRITE NEW MESSAGE PAGE");
                            log.info("WRITE NEW MESSAGE PAGE");
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new message or press Enter to go back:");
                            log.info("Write your new message or press Enter to go back:");
                            System.out.println();
                            String ii = beholder.nextLine();
                            if (!ii.equals("")) {
                                System.out.println();
                                System.out.println(ConsoleColors.YELLOW_BOLD + "New message written!!!");
                                log.info("New message written!!!");
                                System.out.println();
                                Bark_Comment message = new Bark_Comment(profile.getUser().getUsername(), ii);
                                loadSendMessagePage(profile, message);
                                break;
                            } else {
                                loadPersonalProfilePage(profile, username);
                            }
                        } else {
                            System.out.println(ConsoleColors.RED_BOLD + "Blacklist error... :(");
                            log.error("Blacklist error... :(");
                        }
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD + "Messenger rules error... :(");
                        log.error("Messenger rules error... :(");
                    }
                } else if (i.equals("3")) {
                    HashSet<User> tSet = new HashSet<>(profile.getBlackList());
                    tSet.add(thatUser);
                    profile.getBlackList().clear();
                    profile.getBlackList().addAll(tSet);
                    profile.saveProfile();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "User blacklisted!!!");
                    log.info("User blacklisted!!!");
                    System.out.println();
                } else if (i.equals("4")) {
                    HashSet<User> tSet = new HashSet<>(profile.getMuteList());
                    tSet.add(thatUser);
                    profile.getSpamList().clear();
                    profile.getSpamList().addAll(tSet);
                    profile.saveProfile();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "User reported!!!");
                    log.info("User reported!!!");
                    System.out.println();
                } else if (i.equals("0")) {
                    loadMainMenu(profile);
                    break;
                } else {
                    System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                    log.error("Invalid command... :(");
                    System.out.println();
                }
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "User is deleted... :(");
                log.error("User is deleted... :(");
                System.out.println();
                loadMainMenu(profile);
                break;
            }
        }
    }
}

package iDoge.Logic.Timeline.Pages.AfterTimelinePages.AfterYourBarksCommentsPages;

import iDoge.Models.Bark_Comment;
import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.User;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static iDoge.Logic.Messenger.AfterMessengerPages.AfterPacksPage.Send_Message_Page.loadSendMessagePage;
import static iDoge.Models.User.loadUser;
import static iDoge.Logic.Profile.Personal_Profile_Page.loadPersonalProfilePage;
import static iDoge.Logic.Timeline.Pages.AfterTimelinePages.New_Bark_Comment_Page2.loadNew_Comment_Page2;
import static iDoge.Logic.Timeline.Pages.AfterTimelinePages.Your_Barks_Comments_Page2.loadYourBarksCommentsPage2;
import static iDoge.Logic.Timeline.Timeline_Page.loadYourBarksCommentsPageFromAddress2;

public class Bark_Comment_Page2 {
    private static Logger log = LogManager.getLogger(Bark_Comment_Page2.class.getName());
    public static void loadBarkCommentPage2(Profile profile, Profile profile2, ArrayList<Bark_Comment> postlist, ArrayList<Integer> address, ArrayList<Bark_Comment> list, int w) throws IOException {
        Bark_Comment barkComment = list.get(w - 1);
        System.out.println(ConsoleColors.PURPLE_BOLD + "BARK / COMMENT No. " + w + '/' + list.size());
        log.info("BARK / COMMENT No. " + w + '/' + list.size());
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter Bark number to enter:");
        log.info("Enter Bark number to enter:");
        System.out.println();
        System.out.println(ConsoleColors.PURPLE_BOLD + barkComment.getWriter());
        System.out.println(ConsoleColors.CYAN_BOLD + barkComment.getText());
        System.out.println();
        System.out.println(ConsoleColors.PURPLE_BOLD + barkComment.getPublishedDate());
        ArrayList<String> liked = new ArrayList<>();
        for (User my: barkComment.getLikedUsers()) {
            liked.add(my.getUsername());
        }
        System.out.println(ConsoleColors.PURPLE_BOLD + "Liked by: " + liked.toString());
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Next Bark/Comment");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Previous Bark/Comment");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Add to Saved Messages");
        System.out.println(ConsoleColors.BLUE_BOLD + "4) ReBark");
        System.out.println(ConsoleColors.BLUE_BOLD + "5) Forward");
        System.out.println(ConsoleColors.BLUE_BOLD + "6) Block user");
        System.out.println(ConsoleColors.BLUE_BOLD + "7) Mute user");
        System.out.println(ConsoleColors.BLUE_BOLD + "8) Report spam");
        System.out.println(ConsoleColors.BLUE_BOLD + "9) View profile");
        System.out.println(ConsoleColors.BLUE_BOLD + "10) Add comment");
        System.out.println(ConsoleColors.BLUE_BOLD + "11) Show comments");
        System.out.println(ConsoleColors.BLUE_BOLD + "12) Like Bark/Comment");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println(address.toString());
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                if (w == list.size()) {
                    loadBarkCommentPage2(profile, profile2, postlist, address, list, 1);
                } else {
                    loadBarkCommentPage2(profile, profile2, postlist, address, list, w + 1);
                }
                break;
            } else if (i.equals("2")){
                if (w == 1) {
                    loadBarkCommentPage2(profile, profile2, postlist, address, list, list.size());
                } else {
                    loadBarkCommentPage2(profile, profile2, postlist, address, list, w - 1);
                }
                break;
            } else if (i.equals("3")){
                profile.getSavedMessages().add(barkComment);
                profile.saveProfile();
                System.out.println(ConsoleColors.YELLOW_BOLD + "Added to Saved Messages!!!");
                log.info("Added to Saved Messages!!!");
                System.out.println();
            } else if (i.equals("4")){
                profile.getBarksList().add(barkComment);
                profile.saveProfile();
                System.out.println(ConsoleColors.YELLOW_BOLD + "ReBarked!!!");
                log.info("ReBarked!!!");
                System.out.println();
            } else if (i.equals("5")){
                Bark_Comment message = new Bark_Comment(profile.getUser().getUsername(), "[FORWARDED FROM: " + barkComment.getWriter() + "] " + barkComment.getText());
                System.out.println(ConsoleColors.YELLOW_BOLD + "Forwarded!!!");
                log.info("Forwarded!!!");
                System.out.println();
                loadSendMessagePage(profile, message);
            } else if (i.equals("6")){
                if (barkComment.getWriter().equals(profile.getUser().getUsername())){
                    System.out.println(ConsoleColors.RED_BOLD + "You cannot blacklist yourself... :(");
                    log.error("You cannot blacklist yourself... :(");
                    System.out.println();
                } else {
                    HashSet<User> tSet = new HashSet<>(profile.getBlackList());
                    tSet.add(loadUser(barkComment.getWriter()));
                    profile.getBlackList().clear();
                    profile.getBlackList().addAll(tSet);
                    profile.saveProfile();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "User blacklisted!!!");
                    log.info("User blacklisted!!!");
                    System.out.println();
                    break;
                }
            } else if (i.equals("7")){
                if (barkComment.getWriter().equals(profile.getUser().getUsername())){
                    System.out.println(ConsoleColors.RED_BOLD + "You cannot mute yourself... :(");
                    log.error("You cannot mute yourself... :(");
                    System.out.println();
                } else {
                    HashSet<User> tSet = new HashSet<>(profile.getMuteList());
                    tSet.add(loadUser(barkComment.getWriter()));
                    profile.getMuteList().clear();
                    profile.getMuteList().addAll(tSet);
                    profile.saveProfile();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "User muted!!!");
                    log.info("User muted!!!");
                    System.out.println();
                    break;
                }
            } else if (i.equals("8")){
                if (barkComment.getWriter().equals(profile.getUser().getUsername())){
                    System.out.println(ConsoleColors.RED_BOLD + "You cannot report spam yourself... :(");
                    log.error("You cannot report spam yourself... :(");
                    System.out.println();
                } else {
                    profile.getSpamList().add(loadUser(barkComment.getWriter()));
                    profile.saveProfile();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "User reported!!!");
                    log.info("User reported!!!");
                    System.out.println();
                    break;
                }
            } else if (i.equals("9")){
                if (barkComment.getWriter().equals(profile.getUser().getUsername())){
                    System.out.println(ConsoleColors.RED_BOLD + "You cannot open your personal profile page... :(");
                    log.error("You cannot open your personal profile page... :(");
                    System.out.println();
                } else {
                    loadPersonalProfilePage(profile, barkComment.getWriter());
                    break;
                }
                break;
            } else if (i.equals("10")){
                loadNew_Comment_Page2(profile, profile2, postlist, address, barkComment.getCommentsList(), list, w);
                break;
            } else if (i.equals("11")){
                loadYourBarksCommentsPage2(profile, profile2, postlist, address, barkComment.getCommentsList(), 1);
                break;
            } else if (i.equals("12")){
                barkComment.getLikedUsers().add(profile.getUser());
                profile2.saveProfile();
                System.out.println(ConsoleColors.YELLOW_BOLD + "Liked!!!");
                log.info("Liked!!!");
                System.out.println();
            } else if (i.equals("0")){
                int x = address.size();
                address.remove(x - 1);
                loadYourBarksCommentsPageFromAddress2(profile, profile2, postlist, address, 0, new Bark_Comment("invalid1", "invalid5"));
                break;
            }else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.info("Invalid command... :(");
                System.out.println();
            }
        }
    }
}

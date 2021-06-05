package iDoge.Logic.Profile.AfterProfilePages;

import iDoge.Models.Bark_Comment;
import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Profile;
import iDoge.Logic.Profile.AfterProfilePages.AfterYourBarksCommentsPage.Bark_Comment_Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static iDoge.Logic.Profile.Profile_Page.loadProfile_Page;

public class New_Bark_Comment_Page {
    private static Logger log = LogManager.getLogger(New_Bark_Comment_Page.class.getName());
    public static void loadNew_Bark_Page(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "NEW BARK PAGE");
        log.info("NEW BARK PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new Bark or press Enter to go back:");
        log.info("Write your new Bark or press Enter to go back:");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        String i = beholder.nextLine();
        if (!i.equals("")) {
            System.out.println();
            System.out.println(ConsoleColors.YELLOW_BOLD + "New Bark posted!!!");
            log.info("New Bark posted!!!");
            System.out.println();
            profile.getBarksList().add(new Bark_Comment(profile.getUser().getUsername(), i));
            profile.saveProfile();
        }
        loadProfile_Page(profile);
    }

    public static void loadNew_Comment_Page(Profile profile, ArrayList<Integer> address, ArrayList<Bark_Comment> commentsList, ArrayList<Bark_Comment> prevCommentsList, int w) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "NEW COMMENT PAGE");
        log.info("NEW COMMENT PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new Comment or press Enter to go back:");
        log.info("Write your new Comment or press Enter to go back:");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        String i = beholder.nextLine();
        if (!i.equals("")) {
            System.out.println();
            System.out.println(ConsoleColors.YELLOW_BOLD + "New Comment posted!!!");
            log.info("New Comment posted!!!");
            System.out.println();
            commentsList.add(new Bark_Comment(profile.getUser().getUsername(), i));
            profile.saveProfile();
        }
        Bark_Comment_Page.loadBarkCommentPage(profile, address, prevCommentsList, w);
    }
}

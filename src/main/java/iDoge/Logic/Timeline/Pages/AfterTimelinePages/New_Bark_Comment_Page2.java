package iDoge.Logic.Timeline.Pages.AfterTimelinePages;

import iDoge.Models.Bark_Comment;
import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static iDoge.Logic.Timeline.Pages.AfterTimelinePages.AfterYourBarksCommentsPages.Bark_Comment_Page2.loadBarkCommentPage2;

public class New_Bark_Comment_Page2 {
    private static Logger log = LogManager.getLogger(New_Bark_Comment_Page2.class.getName());
    public static void loadNew_Comment_Page2(Profile profile, Profile profile2, ArrayList<Bark_Comment> postList, ArrayList<Integer> address, ArrayList<Bark_Comment> commentsList, ArrayList<Bark_Comment> prevCommentsList, int w) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "NEW COMMENT PAGE");
        log.info("NEW COMMENT PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new Comment or press Enter to go back:");
        log.info("Write your new Comment or press Enter to go back:");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        String i = beholder.nextLine();
        System.out.println();
        if (!i.equals("")) {
            System.out.println(ConsoleColors.YELLOW_BOLD + "New Comment posted!!!");
            log.info("New Comment posted!!!");
            System.out.println();
            commentsList.add(new Bark_Comment(profile.getUser().getUsername(), i));
            profile2.saveProfile();
        }
        loadBarkCommentPage2(profile, profile2, postList, address, prevCommentsList, w);
    }
}

package iDoge.Logic.Timeline.Pages.AfterTimelinePages;

import iDoge.Models.Bark_Comment;
import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static iDoge.Logic.Other.Main_Menu.loadMainMenu;
import static iDoge.Models.Profile.loadProfile;
import static iDoge.Logic.Timeline.Pages.AfterTimelinePages.AfterYourBarksCommentsPages.Bark_Comment_Page2.loadBarkCommentPage2;
import static iDoge.Logic.Timeline.Timeline_Page.loadBarkCommentPageFromAddress2;

public class Your_Barks_Comments_Page2 {
    private static Logger log = LogManager.getLogger(Your_Barks_Comments_Page2.class.getName());
    public static void loadYourBarksCommentsPage2(Profile profile, Profile profile2, ArrayList<Bark_Comment> postlist, ArrayList<Integer> address, ArrayList<Bark_Comment> yourList, int t) throws IOException {
        if (t != 0){
            System.out.println(ConsoleColors.PURPLE_BOLD + "COMMENTS PAGE");
            log.info("COMMENTS PAGE");
            System.out.println(ConsoleColors.YELLOW_BOLD + "Enter Comment number to enter:");
            log.info("Enter Comment number to enter:");
        } else {
            System.out.println(ConsoleColors.PURPLE_BOLD + "BARKS PAGE");
            log.info("BARKS PAGE");
            System.out.println(ConsoleColors.YELLOW_BOLD + "Enter Bark number to enter:");
            log.info("Enter Bark number to enter:");
        }
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println(address.toString());
        System.out.println();
        for (int i = 1; i <= yourList.size(); i++){
            Bark_Comment bark = yourList.get(i - 1);
            System.out.println(ConsoleColors.GREEN_BOLD + i + ") " + bark.getWriter());
            System.out.println(ConsoleColors.BLUE_BOLD + bark.getText());
            System.out.println();
        }
        ArrayList<String> numbersList = new ArrayList<>();
        for (int i = 1; i <= yourList.size(); i++) numbersList.add(String.valueOf(i));
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (numbersList.contains(i)){
                address.add(Integer.parseInt(i) - 1);
                if (address.size() > 1) loadBarkCommentPage2(profile, profile2, postlist, address, yourList ,Integer.parseInt(i));
                else loadBarkCommentPage2(profile, loadProfile(yourList.get(Integer.parseInt(i) - 1).getWriter()), postlist, address, yourList ,Integer.parseInt(i));
                break;
            } else if (i.equals("0") && t != 0){
                loadBarkCommentPageFromAddress2(profile, profile2, postlist, address, 0, new Bark_Comment("invalid1", "invalid5"));
                break;
            } else if (i.equals("0")){
                loadMainMenu(profile);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
                System.out.println();
            }
        }
    }
}

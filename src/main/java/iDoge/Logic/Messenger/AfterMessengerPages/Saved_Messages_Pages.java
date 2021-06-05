package iDoge.Logic.Messenger.AfterMessengerPages;

import iDoge.Models.Bark_Comment;
import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

import static iDoge.Logic.Messenger.Messenger_Page.loadMessengerPage;

public class Saved_Messages_Pages {
    private static Logger log = LogManager.getLogger(Saved_Messages_Pages.class.getName());
    public static void loadSavedMessagesPage(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "SAVED MESSAGES PAGE");
        log.info("SAVED MESSAGES PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new message or press Enter to go back:");
        log.info("Write your new message or press Enter to go back:");
        System.out.println();
        for (int i = 1; i <= profile.getSavedMessages().size(); i++){
            Bark_Comment bark = profile.getSavedMessages().get(i - 1);
            System.out.println(ConsoleColors.GREEN_BOLD + i + ") " + bark.getWriter());
            System.out.println(ConsoleColors.BLUE_BOLD + bark.getText());
            System.out.println();
        }
        Scanner beholder = new Scanner(System.in);
        String i = beholder.nextLine();
        if (!i.equals("")) {
            System.out.println(ConsoleColors.YELLOW_BOLD + "New message posted!!!");
            log.info("New message posted!!!");
            System.out.println();
            profile.getSavedMessages().add(new Bark_Comment(profile.getUser().getUsername(), i));
            profile.saveProfile();
        }
        loadMessengerPage(profile);
    }
}

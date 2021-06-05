package iDoge.Logic.Messenger.AfterMessengerPages.AfterChatListPages;

import iDoge.Models.Chat;
import iDoge.Models.Bark_Comment;
import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static iDoge.Models.Chat.loadChat;
import static iDoge.Logic.Messenger.Messenger_Page.*;
import static iDoge.Logic.Timeline.Timeline_Page.sortPosts;

public class Chat_Page {
    private static Logger log = LogManager.getLogger(Chat_Page.class.getName());
    public static void loadChatPage(Profile profile, String username) throws IOException {
        Chat chat = loadChat(profile.getUser().getUsername(), username);
        chat.setUnseenMessages(0);
        ArrayList<Bark_Comment> chatList = new ArrayList<>();
        chatList.addAll(chat.getRecievedMessages());
        chatList.addAll(chat.getSentMessages());
        sortPosts(chatList);
        for (int i = chatList.size(); i >= 1; i--){
            Bark_Comment bark = chatList.get(i - 1);
            if (bark.getWriter().equals(chat.getUser1())){
                System.out.println(ConsoleColors.BLUE_BOLD + bark.getWriter() + ":");
                System.out.println(ConsoleColors.BLUE_BOLD + bark.getText());
                System.out.println();
            } else{
                System.out.println(ConsoleColors.GREEN_BOLD + bark.getWriter() + ":");
                System.out.println(ConsoleColors.GREEN_BOLD + bark.getText());
                System.out.println();
            }
        }
        System.out.println(ConsoleColors.PURPLE_BOLD + "CHAT PAGE WITH: " + chat.getUser2());
        log.info("CHAT PAGE WITH: " + chat.getUser2());
        System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new message or press Enter to go back:");
        log.info("Write your new message or press Enter to go back:");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        String i = beholder.nextLine();
        if (!i.equals("")) {
            if (isNotBlaclisted(profile.getUser().getUsername(), username)) {
                if (canCommunicate(profile.getUser().getUsername(), username)) {
                    Bark_Comment barkComment = new Bark_Comment(profile.getUser().getUsername(), i);
                    chat.getSentMessages().add(barkComment);
                    chat.saveChat();
                    try {
                        Chat chat2 = loadChat(chat.getUser2(), chat.getUser1());
                        chat2.getRecievedMessages().add(barkComment);
                        int r = chat2.getUnseenMessages();
                        chat2.setUnseenMessages(r + 1);
                        chat2.saveChat();
                    } catch (IOException e) {
                        Chat chat2 = new Chat(chat.getUser2(), chat.getUser1());
                        chat2.getRecievedMessages().add(barkComment);
                        int r = chat2.getUnseenMessages();
                        chat2.setUnseenMessages(r + 1);
                        chat2.saveChat();
                    }
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Message sent!!!");
                    log.info("Message sent!!!");
                    System.out.println();
                } else {
                    System.out.println(ConsoleColors.RED_BOLD + "Messenger rules error... :(");
                    log.info("Messenger rules error... :(");
                }
                loadChatPage(profile, username);
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Blacklist error... :(");
                log.info("Blacklist error... :(");
                loadMessengerPage(profile);
            }
        } else {
            loadMessengerPage(profile);
        }
    }
}

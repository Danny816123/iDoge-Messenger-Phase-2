package iDoge.Logic.Messenger.AfterMessengerPages;

import com.fasterxml.jackson.databind.ObjectMapper;
import iDoge.Logic.Messenger.AfterMessengerPages.AfterChatListPages.Chat_Page;
import iDoge.Logic.Messenger.Messenger_Page;
import iDoge.Models.Chat;
import iDoge.Logic.Other.ConsoleColors;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Chat_List_Page {
    private static Logger log = LogManager.getLogger(Chat_List_Page.class.getName());
    public static void loadChatListPage(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "CHAT PAGE");
        log.info("CHAT PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter username to open chat:");
        log.info("Enter username to open chat:");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        ArrayList<Chat> chatList = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        File dir = new File("Database/Chats/" + profile.getUser().getUsername());
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                ObjectMapper objectMapper = new ObjectMapper();
                Chat chat = objectMapper.readValue(child, Chat.class);
                log.info("File opened");
                chatList.add(chat);
                log.info("File closed");
            }
            HashMap<Integer, Chat> myMap = new HashMap<>();
            for (Chat chat : chatList){
                myMap.put(chat.getUnseenMessages(), chat);
            }
            Integer[] bArray = new Integer[chatList.size()];
            for (int i = 0; i < chatList.size(); i++) {
                bArray[i] = chatList.get(i).getUnseenMessages();
            }
            Arrays.sort(bArray);
            ArrayList<Integer> times = new ArrayList<>(Arrays.asList(bArray));
            Collections.reverse(times);
            chatList.clear();
            for (int ii : times){
                chatList.add(myMap.get(ii));
            }
            log.info("Chats sorted");
        }
        for (int i = 1; i <= chatList.size(); i++){
            Chat chat = chatList.get(i - 1);
            System.out.println(ConsoleColors.GREEN_BOLD + i + ") " + chat.getUser2());
            System.out.println(ConsoleColors.BLUE_BOLD + chat.getUnseenMessages());
            System.out.println();
            names.add(chat.getUser2());
        }
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (names.contains(i)){
                Chat_Page.loadChatPage(profile, i);
                break;
            } else if (i.equals("0")){
                Messenger_Page.loadMessengerPage(profile);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.info("Invalid command... :(");
                System.out.println();
            }
        }
    }
}

package iDoge.Logic.Messenger.AfterMessengerPages.AfterPacksPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import iDoge.Graphics.Messenger.AfterMessengerPages.AfterChatListPages.Chat_PageController;
import iDoge.Models.Group;
import iDoge.Models.Profile;
import iDoge.Models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static iDoge.Models.Profile.loadProfile;

public class Groups_Page {
    private static Logger log = LogManager.getLogger(Groups_Page.class.getName());
    public static ArrayList<Group> loadGroupsList(Profile profile) throws IOException {
        File dir = new File("Database/Groups/");
        File[] directoryListing = dir.listFiles();
        ArrayList<Group> myList = new ArrayList<>();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                ObjectMapper objectMapper = new ObjectMapper();
                Group myGroup = objectMapper.readValue(child, Group.class);
                log.info("File opened");
                if (myGroup.getUsers().contains(profile.getUser().getUsername())) {
                    myList.add(myGroup);
                }
                log.info("File closed");
            }
        }
        return myList;
    }
}

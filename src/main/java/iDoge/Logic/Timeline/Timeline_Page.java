package iDoge.Logic.Timeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import iDoge.Models.Bark_Comment;
import iDoge.Models.User;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static iDoge.Models.User.listContainsUser;
import static iDoge.Models.Profile.loadProfile;
import static iDoge.Logic.Timeline.Pages.AfterTimelinePages.AfterYourBarksCommentsPages.Bark_Comment_Page2.loadBarkCommentPage2;
import static iDoge.Logic.Timeline.Pages.AfterTimelinePages.Your_Barks_Comments_Page2.loadYourBarksCommentsPage2;

public class Timeline_Page {
    private static Logger log = LogManager.getLogger(Timeline_Page.class.getName());
    public static void loadTimelinePage(Profile profile) throws IOException {
        HashSet<Bark_Comment> postSet = new HashSet<>();
        HashSet<Bark_Comment> postSet2 = new HashSet<>();
        File dir = new File("Database/Profiles/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                ObjectMapper objectMapper = new ObjectMapper();
                Profile profile1 = objectMapper.readValue(child, Profile.class);
                log.info("File opened");
                if (!listContainsUser(profile.getMuteList(), profile1.getUser())) {
                    postSet2.addAll(profile1.getBarksList());
                }
                log.info("File closed");
            }
        }

        for (User user : profile.getFollowingsList()){
            for (Bark_Comment post: postSet2) {
                if (listContainsUser(post.getLikedUsers(), user)) postSet.add(post);
            }
            if (!listContainsUser(profile.getMuteList(), user)) postSet.addAll(loadProfile(user.getUsername()).getBarksList());
        }
        ArrayList<Bark_Comment> postList = new ArrayList<>(postSet);
        sortPosts(postList);
        loadYourBarksCommentsPage2(profile, profile, postList, new ArrayList<>(), postList, 0);
    }

    public static Bark_Comment loadYourBarksCommentsPageFromAddress2(Profile profile, Profile profile2, ArrayList<Bark_Comment> postlist , ArrayList<Integer> address, int s, Bark_Comment b) throws IOException {
        if (0 == address.size()){
            loadYourBarksCommentsPage2(profile, profile2, postlist, address, postlist, 0);
            return null;
        } else if (s == address.size()){
            loadYourBarksCommentsPage2(profile, profile2, postlist, address, b.getCommentsList(), 1);
            return null;
        } else if (s == 0){
            return loadYourBarksCommentsPageFromAddress2(profile, profile2, postlist, address, s + 1, postlist.get(address.get(0)));
        } else {
            return loadYourBarksCommentsPageFromAddress2(profile, profile2, postlist, address, s + 1, b.getCommentsList().get(address.get(s)));
        }
    }

    public static Bark_Comment loadBarkCommentPageFromAddress2(Profile profile, Profile profile2, ArrayList<Bark_Comment> postlist, ArrayList<Integer> address, int s, Bark_Comment b) throws IOException {
        if (address.size() == 1){
            loadBarkCommentPage2(profile, profile2, postlist, address, postlist, address.get(0) + 1);
            return null;
        } else {
            if (s == address.size() - 1) {
                loadBarkCommentPage2(profile, profile2, postlist, address, b.getCommentsList(), address.get(s) + 1);
                return null;
            } else if (s == 0) {
                return loadBarkCommentPageFromAddress2(profile, profile2, postlist, address, s + 1, postlist.get(address.get(0)));
            } else {
                return loadBarkCommentPageFromAddress2(profile, profile2, postlist, address, s + 1, b.getCommentsList().get(address.get(s)));
            }
        }
    }

    public static void sortPosts(ArrayList<Bark_Comment> postList){
        HashMap<String, Bark_Comment> myMap = new HashMap<>();
        for (Bark_Comment barkC : postList){
            myMap.put(barkC.getPublishedDate(), barkC);
        }
        String[] bArray = new String[postList.size()];
        for (int i = 0; i < postList.size(); i++) {
            bArray[i] = postList.get(i).getPublishedDate();
        }
        Arrays.sort(bArray, String.CASE_INSENSITIVE_ORDER);
        ArrayList<String> times = new ArrayList<>(Arrays.asList(bArray));
        Collections.reverse(times);
        postList.clear();
        for (String ii : times){
            postList.add(myMap.get(ii));
        }
        log.info("Posts sorted");
    }
}

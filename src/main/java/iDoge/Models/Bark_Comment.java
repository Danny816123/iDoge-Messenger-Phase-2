package iDoge.Other;

import iDoge.Messenger.Chat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Bark_Comment {
    private static Logger log = LogManager.getLogger(Bark_Comment .class.getName());
    private String writer;
    private String text;
    private String publishedDate;
    private ArrayList<User> likedUsers;
    private ArrayList<Bark_Comment> commentsList;

    public Bark_Comment(){}

    public Bark_Comment(String writer, String text) {
        this.writer = writer;
        this.text = text;
        this.publishedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.likedUsers = new ArrayList<>();
        this.commentsList = new ArrayList<>();
    }

    public String getWriter() {
        return writer;
    }

    public String getText() {
        return text;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public ArrayList<User> getLikedUsers() {
        return likedUsers;
    }

    public ArrayList<Bark_Comment> getCommentsList() {
        return commentsList;
    }

}

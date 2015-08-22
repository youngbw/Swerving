package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by BrentYoung on 4/21/15.
 */
public class SwervePost implements Serializable {

    //TODO:update this class with the correct form of what the picture will be coming from the db as

    public static final String POST_ID = "postID";

    private String id;
    private String userID;
    private String location;
    private String caption;
    private Date postedDate;
    private String imagePath;
    private boolean commentsAllowed;
    private int swerveCount;
    private ArrayList<Comment> comments; //userID or name, comment string


    public SwervePost() {
        this(null);
    }


    public SwervePost(User user) {

        id = "-1";

        if (user != null) {
            userID = user.getUserID();
        }

        comments = new ArrayList<>();

        caption = "None";
        location = "";
        postedDate = new Date();
        imagePath = "";
        commentsAllowed = true;
    }

    public boolean isCommentsAllowed() {
        return commentsAllowed;
    }

    public void setCommentsAllowed(boolean commentsAllowed) {
        this.commentsAllowed = commentsAllowed;
    }

    public String getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }


    public String getLocation() {
        return location;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }


    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getSwerveCount() { return this.swerveCount; }

    public void setSwerveCount(int swerveCount) {
        this.swerveCount = swerveCount;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * This method adds a new Swerve opinion to this swerve post.
     */
    public void addSwerve() {
        swerveCount++;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "Post: " + this.getId().hashCode() % 10 + "\nUser: " + this.getUserID().hashCode() % 10;
    }
}

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BrentYoung on 4/21/15.
 */
public class SwervePost implements Serializable {

    public static final String POST_ID = "postID";

    private String id;
    private String userID;
    private String fromLocation;
    private String toLocation;
    private String caption;
    private Date postedDate;
    private String imagePath;
    private double swervePercentage;
    private boolean isPublic;
    private int swerves;
    private int antiSwerves;
    private List<String> usersCanSee;
    private List<String> groupsCanSee;
    private ArrayList<Comment> comments; //userID or name, comment string


    public SwervePost() {
        this(null);
    }


    public SwervePost(User user) {

        id = "-1";

        if (user != null) {
            userID = user.getUserID();
        }

        usersCanSee = new ArrayList<>();
        groupsCanSee = new ArrayList<>();
        comments = new ArrayList<>();

        caption = "None";
        toLocation = "";
        fromLocation = "";
        postedDate = new Date();
        swerves = 2;
        antiSwerves = 1;
        imagePath = "";
        isPublic = false;
    }


    public String getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public List<String> getUsersCanSee() {
        return usersCanSee;
    }

    public List<String> getGroupsCanSee() {
        return groupsCanSee;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public double getSwervePercentage() {
        return swervePercentage;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void setSwervePercentage(float swervePercentage) {
        this.swervePercentage = swervePercentage;
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
     * @param swerved whether or not the person thought this post should be swerved
     */
    public void addOpinion(boolean swerved) {
        if (swerved) {
            swerves++;
        } else {
            antiSwerves++;
        }

        swervePercentage = (swerves / (swerves + antiSwerves)) * 100.00;
    }

    public void addUserPermission(String userID) {
        if (!usersCanSee.contains(userID)) {
            usersCanSee.add(userID);
        }
    }

    public void addGroupPermission(String groupID) {
        if (!groupsCanSee.contains(groupID)) {
            groupsCanSee.add(groupID);
        }
    }

    public void makePublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "Post: " + this.getId().hashCode() % 10 + "\nUser: " + this.getUserID().hashCode() % 10;
    }
}

package model;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by BrentYoung on 4/21/15.
 */
public class SwervePost {

    private UUID id;
    private UUID userID;
    private String fromLocation;
    private String toLocation;
    private String caption;
    private Date postedDate;
    private Uri imageURI;
    private double swervePercentage;
    private int swerves;
    private int antiSwerves;
    private List<Integer> usersCanSee;
    private List<Integer> groupsCanSee;
    private List<String> comments;


    public SwervePost() {
        this(null);
    }


    public SwervePost(User user) {

        id = UUID.randomUUID();

        if (user != null) {
            userID = user.getUserID();
        }

        usersCanSee = new ArrayList<>();
        groupsCanSee = new ArrayList<>();
        comments = new ArrayList<>();

        caption = "None";
        toLocation = "New York";
        fromLocation = "Seattle";
        postedDate = new Date();
        swerves = 2;
        antiSwerves = 1;
        imageURI = null;
    }


    public UUID getId() {
        return id;
    }

    public UUID getUserID() {
        return userID;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public List<Integer> getUsersCanSee() {
        return usersCanSee;
    }

    public List<Integer> getGroupsCanSee() {
        return groupsCanSee;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void setId(UUID id) {
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

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
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

    public Uri getImageURI() {
        return imageURI;
    }

    public void setImageURI(Uri imageURI) {
        this.imageURI = imageURI;
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

    @Override
    public String toString() {
        return "Post: " + this.getId().hashCode() % 10 + "\nUser: " + this.getUserID().hashCode() % 10;
    }
}

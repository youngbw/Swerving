package model;

/**
 * Created by BrentYoung on 5/7/15.
 */
public class Comment {

    private String user;
    private String comment;

    public Comment() {}

    public void addComment(String user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }
}

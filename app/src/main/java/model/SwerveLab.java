package model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by BrentYoung on 4/21/15.
 * Use background services with database to load swerves into this class
 */
public class SwerveLab {

    private static SwerveLab sSwerveLab;
    private ArrayList<SwervePost> mSwerves;
    private ArrayList<User> mFriends;
    private ArrayList<Group> mGroups;
    private ArrayList<Comment> mComments;
    private Context mAppContext;


    public static SwerveLab getInstance(Context context) {
        if (sSwerveLab == null) {
            sSwerveLab = new SwerveLab(context.getApplicationContext());
        }
        return sSwerveLab;
    }

    private SwerveLab(Context appContext) {

        mAppContext = appContext;
        mSwerves = new ArrayList<>();
        mFriends = new ArrayList<>();
        mGroups = new ArrayList<>();
        mComments = new ArrayList<>();

        //Take this out when services are up and running
        for (int i = 0; i < 100; i++) {
            SwervePost post = new SwervePost(new User());
            Comment comment = new Comment();
            comment.addComment("Jake", "This is an awesome comment");
            post.addComment(comment);
            mComments.add(comment);
            mSwerves.add(post);
        }

    }

    /* Use the following methods to load the proper items from the remote database into the list, and return for display */

    public ArrayList<SwervePost> getSwerves() {
        return mSwerves;
    }

    public ArrayList<User> getFriends() {return mFriends; }

    public ArrayList<Group> getGroups() {return mGroups; }

    public ArrayList<Comment> getComments(String postID) { return mComments; }

    public SwervePost getSwerve(String id) {
        for (SwervePost sp: mSwerves) {
            if (sp.getId().equals(id)) {
                return sp;
            }
        }
        return null;
    }
}

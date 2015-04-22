package model;

import java.util.UUID;

/**
 * Created by BrentYoung on 4/19/15.
 */
public class User {

    public static final String USER_PREFS = "userPreferences";
    public static final String USER_ID = "userID";


    private UUID userID;



    public User() {

        userID = UUID.randomUUID();

    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }
}

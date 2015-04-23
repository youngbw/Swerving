package model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by BrentYoung on 4/19/15.
 */
public class User {

    public static final String USER_PREFS = "userPreferences";
    public static final String USER_ID = "userID";


    private UUID userID;
    private ArrayList<UUID> groups;
    private ArrayList<UUID> friends;
    private String name;


    public User() {

        userID = UUID.randomUUID();
        groups = new ArrayList<>();
        friends = new ArrayList<>();
        name = "";
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void addGroup(UUID groupID) {
        groups.add(groupID);
    }

    public void removeGroup(UUID groupID) {
        groups.remove(groupID);
    }

    public void addFriend(UUID friendID) {
        friends.add(friendID);
    }

    public void removeFriend(UUID friendID) {
        friends.remove(friendID);
    }

    public String getName() {
        if (name.equals("")) {
            return "" + getUserID();
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

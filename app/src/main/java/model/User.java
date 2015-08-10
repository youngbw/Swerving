package model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by BrentYoung on 4/19/15.
 */
public class User {

    public static final String USER_PREFS = "userPreferences";
    public static final String USER_ID = "userID";
    public static final String USER_PHONE = "phone_number";
    public static final String USER_SEX = "sex";
    public static final String USER_HOMETOWN = "hometown";
    public static final String USER_QUESTION = "security_question";
    public static final String USER_ANSWER = "security_answer";



    private String userID;
    private String name;
    private String homeTown;
    private String phone;
    private Character sex;
    private String secQuestion;
    private String secAnswer;



    private ArrayList<UUID> groups;
    private ArrayList<UUID> friends;


    public User() {

        userID = "-1";
        groups = new ArrayList<>();
        friends = new ArrayList<>();
        name = "";
    }


    public String getUserID() {
        return userID;
    }

    public String getSecQuestion() {
        return secQuestion;
    }

    public void setSecQuestion(String secQuestion) {
        this.secQuestion = secQuestion;
    }

    public String getSecAnswer() {
        return secAnswer;
    }

    public void setSecAnswer(String secAnswer) {
        this.secAnswer = secAnswer;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
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

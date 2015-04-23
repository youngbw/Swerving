package model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by BrentYoung on 4/22/15.
 */
public class Group {

    private UUID groupID;

    private UUID creatorID;

    private ArrayList<UUID> members;

    public Group(UUID creator) {
        creatorID = creator;
        groupID = UUID.randomUUID();
        members = new ArrayList<>();
    }

    /**
     * Adds a user to this group.
     * @param memberID the UUID of the user
     */
    public void addMember(UUID memberID) {
        members.add(memberID);
    }

    /**
     * Removes a member from this group.
     * @param memberID the UUID of the user
     */
    public void removeMember(UUID memberID) {
        members.remove(memberID);
    }

}

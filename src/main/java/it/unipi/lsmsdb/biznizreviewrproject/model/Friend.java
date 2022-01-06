package it.unipi.lsmsdb.biznizreviewrproject.model;

public class Friend {
    private String friendId;
    private String friendName;

    public Friend(String friendId, String friendName) {
        this.friendId = friendId;
        this.friendName = friendName;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}

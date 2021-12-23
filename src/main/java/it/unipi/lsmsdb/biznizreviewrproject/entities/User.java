package it.unipi.lsmsdb.biznizreviewrproject.entities;


import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class User {

    @Id
    private Object _id;
    private String user_id;
    public String name;
    public List<Review> reviews = new ArrayList<>();

    public User(Object _id, String user_id, String name) {
        this._id = _id;
        this.user_id = user_id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}

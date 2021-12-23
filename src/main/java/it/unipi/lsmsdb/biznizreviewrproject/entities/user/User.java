package it.unipi.lsmsdb.biznizreviewrproject.entities.user;


import it.unipi.lsmsdb.biznizreviewrproject.entities.review.Review;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String user_id;
    public String name;
    public List<Review> reviews = new ArrayList<>();

    public User(String user_id, String name) {
        this.user_id = user_id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}

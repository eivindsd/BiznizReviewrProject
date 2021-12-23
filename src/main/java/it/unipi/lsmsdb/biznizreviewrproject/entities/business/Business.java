package it.unipi.lsmsdb.biznizreviewrproject.entities.business;

import it.unipi.lsmsdb.biznizreviewrproject.entities.review.Review;

import java.util.ArrayList;
import java.util.List;

public class Business {

    private String business_id;
    public String name;
    private String city;
    private String state;
    private String stars;
    private String categories;
    private List<Review> reviews = new ArrayList<>();

    public Business(String business_id, String name, String city, String state, String stars, String categories) {
        this.business_id = business_id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.stars = stars;
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Business{" +
                "business_id='" + business_id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", stars='" + stars + '\'' +
                ", categories='" + categories + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}


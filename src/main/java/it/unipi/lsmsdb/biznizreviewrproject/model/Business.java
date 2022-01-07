package it.unipi.lsmsdb.biznizreviewrproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("business")
public class Business {
    @Id
    private String _id;

    private String businessId;
    private String name;
    private String country;
    private String city;
    private String state;
    private List<String> topTags;
    private int stars;
    private List<String> categories;
    private List<Review> reviews;

    public Business(String businessId, String name, String country, String city, String state, List<String> topTags, int stars, List<String> categories, List<Review> reviews) {
        this.businessId = businessId;
        this.name = name;
        this.country = country;
        this.city = city;
        this.state = state;
        this.topTags = topTags;
        this.stars = stars;
        this.categories = categories;
        this.reviews = reviews;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getTopTags() {
        return topTags;
    }

    public void setTopTags(List<String> topTags) {
        this.topTags = topTags;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
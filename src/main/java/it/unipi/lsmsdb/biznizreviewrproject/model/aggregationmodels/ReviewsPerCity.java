package it.unipi.lsmsdb.biznizreviewrproject.model.aggregationmodels;

import org.springframework.data.annotation.Id;

public class ReviewsPerCity {

    @Id
    private String city;
    private double avgReviews;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getAvgReviews() {
        return avgReviews;
    }

    public void setAvgReviews(double avgReviews) {
        this.avgReviews = avgReviews;
    }
}

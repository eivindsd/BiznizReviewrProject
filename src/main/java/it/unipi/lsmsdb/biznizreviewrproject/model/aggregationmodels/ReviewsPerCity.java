package it.unipi.lsmsdb.biznizreviewrproject.model.aggregationmodels;

import org.springframework.data.annotation.Id;

public class ReviewsPerCity {

    @Id
    private String city;
    private double avgReviews;
}

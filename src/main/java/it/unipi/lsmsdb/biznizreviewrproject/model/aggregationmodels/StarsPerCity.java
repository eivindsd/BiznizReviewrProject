package it.unipi.lsmsdb.biznizreviewrproject.model.aggregationmodels;

import org.springframework.data.annotation.Id;

public class StarsPerCity {

    @Id
    private String city;
    private double avgStars;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getAvgStars() {
        return avgStars;
    }

    public void setAvgStars(double avgStars) {
        this.avgStars = avgStars;
    }
}

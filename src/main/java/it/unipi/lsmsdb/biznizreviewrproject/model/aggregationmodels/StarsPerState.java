package it.unipi.lsmsdb.biznizreviewrproject.model.aggregationmodels;

import org.springframework.data.annotation.Id;

public class StarsPerState {

    @Id
    private String state;
    private double avgStars;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getAvgStars() {
        return avgStars;
    }

    public void setAvgStars(double avgStars) {
        this.avgStars = avgStars;
    }
}

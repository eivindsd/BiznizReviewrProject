package it.unipi.lsmsdb.biznizreviewrproject.entities;

public class Review {

    private String review_id;
    private int stars;
    private String text;

    public Review(String review_id, int stars, String text) {
        this.review_id = review_id;
        this.stars = stars;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review_id='" + review_id + '\'' +
                ", stars=" + stars +
                ", text='" + text + '\'' +
                '}';
    }
}

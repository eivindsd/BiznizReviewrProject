package it.unipi.lsmsdb.biznizreviewrproject.model;


public class Review {
    private String reviewId;
    private String userId;
    private String businessId;
    private int stars;
    private String text;
    private String business_name;


    public Review(String reviewId, String userId, String businessId, int stars, String text, String business_name) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.businessId = businessId;
        this.stars = stars;
        this.text = text;
        this.business_name = business_name;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }
}


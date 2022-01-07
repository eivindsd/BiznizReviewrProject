package it.unipi.lsmsdb.biznizreviewrproject.model.aggregationmodels;

import org.springframework.data.annotation.Id;

public class StarsPerBusiness {

    @Id
    private String businessId;
    private int amountFive;
    private int amountFour;
    private int amountThree;
    private int amountTwo;
    private int amountOne;

    public StarsPerBusiness(int amountFive, int amountFour, int amountThree, int amountTwo, int amountOne) {
        this.amountFive = amountFive;
        this.amountFour = amountFour;
        this.amountThree = amountThree;
        this.amountTwo = amountTwo;
        this.amountOne = amountOne;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public int getAmountFive() {
        return amountFive;
    }

    public void setAmountFive(int amountFive) {
        this.amountFive = amountFive;
    }

    public int getAmountFour() {
        return amountFour;
    }

    public void setAmountFour(int amountFour) {
        this.amountFour = amountFour;
    }

    public int getAmountThree() {
        return amountThree;
    }

    public void setAmountThree(int amountThree) {
        this.amountThree = amountThree;
    }

    public int getAmountTwo() {
        return amountTwo;
    }

    public void setAmountTwo(int amountTwo) {
        this.amountTwo = amountTwo;
    }

    public int getAmountOne() {
        return amountOne;
    }

    public void setAmountOne(int amountOne) {
        this.amountOne = amountOne;
    }
}

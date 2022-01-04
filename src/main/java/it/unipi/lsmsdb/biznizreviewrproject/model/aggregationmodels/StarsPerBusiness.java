package it.unipi.lsmsdb.biznizreviewrproject.model.aggregationmodels;

import org.springframework.data.annotation.Id;

public class StarsPerBusiness {

    @Id
    private String businessid;
    private int amountFive;
    private int amountFour;
    private int amountThree;
    private int amountTwo;
    private int amountOne;

    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
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

package Entities;

public class BalanceInterest {
    public BalanceInterest(float interestValue)
    {
        this.interestValue = interestValue;
    }

    public float getInterestValue() {
        return interestValue;
    }

    public void setInterestValue(float interestValue) {
        this.interestValue = interestValue;
    }

    private float interestValue;

}

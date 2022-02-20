package Entities;

public class BankLimit {
    private float limitValue;

    public BankLimit(float limitValue)
    {
        this.limitValue = limitValue;
    }

    public float getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(float limitValue) {
        this.limitValue = limitValue;
    }
}

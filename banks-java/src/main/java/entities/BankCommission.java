package entities;

public class BankCommission {
    private float commissionValue;

    public BankCommission(float commissionValue)
    {
        this.commissionValue = commissionValue;
    }

    public float getCommissionValue() {
        return commissionValue;
    }

    public void setCommissionValue(float commissionValue) {
        this.commissionValue = commissionValue;
    }
}

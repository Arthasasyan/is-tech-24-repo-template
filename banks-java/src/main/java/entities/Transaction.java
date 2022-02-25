package entities;

public class Transaction {
    public float moneyBefore;
    public float moneyAfter;
    public int id;
    public boolean isDeclined;

    public Transaction(int id, float moneyBefore, float moneyAfter)
    {
        moneyBefore = moneyBefore;
        moneyAfter = moneyAfter;
        id = id;
        isDeclined = false;
    }

    public float getMoneyBefore() {
        return moneyBefore;
    }

    public float getMoneyAfter() {
        return moneyAfter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDeclined(boolean declined) {
        isDeclined = declined;
    }

    public boolean isDeclined() {
        return isDeclined;
    }
}

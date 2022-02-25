package entities.bank_account;
import entities.*;
import entities.client.Client;
import tools.BanksException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class BaseBankAccountImpl implements BankAccount{

    private final int id;
    private int _lastTransactionId = 0;
    private Client bankClient;
    private float money = 0;
    private BalanceInterest accountBalanceInterest;
    private BankCommission commission;
    private BankLimit limit;
    private LocalDate creationTime;
    private float balanceInterestPerMonth;
    private List<Transaction> transactionHistory;


    public BaseBankAccountImpl(int id, Client bankClient, BalanceInterest accountBalanceInterest, BankCommission commission, BankLimit limit)
    {
        this.id = id;
        this.bankClient = bankClient;
        this.money = 0;
        this.accountBalanceInterest = accountBalanceInterest;
        this.commission = commission;
        this.limit = limit;
        this.creationTime = LocalDate.now();
        transactionHistory = new ArrayList<>() { };
        this.balanceInterestPerMonth = 0;
    }
    public int getId() {
        return id;
    }

    public int get_lastTransactionId() {
        return _lastTransactionId;
    }

    public void set_lastTransactionId(int _lastTransactionId) {
        this._lastTransactionId = _lastTransactionId;
    }

    public Client getBankClient() {
        return bankClient;
    }

    public void setBankClient(Client bankClient) {
        this.bankClient = bankClient;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) throws BanksException {
        this.money = money;
    }

    public BalanceInterest getAccountBalanceInterest() {
        return accountBalanceInterest;
    }

    public void setAccountBalanceInterest(BalanceInterest accountBalanceInterest) {
        this.accountBalanceInterest = accountBalanceInterest;
    }

    public BankCommission getCommission() {
        return commission;
    }

    public void setCommission(BankCommission commission) {
        this.commission = commission;
    }

    public BankLimit getLimit() {
        return limit;
    }

    public void setLimit(BankLimit limit) {
        this.limit = limit;
    }

    public LocalDate getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDate creationTime) {
        this.creationTime = creationTime;
    }

    public float getBalanceInterestPerMonth() {
        return balanceInterestPerMonth;
    }

    public void setBalanceInterestPerMonth(float balanceInterestPerMonth) {
        this.balanceInterestPerMonth = balanceInterestPerMonth;
    }

    @Override
    public Transaction addMoneyToAccount(float money) throws BanksException {
        var transaction = new Transaction(_lastTransactionId++, this.money, this.money + money);
        this.transactionHistory.add(transaction);
        this.money += money;
        return transaction;
    }
    @Override
    public Transaction getMoneyFromAccount(float money) throws BanksException {
        var transaction = new Transaction(_lastTransactionId++, this.money, this.money - money);
        this.transactionHistory.add(transaction);
        if (this.bankClient.isFishy() && money > this.limit.getLimitValue())
            throw new BanksException("You have exceeded your funds limit");
        this.money -= money;
        return transaction;
    }

    public Transaction declineTransaction(int id) throws BanksException {
        Transaction transaction = null;
        for (Transaction trans : this.transactionHistory)
        {
            if (trans.id == id) {
                transaction = trans;
            }
        }
        if (transaction == null)
        {
            throw new BanksException("We can't find transaction!");
        }
            else if (transaction.isDeclined()) {
            throw new BanksException("This transaction already declined!");
        }

        transaction.setDeclined(true);
        money = transaction.getMoneyBefore();
        return transaction;
    }

}

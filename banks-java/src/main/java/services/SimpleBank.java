package services;

import entities.BalanceInterest;
import entities.bank_account.*;
import entities.BankCommission;
import entities.BankLimit;
import entities.client.Client;
import tools.BanksException;

import java.time.LocalDate;
import java.util.ArrayList;

public class SimpleBank
{
    public String bankName;
    public BalanceInterest bankBalanceInterest;
    public BalanceInterest bankDepositBalanceInterest;
    public BankCommission bankCommission;
    public BankLimit creditLimit;
    public BankLimit bankAccountLimit;
    public int depositAccountDuration;
    private ArrayList<Client> bankClients;
    private ArrayList<BaseBankAccountImpl> accounts;
    private final int id;
    private int _lastAccountId = 0;


    public SimpleBank(
            int id,
            String bankName,
            BalanceInterest bankBalanceInterest,
            BalanceInterest bankDepositBalanceInterest,
            entities.BankCommission bankCommission,
            BankLimit bankLimit,
            BankLimit creditLimit,
            int depositAccountDuration)
    {
        this.id = id;
        this.bankName = bankName;
        this.bankBalanceInterest = bankBalanceInterest;
        this.bankDepositBalanceInterest = bankDepositBalanceInterest;
        this.bankCommission = bankCommission;
        this.bankAccountLimit = bankLimit;
        this.creditLimit = creditLimit;
        this.depositAccountDuration = depositAccountDuration;
        this.bankClients = new ArrayList<Client>();
        this.accounts = new ArrayList<BaseBankAccountImpl>();
    }
    public int get_lastAccountId() {
        return _lastAccountId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BalanceInterest getBankBalanceInterest() {
        return bankBalanceInterest;
    }

    public void setBankBalanceInterest(BalanceInterest bankBalanceInterest) {
        this.bankBalanceInterest = bankBalanceInterest;
    }

    public BalanceInterest getBankDepositBalanceInterest() {
        return bankDepositBalanceInterest;
    }

    public void setBankDepositBalanceInterest(BalanceInterest bankDepositBalanceInterest) {
        this.bankDepositBalanceInterest = bankDepositBalanceInterest;
    }

    public BankCommission getBankCommission() {
        return bankCommission;
    }

    public void setBankCommission(BankCommission bankCommission) {
        this.bankCommission = bankCommission;
    }

    public BankLimit getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BankLimit creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BankLimit getBankAccountLimit() {
        return bankAccountLimit;
    }

    public void setBankAccountLimit(BankLimit bankAccountLimit) {
        this.bankAccountLimit = bankAccountLimit;
    }

    public int getDepositAccountDuration() {
        return depositAccountDuration;
    }

    public void setDepositAccountDuration(int depositAccountDuration) {
        this.depositAccountDuration = depositAccountDuration;
    }

    public ArrayList<Client> getBankClients() {
        return bankClients;
    }

    public void setBankClients(ArrayList<Client> bankClients) {
        this.bankClients = bankClients;
    }

    public ArrayList<BaseBankAccountImpl> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<BaseBankAccountImpl> accounts) {
        this.accounts = accounts;
    }

    public void set_lastAccountId(int _lastAccountId) {
        this._lastAccountId = _lastAccountId;
    }

    public void transferMoney(int firstAccountId, int secondAccountId, float value) throws BanksException {
        BaseBankAccountImpl firstAccount = findAccountById(firstAccountId);
        BaseBankAccountImpl secondAccount = findAccountById(secondAccountId);
        firstAccount.getMoneyFromAccount(value);
        secondAccount.addMoneyToAccount(value);
    }

    public void sendNotificationsForClients(String message)
    {
        ArrayList<Client> clients = new ArrayList<>();
        for (Client client : bankClients)
        {
            if (client.isNotifications())
                clients.add(client);
        }
        for (Client client : clients)
        {
            ArrayList<String> clientNotifications = client.getNotificationsHistory();
            clientNotifications.add(message);
            client.setNotificationsHistory(clientNotifications);
        }
    }

    public void changeBalanceInterest(float value)
    {
        this.bankBalanceInterest.setInterestValue(value);
        sendNotificationsForClients("Now we have new balance interest!");
    }

    public void changeDepositBalanceInterest(float value)
    {
        this.bankDepositBalanceInterest.setInterestValue(value);
        sendNotificationsForClients("Now we have new deposit balance interest!");
    }

    public void changeBankCommission(float value)
    {
        this.bankCommission.setCommissionValue(value);
        sendNotificationsForClients("Now we have new credit commission!");
    }

    public void changeBankCreditLimit(float value)
    {
        this.creditLimit.setLimitValue(value);
        sendNotificationsForClients("Now we have new credit limit!");
    }

    public void changeBankAccountLimit(float value)
    {
        this.bankAccountLimit.setLimitValue(value);
        sendNotificationsForClients("Now we have new limit for your fishy accounts!");
    }

    public void changeDepositAccountDuration(int value)
    {
        this.setDepositAccountDuration(value);
        sendNotificationsForClients("Now we have new limit for your fishy accounts!");
    }

    public Client addBankClient(Client client)
    {
        bankClients.add(client);
        return client;
    }

    public DebitAccountImpl addDebitAccountForClient(Client client)
    {
        var clientAccount = new DebitAccountImpl(_lastAccountId++, client, bankBalanceInterest, bankCommission, bankAccountLimit);
        accounts.add(clientAccount);
        return clientAccount;
    }

    public DepositAccountImpl addDepositAccountForClient(Client client, float startMoney) throws BanksException {
        var clientAccount = new DepositAccountImpl(_lastAccountId++, client, bankDepositBalanceInterest, bankCommission, bankAccountLimit, LocalDate.now().plusMonths(depositAccountDuration), startMoney);
        accounts.add(clientAccount);
        return clientAccount;
    }

    public CreditAccountImpl addCreditAccountForClient(Client client)
    {
        var clientAccount = new CreditAccountImpl(_lastAccountId++, client, new BalanceInterest(0), bankCommission, bankAccountLimit);
        accounts.add(clientAccount);
        return clientAccount;
    }

    public void addAccountInterests()
    {
        for (BaseBankAccountImpl account : accounts)
        {
            account.setBalanceInterestPerMonth(account.getMoney() * account.getAccountBalanceInterest().getInterestValue());
        }
    }

    public void addAccountInterestOnAccount() throws BanksException {
        for (BaseBankAccountImpl account : accounts)
        {
            account.addMoneyToAccount(account.getBalanceInterestPerMonth());
            account.setBalanceInterestPerMonth(0);
        }
    }

    private BaseBankAccountImpl findAccountById(int accountId) throws BanksException {
        BaseBankAccountImpl account = null;
        for (BaseBankAccountImpl account1 : accounts)
        {
            if (account1.getId() == accountId)
            {
                account = account1;
                break;
            }
        }
        if (account != null)
        {
            return account;
        }
        else
        {
            throw new BanksException("We cannot find account with this id!");
        }
    }
}

package entities.bank_account;

import entities.BalanceInterest;
import entities.BankCommission;
import entities.BankLimit;
import entities.client.Client;
import tools.BanksException;

public class DebitAccountImpl extends BaseBankAccountImpl {

    public DebitAccountImpl
            (
                    int id,
                    Client bankClient,
                    BalanceInterest accountBalanceInterest,
                    BankCommission commission,
                    BankLimit limit) {
        super(id, bankClient, accountBalanceInterest, commission, limit);
    }

    @Override
    public void setMoney(float money) throws BanksException {

        if (money < 0)
            throw new BanksException("Money on debit account cannot be < 0");
        super.setMoney(money);
    }
}
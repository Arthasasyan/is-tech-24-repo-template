package Entities.BankAccount;

import Entities.BalanceInterest;
import Entities.BankCommission;
import Entities.BankLimit;
import Entities.Client;
import Tools.BanksException;

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
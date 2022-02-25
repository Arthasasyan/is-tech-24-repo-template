package entities.bank_account;
import entities.*;
import entities.client.Client;
import tools.BanksException;

public class CreditAccountImpl extends BaseBankAccountImpl {
    public CreditAccountImpl(
            int id,
            Client bankClient,
            BalanceInterest accountBalanceInterest,
            BankCommission commission,
            BankLimit limit)
    {
        super(id, bankClient, accountBalanceInterest, commission, limit);
    }

    @Override
    public Transaction addMoneyToAccount(float money) throws BanksException {
        return money < 0 ? super.addMoneyToAccount(money - (this.getCommission().getCommissionValue() * money)) : super.addMoneyToAccount(money);
    }

    @Override
    public  Transaction getMoneyFromAccount(float money) throws BanksException
    {
        if (this.getMoney() < 0 && this.getMoney() - money > -this.getLimit().getLimitValue())
        {
            return super.addMoneyToAccount(money - (this.getLimit().getLimitValue() * money));
        }
        else if (this.getMoney() - money <= -this.getLimit().getLimitValue())
        {
            throw new BanksException("You have reached your credit limit");
        }
        else
        {
            return super.getMoneyFromAccount(money);
        }
    }
}

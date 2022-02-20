package Entities.BankAccount;
import Entities.*;
import Tools.BanksException;

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
    public Transaction AddMoneyToAccount(float money) throws BanksException {
        return money < 0 ? super.AddMoneyToAccount(money - (this.getCommission().getCommissionValue() * money)) : super.AddMoneyToAccount(money);
    }

    @Override
    public  Transaction GetMoneyFromAccount(float money) throws BanksException
    {
        if (this.getMoney() < 0 && this.getMoney() - money > -this.getLimit().getLimitValue())
        {
            return super.AddMoneyToAccount(money - (this.getLimit().getLimitValue() * money));
        }
        else if (this.getMoney() - money <= -this.getLimit().getLimitValue())
        {
            throw new BanksException("You have reached your credit limit");
        }
        else
        {
            return super.GetMoneyFromAccount(money);
        }
    }
}

package Entities.BankAccount;

import Entities.*;
import Tools.BanksException;

import java.time.LocalDate;

public class DepositAccountImpl extends BaseBankAccountImpl
{
private LocalDate durationEnd;

    public DepositAccountImpl(
        int id,
        Client bankClient,
        BalanceInterest accountBalanceInterest,
        BankCommission commission,
        BankLimit limit,
        LocalDate durationEnd,
        float startMoney) throws BanksException
    {
        super(id, bankClient, accountBalanceInterest, commission, limit);
        this.durationEnd = durationEnd;
        super.setMoney(startMoney);
    }

    public LocalDate getDurationEnd() {
        return durationEnd;
    }

    public void setDurationEnd(LocalDate durationEnd) {
        this.durationEnd = durationEnd;
    }

    @Override
    public Transaction AddMoneyToAccount(float money) throws BanksException {
        if (this.getDurationEnd().isAfter(this.getCreationTime()))
        {
        throw new BanksException("Your deposit account duration is not end.");
        }
        else
        {
        return super.AddMoneyToAccount(money);
        }
    }

    @Override
    public Transaction GetMoneyFromAccount(float money) throws BanksException {
        if (this.getDurationEnd().isAfter(this.getCreationTime()))
        {
        throw new BanksException("Your deposit account duration is not end.");
        }
        else
        {
        return super.GetMoneyFromAccount(money);
        }
    }
}
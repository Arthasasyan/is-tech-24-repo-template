package entities.bank_account;

import entities.*;
import entities.client.Client;
import tools.BanksException;

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
    public Transaction addMoneyToAccount(float money) throws BanksException {
        if (this.getDurationEnd().isAfter(this.getCreationTime()))
        {
        throw new BanksException("Your deposit account duration is not end.");
        }
        else
        {
        return super.addMoneyToAccount(money);
        }
    }

    @Override
    public Transaction getMoneyFromAccount(float money) throws BanksException {
        if (this.getDurationEnd().isAfter(this.getCreationTime()))
        {
        throw new BanksException("Your deposit account duration is not end.");
        }
        else
        {
        return super.getMoneyFromAccount(money);
        }
    }
}
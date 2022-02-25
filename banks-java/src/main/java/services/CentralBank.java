package services;
import entities.BankLimit;
import entities.BalanceInterest;
import entities.BankCommission;
import tools.BanksException;

import java.util.ArrayList;

public class CentralBank
{
    private  int BankLimit = 0;
    private int _lastBankId = 0;
    private ArrayList<SimpleBank> systemBanks;

    public CentralBank()
    {
        systemBanks = new ArrayList<SimpleBank>();
    }


    public SimpleBank CreateBank(
            String bankName,
            float bankBalanceInterest,
            float bankDepositInterest,
            float bankCommission,
            float bankLimit,
            float bankCreditLimit,
            int depositAccountDuration)
    {
        var bank = new SimpleBank(
                _lastBankId++,
                bankName,
                new BalanceInterest(bankBalanceInterest),
                new BalanceInterest(bankDepositInterest),
                new BankCommission(bankCommission),
                new BankLimit(bankLimit),
                new BankLimit(bankCreditLimit),
                depositAccountDuration);
        systemBanks.add(bank);
        return bank;
    }

    public void runTimeMachine(int dayNumber) throws BanksException {
        for (int i = 0; i < dayNumber; i++)
        {
            for (SimpleBank bank : systemBanks) bank.addAccountInterests();
            if (i % BankLimit != 0) continue;
            {
                for (SimpleBank bank : systemBanks) bank.addAccountInterestOnAccount();
            }
        }
    }

    public SimpleBank findBankByName(String bankName)
    {
        for (SimpleBank bank : systemBanks)
        {
            if (bank.getBankName() == bankName)
            {
                return bank;
            }
        }
        return null;
    }
}


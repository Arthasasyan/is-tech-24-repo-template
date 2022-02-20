package Services;
import Entities.BankLimit;
import Entities.BalanceInterest;
import Entities.BankCommission;
import Tools.BanksException;

import java.util.ArrayList;

public class CentralBank
{
    private  int BankLimit = 0;
    private int _lastBankId = 0;
    private ArrayList<SimpleBank> systemBanks;

    public CentralBank()
    {
        ArrayList<SimpleBank> SystemBanks = new ArrayList<SimpleBank>();
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

    public void RunTimeMachine(int dayNumber) throws BanksException {
        for (int i = 0; i < dayNumber; i++)
        {
            for (SimpleBank bank : systemBanks) bank.AddAccountInterests();
            if (i % BankLimit != 0) continue;
            {
                for (SimpleBank bank : systemBanks) bank.AddAccountInterestOnAccount();
            }
        }
    }

    public SimpleBank FindBankByName(String bankName)
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


package Entities.BankAccount;

import Entities.Transaction;
import Tools.BanksException;

public interface BankAccount {

    Transaction AddMoneyToAccount(float money) throws BanksException;

    Transaction GetMoneyFromAccount(float money) throws BanksException;

}

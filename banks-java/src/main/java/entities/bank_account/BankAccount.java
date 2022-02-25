package entities.bank_account;

import entities.Transaction;
import tools.BanksException;

public interface BankAccount {

    Transaction addMoneyToAccount(float money) throws BanksException;

    Transaction getMoneyFromAccount(float money) throws BanksException;

}

import models.BankService;
import models.account.factory_method.DepositAccountCreator;
import models.central_bank.CentralBank;

import java.util.UUID;

public class Main {
	public static void main(String[] args) {
		BankService bankService = new BankService();
		bankService.Start();
		CentralBank centralBank = new CentralBank();
		centralBank.openAccount("d", UUID.randomUUID(), new DepositAccountCreator());
	}
}
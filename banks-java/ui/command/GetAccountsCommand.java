package ui.command;

import models.account.Account;
import models.central_bank.CentralBank;
import ui.UserInterface;

public class GetAccountsCommand extends NonTerminatingCommand {
	private final CentralBank centralBank;

	public GetAccountsCommand(UserInterface userInterface, CentralBank centralBank) {
		super(userInterface);
		this.centralBank = centralBank;
	}

	@Override
	public void run() {
		for (Account account : centralBank.getAllAccounts()) {
			getUserInterface().writeReturnedValue("Account id", account.getId());
			getUserInterface().writeReturnedValue("balance", account.getBalance());
			getUserInterface().writeReturnedValue("type", account.getBankAccountType());
			getUserInterface().writeReturnedValue("client name", account.getClient().getName());
			getUserInterface().writeReturnedValue("surname", account.getClient().getName());
		}
	}
}

package ui.command;

import models.account.factory_method.AccountCreator;
import models.account.factory_method.CreditAccountCreator;
import models.account.factory_method.DebitAccountCreator;
import models.account.factory_method.DepositAccountCreator;
import models.central_bank.CentralBank;
import ui.UserInterface;

import java.util.Locale;
import java.util.UUID;

public class OpenAccountCommand extends NonTerminatingCommand {
	private final CentralBank centralBank;

	public OpenAccountCommand(UserInterface userInterface, CentralBank centralBank) {
		super(userInterface);
		this.centralBank = centralBank;
	}

	@Override
	public void run() {
		String bankName = getUserInterface().readStringValue("bank name");
		String stringClientId = getUserInterface().readStringValue("client id");
		UUID clientId = UUID.fromString(stringClientId);
		String accountType = getUserInterface().readStringValue("account type (debit, credit, deposit)");
		AccountCreator accountCreator = null;
		switch (accountType.toLowerCase(Locale.ROOT)) {
			case "debit":
				accountCreator = new DebitAccountCreator();
			case "credit":
				accountCreator = new CreditAccountCreator();
			case "deposit":
				accountCreator = new DepositAccountCreator();
			default:
				break;
		}
		getUserInterface().writeReturnedValue(
				"Account id",
				centralBank.openAccount(bankName, clientId, new DepositAccountCreator())
		);
	}
}

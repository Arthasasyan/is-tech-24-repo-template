package ui.command;

import models.central_bank.CentralBank;
import models.central_bank.Transaction;
import ui.UserInterface;

public class GetTransactionsCommand extends NonTerminatingCommand {
	private final CentralBank centralBank;

	public GetTransactionsCommand(UserInterface userInterface, CentralBank centralBank) {
		super(userInterface);
		this.centralBank = centralBank;
	}

	@Override
	public void run() {
		for (Transaction transaction : centralBank.getTransactions()) {
			getUserInterface().writeReturnedValue("Transaction id", transaction.getId());
			getUserInterface().writeReturnedValue("from id", transaction.getFrom());
			getUserInterface().writeReturnedValue("to id", transaction.getTo());
			getUserInterface().writeReturnedValue("amount id", transaction.getAmount());
		}
	}
}

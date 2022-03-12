package ui.command;

import models.central_bank.CentralBank;
import models.central_bank.Transaction;
import ui.UserInterface;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactCommand extends NonTerminatingCommand {
	private final CentralBank centralBank;

	public TransactCommand(UserInterface userInterface, CentralBank centralBank) {
		super(userInterface);
		this.centralBank = centralBank;
	}

	@Override
	public void run() {
		String string_from = getUserInterface().readStringValue("from id (can be empty)");
		UUID from_id = string_from.equals("") ? null : UUID.fromString(string_from);
		String string_to = getUserInterface().readStringValue("to id (can be empty)");
		UUID to_id = string_to.equals("") ? null : UUID.fromString(string_to);
		BigDecimal amount = getUserInterface().readBigDecimalValue("amount");
		Transaction transaction = new Transaction(from_id, to_id, amount);
		getUserInterface().writeReturnedValue(
				"transaction id",
				centralBank.transact(transaction)
		);
	}
}

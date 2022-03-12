package ui.command;

import models.central_bank.CentralBank;
import ui.UserInterface;

import java.util.UUID;

public class CancelTransactionCommand extends NonTerminatingCommand {
	private final CentralBank centralBank;

	public CancelTransactionCommand(UserInterface userInterface, CentralBank centralBank) {
		super(userInterface);
		this.centralBank = centralBank;
	}

	@Override
	public void run() {
		String stringTransactionId = getUserInterface().readStringValue("transaction id");
		UUID transactionId = UUID.fromString(stringTransactionId);
		centralBank.cancelTransaction(transactionId);
		getUserInterface().writeMessage("Canceled successfully");
	}
}

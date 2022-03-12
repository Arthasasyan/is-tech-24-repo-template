package ui.command;

import ui.UserInterface;

public class HelpCommand extends NonTerminatingCommand {
	public HelpCommand(UserInterface userInterface) {
		super(userInterface);
	}

	@Override
	public void run() {
		getUserInterface().writeMessage("quit, q, term, terminate - QuitCommand");
		getUserInterface().writeMessage("?, ??, ??? - HelpCommand");
		getUserInterface().writeMessage("ob, open bank - OpenBankCommand");
		getUserInterface().writeMessage("ubm, update bank model - UpdateBankModelCommand");
		getUserInterface().writeMessage("ucpi, update client personal info - UpdateClientPersonalInfoCommand");
		getUserInterface().writeMessage("ucd, update current date - UpdateCurrentDateCommand");
		getUserInterface().writeMessage("ct, cancel transaction - CancelTransactionCommand");
		getUserInterface().writeMessage("gt, get transactions - GetTransactionsCommand");
		getUserInterface().writeMessage("gb, get banks - GetBanksCommand");
		getUserInterface().writeMessage("ga, get accounts - GetAccountsCommand");
		getUserInterface().writeMessage("gc, get clients - GetClientsCommand");
	}

}

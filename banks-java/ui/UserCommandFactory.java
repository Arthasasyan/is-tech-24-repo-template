package ui;

import models.central_bank.CentralBank;
import org.jetbrains.annotations.NotNull;
import ui.command.*;

import java.util.Locale;

public class UserCommandFactory {
	private final UserInterface userInterface;
	private final CentralBank centralBank;

	public UserCommandFactory(
			@NotNull UserInterface userInterface,
			@NotNull CentralBank centralBank
	) {
		this.centralBank = centralBank;
		this.userInterface = userInterface;
	}

	public UserCommand getCommand(String input) {
		switch (input.toLowerCase(Locale.ROOT)) {
			case "quit", "q", "term", "terminate":
				return new QuitCommand(userInterface);
			case "?", "??", "???":
				return new HelpCommand(userInterface);
			case "ob", "open bank":
				return new OpenBankCommand(userInterface, centralBank);
			case "bc", "become client":
				return new BecomeClientCommand(userInterface, centralBank);
			case "oa", "open account":
				return new OpenAccountCommand(userInterface, centralBank);
			case "ubm", "update bank model":
				return new UpdateBankModelCommand(userInterface, centralBank);
			case "ucpi", "update client personal info":
				return new UpdateClientInfoCommand(userInterface, centralBank);
			case "ucd", "update current date":
				return new UpdateCurrentDate(userInterface, centralBank);
			case "t", "transact":
				return new TransactCommand(userInterface, centralBank);
			case "ct", "cancel transaction":
				return new CancelTransactionCommand(userInterface, centralBank);
			case "gt", "get transactions":
				return new GetTransactionsCommand(userInterface, centralBank);
			case "gb", "get banks":
				return new GetBanksCommand(userInterface, centralBank);
			case "ga", "get accounts":
				return new GetAccountsCommand(userInterface, centralBank);
			case "gc", "get clients":
				return new GetClients(userInterface, centralBank);
			default:
				return new UnknownCommand(userInterface);
		}
	}
}
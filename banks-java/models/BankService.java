package models;

import models.central_bank.CentralBank;
import ui.ConsoleUserInterface;
import ui.UserCommandFactory;
import ui.UserInterface;
import ui.command.UserCommand;

public class BankService {
	private final UserInterface userInterface = new ConsoleUserInterface();
	private final CentralBank centralBank = new CentralBank();
	private final UserCommandFactory userCommandFactory = new UserCommandFactory(userInterface, centralBank);

	public void Start() {
		userInterface.writeMessage("Hello");
		userInterface.writeMessage("Enter ? for command list");
		boolean shouldContinue = true;
		while (shouldContinue) {
			String input = userInterface.readStringValue("command");
			UserCommand userCommand = userCommandFactory.getCommand(input);
			try {
				userCommand.run();
			}

			catch (Exception exception) {
				userInterface.writeWarning(exception.getMessage());
			}
			shouldContinue = userCommand.isShouldContinue();
		}
	}
}

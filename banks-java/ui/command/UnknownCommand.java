package ui.command;

import ui.UserInterface;

public class UnknownCommand extends NonTerminatingCommand {
	public UnknownCommand(UserInterface userInterface) {
		super(userInterface);
	}

	@Override
	public void run() {
		getUserInterface().writeWarning("Command not found, try again");
	}
}

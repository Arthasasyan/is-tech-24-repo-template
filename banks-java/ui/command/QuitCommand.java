package ui.command;

import ui.UserInterface;

public class QuitCommand extends TerminatingCommand {
	public QuitCommand(UserInterface userInterface) {
		super(userInterface);
	}

	@Override
	public void run() {
		getUserInterface().writeMessage("Thanks for using, bye");
	}

}

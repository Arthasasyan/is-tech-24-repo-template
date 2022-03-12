package ui.command;

import ui.UserInterface;

public abstract class TerminatingCommand extends UserCommand {
	public TerminatingCommand(UserInterface userInterface) {
		super(false, userInterface);
	}
}

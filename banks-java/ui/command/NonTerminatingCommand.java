package ui.command;

import ui.UserInterface;

public abstract class NonTerminatingCommand extends UserCommand {
	public NonTerminatingCommand(UserInterface userInterface) {
		super(true, userInterface);
	}
}

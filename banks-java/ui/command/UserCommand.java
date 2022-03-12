package ui.command;

import org.jetbrains.annotations.NotNull;
import ui.UserInterface;

public abstract class UserCommand {
	private boolean shouldContinue;
	private UserInterface userInterface;

	public UserCommand(
			boolean shouldContinue,
			@NotNull UserInterface userInterface) {
		this.shouldContinue = shouldContinue;
		this.userInterface = userInterface;
	}

	public abstract void run();

	public boolean isShouldContinue() {
		return shouldContinue;
	}

	public UserInterface getUserInterface() {
		return userInterface;
	}
}

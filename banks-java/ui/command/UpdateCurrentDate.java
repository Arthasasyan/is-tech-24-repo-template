package ui.command;

import models.central_bank.CentralBank;
import ui.UserInterface;

import java.time.LocalDate;

public class UpdateCurrentDate extends NonTerminatingCommand {
	private final CentralBank centralBank;

	public UpdateCurrentDate(UserInterface userInterface, CentralBank centralBank) {
		super(userInterface);
		this.centralBank = centralBank;
	}

	@Override
	public void run() {
		long year = getUserInterface().readLongValue("year");
		long month = getUserInterface().readLongValue("month");
		long day = getUserInterface().readLongValue("day");
		LocalDate newCurrentDate = LocalDate.of((int) year, (int) month, (int) day);
		centralBank.updateCurrentDate(newCurrentDate);
		getUserInterface().writeMessage("Updated successfully");
	}
}

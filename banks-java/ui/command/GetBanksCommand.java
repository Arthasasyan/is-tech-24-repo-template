package ui.command;

import models.central_bank.CentralBank;
import ui.UserInterface;

public class GetBanksCommand extends NonTerminatingCommand {
	private final CentralBank centralBank;

	public GetBanksCommand(UserInterface userInterface, CentralBank centralBank) {
		super(userInterface);
		this.centralBank = centralBank;
	}

	@Override
	public void run() {
		centralBank.getBanks().stream().
				forEach(bank -> getUserInterface().writeReturnedValue("Bank name", bank.getName()));
	}
}

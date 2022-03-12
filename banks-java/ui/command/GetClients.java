package ui.command;

import models.central_bank.CentralBank;
import models.client.Client;
import ui.UserInterface;

public class GetClients extends NonTerminatingCommand {
	private final CentralBank centralBank;

	public GetClients(UserInterface userInterface, CentralBank centralBank) {
		super(userInterface);
		this.centralBank = centralBank;
	}

	@Override
	public void run() {
		for (Client client : centralBank.getAllClients()) {
			getUserInterface().writeReturnedValue("Client id", client.getId());
			getUserInterface().writeReturnedValue("name", client.getName());
			getUserInterface().writeReturnedValue("surname", client.getSurname());
		}
	}
}

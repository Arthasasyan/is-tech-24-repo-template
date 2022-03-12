package ui.command;

import models.central_bank.CentralBank;
import models.client.additional_info.Address;
import models.client.additional_info.PassportData;
import ui.UserInterface;

import java.util.UUID;

public class UpdateClientInfoCommand extends NonTerminatingCommand {
	private final CentralBank centralBank;

	public UpdateClientInfoCommand(UserInterface userInterface, CentralBank centralBank) {
		super(userInterface);
		this.centralBank = centralBank;
	}

	@Override
	public void run() {
		String bankName = getUserInterface().readStringValue("bank name");
		String stringClientId = getUserInterface().readStringValue("client id");
		UUID clientId = UUID.fromString(stringClientId);
		String city = getUserInterface().readStringValue("city");
		String street = getUserInterface().readStringValue("street");
		String houseNumber = getUserInterface().readStringValue("house number");
		String passportNumber = getUserInterface().readStringValue("hyphenated passport number");
		Address newAddress =  new Address(city, street, houseNumber);
		PassportData newPassportData = new PassportData(passportNumber);
		centralBank.updateClientPersonalInfo(bankName, clientId, newAddress, newPassportData);
		getUserInterface().writeMessage("Successfully updated");
	}
}

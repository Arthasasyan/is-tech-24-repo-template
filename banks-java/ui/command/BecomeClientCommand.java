package ui.command;

import models.central_bank.CentralBank;
import models.client.additional_info.Address;
import models.client.additional_info.PassportData;
import models.client.factory_method.ClientCreator;
import models.client.factory_method.IndividualCustomerCreator;
import ui.UserInterface;

public class BecomeClientCommand extends NonTerminatingCommand {
	private final CentralBank centralBank;

	public BecomeClientCommand(UserInterface userInterface, CentralBank centralBank) {
		super(userInterface);
		this.centralBank = centralBank;
	}

	@Override
	public void run() {
		String bankName = getUserInterface().readStringValue("bank name");
		String clientName = getUserInterface().readStringValue("client name");
		String surname = getUserInterface().readStringValue("surname");
		String city = getUserInterface().readStringValue("city");
		String street = getUserInterface().readStringValue("street");
		String houseNumber = getUserInterface().readStringValue("house number");
		String passportNumber = getUserInterface().readStringValue("hyphenated passport number");
		Address address =  new Address(city, street, houseNumber);
		PassportData passportData = new PassportData(passportNumber);
		ClientCreator clientCreator = new IndividualCustomerCreator(
				clientName,
				surname,
				address,
				passportData
		);
		getUserInterface().writeReturnedValue(
				"Client id",
				centralBank.becomeClient(bankName, clientCreator)
		);
	}
}

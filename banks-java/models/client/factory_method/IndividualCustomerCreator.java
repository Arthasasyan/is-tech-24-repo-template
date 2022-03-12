package models.client.factory_method;

import models.client.IndividualCustomer;
import models.client.additional_info.Address;
import models.client.additional_info.PassportData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class IndividualCustomerCreator extends BankClientCreator {
	public IndividualCustomerCreator(
			@NotNull String name,
			@NotNull String surname,
			@Nullable Address address,
			@Nullable PassportData passportData
	) {
		super(name, surname, address, passportData);
	}

	@Override
	public IndividualCustomer create() {
		return new IndividualCustomer(
				UUID.randomUUID(),
				getName(),
				getSurname(),
				getAddress(),
				getPassportData()
		);
	}
}

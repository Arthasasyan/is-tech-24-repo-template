package models.client;

import models.client.additional_info.Address;
import models.client.additional_info.PassportData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class IndividualCustomer extends BankClient {
	public IndividualCustomer(
			@NotNull UUID id,
			@NotNull String name,
			@NotNull String middleName,
			@Nullable Address address,
			@Nullable PassportData passportData
	) {
		super(id, name, middleName, address, passportData);
	}

	@Override
	public boolean isSuspicious() {
		return getAddress() == null || getPassportData() == null;
	}

	public IndividualCustomer updatePersonalInfo(
			@Nullable PassportData newPassportData,
			@Nullable Address newAddress
	) {
		return new IndividualCustomer(
				getId(),
				getName(),
				getSurname(),
				newAddress,
				newPassportData
		);
	}
}

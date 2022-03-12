package models.client.factory_method;

import models.client.additional_info.Address;
import models.client.BankClient;
import models.client.additional_info.PassportData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BankClientCreator implements ClientCreator {
	private final String name;
	private final String surname;
	private final Address address;
	private final PassportData passportData;

	public BankClientCreator(
			@NotNull String name,
			@NotNull String surname,
			@Nullable Address address,
			@Nullable PassportData passportData
	) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.passportData = passportData;
	}

	public abstract BankClient create();

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	@Nullable
	public Address getAddress() {
		return address;
	}

	@Nullable
	public PassportData getPassportData() {
		return passportData;
	}
}

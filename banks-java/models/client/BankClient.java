package models.client;

import models.client.additional_info.Address;
import models.client.additional_info.PassportData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public abstract class BankClient implements Client {
	private final UUID id;
	private final String name;
	private final String surname;
	private final Address address;
	private final PassportData passportData;

	public BankClient(
			@NotNull UUID id,
			@NotNull String name,
			@NotNull String surname,
			@Nullable Address address,
			@Nullable PassportData passportData) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.passportData = passportData;
	}

	public abstract boolean isSuspicious();
	public abstract BankClient updatePersonalInfo(
			@Nullable PassportData newPassportData,
			@Nullable Address newAddress
	);

	public UUID getId() {
		return id;
	}

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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass() || passportData == null) return false;
		BankClient bankClient = (BankClient) o;
		return passportData.equals(bankClient.passportData);
	}

	@Override
	public int hashCode() {
		return (passportData == null) ? Objects.hash(null, id) : Objects.hash(passportData);
	}
}

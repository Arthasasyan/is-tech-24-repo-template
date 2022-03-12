package models.client.additional_info;

import org.jetbrains.annotations.NotNull;

public class Address {
	private final String city;
	private final String street;
	private final String houseNumber;

	public Address(@NotNull String city,@NotNull String street,@NotNull String houseNumber) {
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}
}

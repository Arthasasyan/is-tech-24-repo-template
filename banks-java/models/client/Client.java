package models.client;

import models.client.additional_info.Address;
import models.client.additional_info.PassportData;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface Client {
	UUID getId();
	String getName();
	String getSurname();
	@Nullable Address getAddress();
	@Nullable PassportData getPassportData();
	boolean isSuspicious();
	Client updatePersonalInfo(@Nullable PassportData newPassportData, @Nullable Address newAddress);
}

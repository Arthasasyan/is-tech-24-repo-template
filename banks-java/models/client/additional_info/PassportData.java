package models.client.additional_info;

import org.jetbrains.annotations.NotNull;
import tool.BanksException;
import tool.ExceptionMessages;

import java.util.Objects;

public class PassportData {
	private final String hyphenatedPassportNumber;
	public PassportData(@NotNull String hyphenatedPassportNumber) {
		if (!hyphenatedPassportNumber.matches("[0-9]{2}-[0-9]{2}-[0-9]{6}")) {
			throw new BanksException(ExceptionMessages.PASSPORT_DATA_FORMAT);
		}
		this.hyphenatedPassportNumber = hyphenatedPassportNumber;
	}

	public String getHyphenatedPassportNumber() {
		return hyphenatedPassportNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PassportData that = (PassportData) o;
		return hyphenatedPassportNumber.equals(that.hyphenatedPassportNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hyphenatedPassportNumber);
	}
}

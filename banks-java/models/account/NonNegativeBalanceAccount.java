package models.account;

import models.bank.BankModel;
import models.client.Client;
import org.jetbrains.annotations.NotNull;
import tool.BanksException;
import tool.ExceptionMessages;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public abstract class NonNegativeBalanceAccount extends BankAccount {

	public NonNegativeBalanceAccount(
			@NotNull UUID id,
			@NotNull String bankAccountType,
			@NotNull BigDecimal balance,
			@NotNull LocalDate currentDate,
			@NotNull Client client,
			@NotNull BankModel bankModel
	) {
		super(
				id,
				bankAccountType,
				balance,
				currentDate,
				client,
				bankModel
		);
		if (balance.compareTo(BigDecimal.ZERO) < 0)
			throw new BanksException(ExceptionMessages.INVALID_BALANCE);
	}
}

package models.account;

import models.bank.BankModel;
import models.client.Client;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public abstract class NegativeBalanceAccount extends BankAccount {
	public NegativeBalanceAccount(
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
	}
}

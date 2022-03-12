package models.account.factory_method;

import models.account.CreditAccount;
import models.bank.BankModel;
import models.client.Client;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CreditAccountCreator implements AccountCreator {

	@Override
	public CreditAccount create(
			@NotNull BankModel bankModel,
			@NotNull Client client,
			@NotNull LocalDate currentDate
	) {
		return new CreditAccount(
				UUID.randomUUID(),
				BigDecimal.ZERO,
				currentDate,
				client,
				bankModel
		);
	}
}

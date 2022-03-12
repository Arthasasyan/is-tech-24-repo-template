package models.account.factory_method;

import models.account.DebitAccount;
import models.bank.BankModel;
import models.client.Client;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class DebitAccountCreator implements AccountCreator {

	@Override
	public DebitAccount create(
			@NotNull BankModel bankModel,
			@NotNull Client client,
			@NotNull LocalDate currentDate
	) {
		return new DebitAccount(
				UUID.randomUUID(),
				BigDecimal.ZERO,
				currentDate,
				BigDecimal.ZERO,
				client,
				bankModel
		);
	}
}

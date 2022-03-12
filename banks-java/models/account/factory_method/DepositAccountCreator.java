package models.account.factory_method;

import models.account.DepositAccount;
import models.bank.BankModel;
import models.client.Client;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class DepositAccountCreator implements AccountCreator {

	@Override
	public DepositAccount create(
			@NotNull BankModel bankModel,
			@NotNull Client client,
			@NotNull LocalDate currentDate
	) {
		return new DepositAccount(
				UUID.randomUUID(),
				BigDecimal.ZERO,
				currentDate,
				BigDecimal.ZERO,
				client,
				bankModel,
				currentDate.plusDays(bankModel.getDepositTermInDays())
		);
	}
}

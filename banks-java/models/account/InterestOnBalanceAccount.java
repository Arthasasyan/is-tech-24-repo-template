package models.account;

import models.bank.BankModel;
import models.client.Client;
import org.jetbrains.annotations.NotNull;
import tool.BanksException;
import tool.ExceptionMessages;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.UUID;

public abstract class InterestOnBalanceAccount extends NonNegativeBalanceAccount {
	private final BigDecimal currentAccumulatedAmount;

	public InterestOnBalanceAccount(
			@NotNull UUID id,
			@NotNull String bankAccountType,
			@NotNull BigDecimal balance,
			@NotNull LocalDate currentDate,
			@NotNull BigDecimal currentAccumulatedAmount,
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
		if (currentAccumulatedAmount.compareTo(BigDecimal.ZERO) < 0)
			throw new BanksException(ExceptionMessages.INVALID_CURRENT_ACCUMULATED_AMOUNT);
		this.currentAccumulatedAmount = currentAccumulatedAmount;
	}
	protected BigDecimal getCurrentAccumulatedAmount() {
		return currentAccumulatedAmount;
	}

	protected BigDecimal calculateAccumulatedAmount(
			long daysNumToPayInterest,
			BigDecimal currentBalance,
			BigDecimal interest
	) {
		BigDecimal calculatedAmount =
				currentBalance.
						multiply(interest).
						divide(BigDecimal.valueOf(100), MathContext.DECIMAL128).
						divide(BigDecimal.valueOf(365), MathContext.DECIMAL128).
						multiply(BigDecimal.valueOf(daysNumToPayInterest));
		return calculatedAmount;
	}
}

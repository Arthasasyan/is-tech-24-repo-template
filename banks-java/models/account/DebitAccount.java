package models.account;

import models.bank.BankModel;
import models.client.Client;
import org.jetbrains.annotations.NotNull;
import tool.BanksException;
import tool.ExceptionMessages;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class DebitAccount extends InterestOnBalanceAccount {

	public DebitAccount(
			@NotNull UUID id,
			@NotNull BigDecimal balance,
			@NotNull LocalDate currentDate,
			@NotNull BigDecimal currentAccumulatedAmount,
			@NotNull Client client,
			@NotNull BankModel bankModel
	) {
		super(
				id,
				"Debit",
				balance,
				currentDate,
				currentAccumulatedAmount,
				client,
				bankModel
		);
	}

	@Override
	public DebitAccount topUp(@NotNull BigDecimal amount) {
		transactionLimitChecking(amount);
		BigDecimal newBalance = getBalance().add(amount);
		return new DebitAccount(
				getId(),
				newBalance,
				getCurrentDate(),
				getCurrentAccumulatedAmount(),
				getClient(),
				getBankModel()
		);
	}

	@Override
	public DebitAccount withdraw(@NotNull BigDecimal amount) {
		transactionLimitChecking(amount);
		BigDecimal newBalance = getBalance().subtract(amount);
		if (newBalance.compareTo(BigDecimal.ZERO) < 0)
			throw new BanksException(ExceptionMessages.INSUFFICIENT_FUNDS);
		return new DebitAccount(
				getId(),
				newBalance,
				getCurrentDate(),
				getCurrentAccumulatedAmount(),
				getClient(),
				getBankModel()
		);
	}

	@Override
	public DebitAccount updateCurrentTime(@NotNull LocalDate newCurrentDate) {
		if (getCurrentDate().isAfter(newCurrentDate))
			throw new BanksException(ExceptionMessages.INVALID_NEW_CURRENT_DATE);
		LocalDate currentDate = getCurrentDate();
		BigDecimal currentBalance = getBalance();
		BigDecimal currentCalculatedAmount = getCurrentAccumulatedAmount();
		long daysBetween = ChronoUnit.DAYS.between(getCurrentDate(), newCurrentDate);
		while (daysBetween > 0) {
			long daysNumUntilFirstOfNewMonth = currentDate.getMonth().minLength() -
					currentDate.getDayOfMonth() + 1;
			if (daysNumUntilFirstOfNewMonth > daysBetween) {
				currentCalculatedAmount =
						currentCalculatedAmount.add(calculateAccumulatedAmount(
								daysBetween,
								currentBalance,
								getInterestOnBalance()
						));
				break;
			}
			currentCalculatedAmount =
					currentCalculatedAmount.add(calculateAccumulatedAmount(
							daysNumUntilFirstOfNewMonth,
							currentBalance,
							getInterestOnBalance()
					));
			daysBetween -= daysNumUntilFirstOfNewMonth;
			currentDate = currentDate.plusDays(daysNumUntilFirstOfNewMonth);
			currentBalance = currentBalance.add(currentCalculatedAmount);
			currentCalculatedAmount = BigDecimal.ZERO;
		}
		return new DebitAccount(
				getId(),
				currentBalance,
				newCurrentDate,
				currentCalculatedAmount,
				getClient(),
				getBankModel()
		);
	}

	@Override
	public DebitAccount updateTermsOfUse(@NotNull BankModel newBankModel) {
		return new DebitAccount(
				getId(),
				getBalance(),
				getCurrentDate(),
				getCurrentAccumulatedAmount(),
				getClient(),
				newBankModel
		);
	}

	public BigDecimal getInterestOnBalance() {
		return getBankModel().getDebitInterestOnBalance();
	}
}

package models.account;

import models.bank.BankModel;
import models.client.Client;
import org.jetbrains.annotations.NotNull;
import tool.BanksException;
import tool.ExceptionMessages;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.UUID;

public class DepositAccount extends InterestOnBalanceAccount {
	private final LocalDate expirationDate;

	public DepositAccount(
			@NotNull UUID id,
			@NotNull BigDecimal balance,
			@NotNull LocalDate currentDate,
			@NotNull BigDecimal currentAccumulatedAmount,
			@NotNull Client client,
			@NotNull BankModel bankModel,
			@NotNull LocalDate expirationDate
	) {
		super(
				id,
				"Deposit",
				balance,
				currentDate,
				currentAccumulatedAmount,
				client,
				bankModel
		);
		this.expirationDate = expirationDate;
	}

	@Override
	public DepositAccount topUp(@NotNull BigDecimal amount) {
		transactionLimitChecking(amount);
		BigDecimal newBalance = getBalance().add(amount);
		if (newBalance.compareTo(BigDecimal.ZERO) < 0)
			throw new BanksException(ExceptionMessages.INSUFFICIENT_FUNDS);
		return new DepositAccount(
				getId(),
				newBalance,
				getCurrentDate(),
				getCurrentAccumulatedAmount(),
				getClient(),
				getBankModel(),
				expirationDate
		);
	}

	@Override
	public DepositAccount withdraw(@NotNull BigDecimal amount) {
		if (getCurrentDate().isBefore(expirationDate))
			throw new BanksException(ExceptionMessages.DEPOSIT_HAS_NOT_EXPIRED);
		transactionLimitChecking(amount);
		BigDecimal newBalance = getBalance().subtract(amount);
		if (newBalance.compareTo(BigDecimal.ZERO) < 0)
			throw new BanksException(ExceptionMessages.INSUFFICIENT_FUNDS);
		return new DepositAccount(
				getId(),
				newBalance,
				getCurrentDate(),
				getCurrentAccumulatedAmount(),
				getClient(),
				getBankModel(),
				expirationDate
		);
	}

	@Override
	public DepositAccount updateCurrentTime(@NotNull LocalDate newCurrentDate) {
		if (getCurrentDate().isAfter(expirationDate))
			return new DepositAccount(
					getId(),
					getBalance(),
					newCurrentDate,
					getCurrentAccumulatedAmount(),
					getClient(),
					getBankModel(),
					expirationDate
			);
		if (getCurrentDate().isAfter(newCurrentDate))
			throw new BanksException(ExceptionMessages.INVALID_NEW_CURRENT_DATE);
		LocalDate currentDate = getCurrentDate();
		BigDecimal currentBalance = getBalance();
		BigDecimal currentCalculatedAmount = getCurrentAccumulatedAmount();
		long daysBetween = (ChronoUnit.DAYS.between(getCurrentDate(),
				newCurrentDate.isAfter(expirationDate) ? expirationDate : newCurrentDate));
		while (daysBetween > 0) {
			long daysNumUntilFirstOfNewMonth = currentDate.getMonth().minLength() -
					currentDate.getDayOfMonth() + 1;
			if (daysNumUntilFirstOfNewMonth > daysBetween) {
				currentCalculatedAmount =
						currentCalculatedAmount.add(calculateAccumulatedAmount(
								daysBetween,
								currentBalance,
								getCurrentDepositRate(currentBalance)
						));
				if (newCurrentDate.isAfter(expirationDate)) {
					currentBalance = currentBalance.add(currentCalculatedAmount);
					currentCalculatedAmount = BigDecimal.ZERO;
				}
				break;
			}
			currentCalculatedAmount =
					currentCalculatedAmount.add(calculateAccumulatedAmount(
							daysNumUntilFirstOfNewMonth,
							currentBalance,
							getCurrentDepositRate(currentBalance)
					));
			daysBetween -= daysNumUntilFirstOfNewMonth;
			currentDate = currentDate.plusDays(daysNumUntilFirstOfNewMonth);
			currentBalance = currentBalance.add(currentCalculatedAmount);
			currentCalculatedAmount = BigDecimal.ZERO;
		}
		return new DepositAccount(
				getId(),
				currentBalance,
				newCurrentDate,
				currentCalculatedAmount,
				getClient(),
				getBankModel(),
				expirationDate
		);
	}

	@Override
	public DepositAccount updateTermsOfUse(@NotNull BankModel newBankModel) {
		return new DepositAccount(
				getId(),
				getBalance(),
				getCurrentDate(),
				getCurrentAccumulatedAmount(),
				getClient(),
				newBankModel,
				expirationDate
		);
	}

	public SortedMap<BigDecimal, BigDecimal> getDepositRatesByBound() {
		return getBankModel().getSortedDepositRatesByBound();
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	private BigDecimal getCurrentDepositRate(BigDecimal currentBalance) {
		Optional<Map.Entry<BigDecimal, BigDecimal>> currentRateByBound =
				getBankModel().
						getSortedDepositRatesByBound().
						entrySet().
						stream().
						filter(rateByBound -> rateByBound.getValue().compareTo(currentBalance) <= 0).
						reduce((first, second) -> second);
		return currentRateByBound.isEmpty() ?
				getBankModel().getSortedDepositRatesByBound().firstKey() :
				currentRateByBound.get().getKey();
	}
}

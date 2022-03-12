package models.bank;

import org.jetbrains.annotations.NotNull;
import tool.BanksException;
import tool.ExceptionMessages;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;

public class BankModel {
	private final BigDecimal debitInterestOnBalance;
	private final SortedMap<BigDecimal, BigDecimal> sortedDepositRatesByBound; // <rate, bound>
	private final long depositTermInDays;
	private final BigDecimal fixedCommission;
	private final BigDecimal creditLimit;
	private final BigDecimal transactionLimit;

	public BankModel(@NotNull BigDecimal debitInterestOnBalance,
					 @NotNull SortedMap<BigDecimal, BigDecimal> sortedDepositRatesByBound,
					 long depositTermInDays,
					 @NotNull BigDecimal fixedCommission,
					 @NotNull BigDecimal creditLimit,
					 @NotNull BigDecimal transactionLimit
	) {
		if (debitInterestOnBalance.doubleValue() < 0)
			throw new BanksException(ExceptionMessages.INVALID_DEBIT_INTEREST);
		Optional<Map.Entry<BigDecimal, BigDecimal>> invalidRateByBound =
				sortedDepositRatesByBound.entrySet().stream().
						filter(rateByBound -> rateByBound.getKey().compareTo(BigDecimal.ZERO) <= 0 ||
								rateByBound.getValue().compareTo(BigDecimal.ZERO) <= 0).
						findFirst();
		if (!invalidRateByBound.isEmpty() || sortedDepositRatesByBound.isEmpty())
			throw new BanksException(ExceptionMessages.INVALID_DEPOSIT_RATES_BY_BOUND_DATA);
		if (depositTermInDays < 0)
			throw new BanksException(ExceptionMessages.INVALID_DEPOSIT_TERM);
		if (fixedCommission.doubleValue() < 0)
			throw new BanksException(ExceptionMessages.INVALID_FIXED_COMMISSION);
		if (creditLimit.doubleValue() < 0)
			throw new BanksException(ExceptionMessages.INVALID_CREDIT_LIMIT);
		if (transactionLimit.doubleValue() < 0)
			throw new BanksException(ExceptionMessages.INVALID_TRANSACTION_LIMIT);
		this.debitInterestOnBalance = debitInterestOnBalance;
		this.sortedDepositRatesByBound = sortedDepositRatesByBound;
		this.depositTermInDays = depositTermInDays;
		this.fixedCommission = fixedCommission;
		this.creditLimit = creditLimit;
		this.transactionLimit = transactionLimit;
	}

	public BigDecimal getDebitInterestOnBalance() {
		return debitInterestOnBalance;
	}

	public SortedMap<BigDecimal, BigDecimal> getSortedDepositRatesByBound() {
		return Collections.unmodifiableSortedMap(sortedDepositRatesByBound);
	}

	public long getDepositTermInDays() {
		return depositTermInDays;
	}

	public BigDecimal getFixedCommission() {
		return fixedCommission;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public BigDecimal getTransactionLimit() {
		return transactionLimit;
	}

}

package models.account;

import models.bank.BankModel;
import models.client.Client;
import org.jetbrains.annotations.NotNull;
import tool.BanksException;
import tool.ExceptionMessages;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CreditAccount extends NegativeBalanceAccount {

	public CreditAccount(
			@NotNull UUID id,
			@NotNull BigDecimal balance,
			@NotNull LocalDate currentDate,
			@NotNull Client client,
			@NotNull BankModel bankModel
	) {
		super(
				id,
				"Credit",
				balance,
				currentDate,
				client,
				bankModel
		);
	}

	@Override
	public CreditAccount topUp(@NotNull BigDecimal amount) {
		transactionLimitChecking(amount);
		BigDecimal newBalance = getBalance().add(amount);
		return new CreditAccount(
				getId(),
				newBalance,
				getCurrentDate(),
				getClient(),
				getBankModel()
		);
	}

	@Override
	public CreditAccount withdraw(@NotNull BigDecimal amount) {
		transactionLimitChecking(amount);
		if (getBalance().compareTo(BigDecimal.ZERO) < 0)
			amount = amount.add(getBankModel().getFixedCommission());
		BigDecimal newBalance = getBalance().subtract(amount);
		if (newBalance.add(getBankModel().getCreditLimit()).compareTo(BigDecimal.ZERO) < 0)
			throw new BanksException(ExceptionMessages.INSUFFICIENT_FUNDS);
		return new CreditAccount(
				getId(),
				newBalance,
				getCurrentDate(),
				getClient(),
				getBankModel()
		);
	}

	@Override
	public CreditAccount updateCurrentTime(@NotNull LocalDate newCurrentDate) {
		if (getCurrentDate().isAfter(newCurrentDate))
			throw new BanksException(ExceptionMessages.INVALID_NEW_CURRENT_DATE);
		return new CreditAccount(
				getId(),
				getBalance(),
				newCurrentDate,
				getClient(),
				getBankModel()
		);
	}

	@Override
	public CreditAccount updateTermsOfUse(@NotNull BankModel newBankModel) {
		return new CreditAccount(
				getId(),
				getBalance(),
				getCurrentDate(),
				getClient(),
				newBankModel
		);
	}

	public BigDecimal getFixedCommission() {
		return getBankModel().getFixedCommission();
	}

	public BigDecimal getCreditLimit() {
		return getBankModel().getCreditLimit();
	}
}

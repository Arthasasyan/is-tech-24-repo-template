package models.account;

import models.bank.BankModel;
import models.client.Client;
import org.jetbrains.annotations.NotNull;
import tool.BanksException;
import tool.ExceptionMessages;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public abstract class BankAccount implements Account {
	private final UUID id;
	private final String bankAccountType;
	private final BigDecimal balance;
	private final LocalDate currentDate;
	private final Client client;
	private final BankModel bankModel;

	public BankAccount(
			@NotNull UUID id,
			@NotNull String bankAccountType,
			@NotNull BigDecimal balance,
			@NotNull LocalDate currentDate,
			@NotNull Client client,
			@NotNull BankModel bankModel
	) {
		this.id = id;
		this.bankAccountType = bankAccountType;
		this.balance = balance;
		this.currentDate = currentDate;
		this.client = client;
		this.bankModel = bankModel;
	}

	public abstract BankAccount topUp(@NotNull BigDecimal amount);
	public abstract BankAccount withdraw(@NotNull BigDecimal amount);
	public abstract BankAccount updateCurrentTime(@NotNull LocalDate newCurrentDate);
	public abstract BankAccount updateTermsOfUse(@NotNull BankModel newBankModel);

	public UUID getId() {
		return id;
	}

	public String getBankAccountType() {
		return bankAccountType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public Client getClient() {
		return client;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BankAccount account = (BankAccount) o;
		return bankAccountType.equals(account.bankAccountType) && client.equals(account.client);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bankAccountType, client);
	}

	public BankModel getBankModel() {
		return bankModel;
	}

	protected void transactionLimitChecking(BigDecimal amount) {
		if (!isValid(amount))
			throw new BanksException(ExceptionMessages.INVALID_AMOUNT);
		if (client.isSuspicious() && amount.compareTo(bankModel.getTransactionLimit()) > 0)
			throw new BanksException(ExceptionMessages.TRANSACTION_LIMIT_EXCEEDED);
	}

	private boolean isValid(BigDecimal amount) {
		return amount.compareTo(BigDecimal.ZERO) > 0;
	}
}

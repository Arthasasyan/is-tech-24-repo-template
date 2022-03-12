package models.central_bank;

import models.account.Account;
import models.account.factory_method.AccountCreator;
import models.bank.Bank;
import models.bank.BankModel;
import models.client.Client;
import models.client.additional_info.Address;
import models.client.additional_info.PassportData;
import models.client.factory_method.ClientCreator;
import models.repository.BankRepository;
import models.repository.Repository;
import models.repository.TransactionRepository;
import org.jetbrains.annotations.NotNull;
import tool.BanksException;
import tool.ExceptionMessages;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class CentralBank {
	private final Repository<Bank> banks = new BankRepository();
	private final Repository<Transaction> transactions = new TransactionRepository();
	private LocalDate currentDate = LocalDate.now();

	public String openBank(
			@NotNull String bankName,
			@NotNull BankModel bankModel
	) {
		Bank newBank = new Bank(bankName, bankModel, currentDate);
		if (!banks.add(newBank))
			throw new BanksException(ExceptionMessages.BANK_ALREADY_EXISTS);
		return newBank.getName();
	}

	public UUID becomeClient(
			@NotNull String bankName,
			@NotNull ClientCreator clientCreator
	) {
		Bank bank = getBankByName(bankName);
		return bank.becomeClient(clientCreator);
	}

	public UUID openAccount(
			@NotNull String bankName,
			@NotNull UUID clientId,
			@NotNull AccountCreator accountCreator
	) {
		Bank bank = getBankByName(bankName);
		return bank.openAccount(clientId, accountCreator);
	}

	public void updateBankModel(
			@NotNull String bankName,
			@NotNull BankModel newBankModel
	) {
		Bank bank = getBankByName(bankName);
		bank.updateBankModel(newBankModel);
	}

	public void updateClientPersonalInfo(
			@NotNull String bankName,
			@NotNull UUID clientId,
			@NotNull Address newAddress,
			@NotNull PassportData newPassportData
	) {
		Bank bank = getBankByName(bankName);
		bank.updateClientPersonalInfo(clientId, newAddress, newPassportData);
	}

	public void updateCurrentDate(
			@NotNull LocalDate newCurrentDate
	) {
		for (Bank bank : banks.getAll()) {
			bank.updateCurrentDate(newCurrentDate);
		}
		currentDate = newCurrentDate;
	}

	public UUID transact(@NotNull Transaction transaction) {
		if (transaction.getFrom() != null) {
			Bank bank = getBankByAccountId(transaction.getFrom());
			bank.withdrawAccount(transaction.getFrom(), transaction.getAmount());
		}
		if (transaction.getTo() != null) {
			Bank bank = getBankByAccountId(transaction.getTo());
			bank.topUpAccount(transaction.getTo(), transaction.getAmount());
		}
		transactions.add(transaction);
		return transaction.getId();
	}

	public void cancelTransaction(UUID transactionId) {
		Transaction transaction = getTransactionById(transactionId);
		Transaction reversedTransaction = transaction.reverse();
		transact(reversedTransaction);
	}

	public Collection<Bank> getBanks() {
		return banks.getAll();
	}

	public Collection<Transaction> getTransactions() {
		return transactions.getAll();
	}

	public Collection<Account> getAllAccounts() {
		return banks.getAll().stream().
				map(Bank::getAccounts).
				flatMap(Collection::stream).toList();
	}

	public Collection<Client> getAllClients() {
		return banks.getAll().stream().
				map(Bank::getClients).
				flatMap(Collection::stream).toList();
	}

	private Bank getBankByName(String bankName) {
		Optional<Bank> bankSearchResult = banks.getAll().stream().
				filter(currentBank -> currentBank.getName().equals(bankName)).
				findFirst();
		if (bankSearchResult.isEmpty())
			throw new BanksException(ExceptionMessages.INVALID_BANK);
		return bankSearchResult.get();
	}

	private Bank getBankByAccountId(UUID accountId) {
		Optional<Bank> bankSearchResult = banks.getAll().stream().
				filter(currentBank -> currentBank.findAccountById(accountId).isPresent()).
				findFirst();
		if (bankSearchResult.isEmpty())
			throw new BanksException(ExceptionMessages.INVALID_BANK);
		return bankSearchResult.get();
	}

	private Transaction getTransactionById(UUID transactionId) {
		Optional<Transaction> transactionSearchResult =
				transactions.getAll().stream().
						filter(transaction -> transaction.getId().equals(transactionId)).
						findFirst();
		if (transactionSearchResult.isEmpty())
			throw new BanksException(ExceptionMessages.INVALID_TRANSACTION_ID);
		return transactionSearchResult.get();
	}
}

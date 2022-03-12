package models.bank;

import models.account.Account;
import models.account.factory_method.AccountCreator;
import models.client.Client;
import models.client.additional_info.Address;
import models.client.additional_info.PassportData;
import models.client.factory_method.ClientCreator;
import models.repository.AccountRepository;
import models.repository.ClientRepository;
import models.repository.Repository;
import org.jetbrains.annotations.NotNull;
import tool.BanksException;
import tool.ExceptionMessages;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Bank {
	private final String name;
	private final Repository<Account> accounts = new AccountRepository();
	private final Repository<Client> clients = new ClientRepository();
	private BankModel bankModel;
	private LocalDate currentDate;

	public Bank(
			@NotNull String name,
			@NotNull BankModel bankModel,
			@NotNull LocalDate currentDate
	) {
		this.name = name;
		this.bankModel = bankModel;
		this.currentDate = currentDate;
	}

	public UUID becomeClient(@NotNull ClientCreator clientCreator) {
		Client newClient = clientCreator.create();
		if (!clients.add(newClient))
			throw new BanksException(ExceptionMessages.CLIENT_ALREADY_EXISTS);
		return newClient.getId();
	}

	public UUID openAccount(
			@NotNull UUID clientId,
			@NotNull AccountCreator accountCreator
	) {
		Client client = getClientById(clientId);
		Account newAccount = accountCreator.create(bankModel, client, currentDate);
		if (!accounts.add(newAccount))
			throw new BanksException(ExceptionMessages.ACCOUNT_ALREADY_EXISTS);
		return newAccount.getId();
	}

	public void updateBankModel(@NotNull BankModel newBankModel) {
		HashSet<Account> updatedAccountsCollection = new HashSet<>();
		for (Account account : accounts.getAll()) {
			updatedAccountsCollection.add(account.updateTermsOfUse(newBankModel));
		}
		accounts.clear();
		accounts.addAll(updatedAccountsCollection);
		bankModel = newBankModel;
	}

	public void updateClientPersonalInfo(
			@NotNull UUID clientId,
			@NotNull Address newAddress,
			@NotNull PassportData newPassportData
	) {
		Client client = getClientById(clientId);
		Client updatedClient = client.updatePersonalInfo(newPassportData, newAddress);
		clients.remove(client);
		clients.add(updatedClient);
	}

	public void updateCurrentDate(@NotNull LocalDate newCurrentDate) {
		HashSet<Account> updatedAccountsCollection = new HashSet<>();
		for (Account account : accounts.getAll()) {
			updatedAccountsCollection.add(account.updateCurrentTime(newCurrentDate));
		}
		accounts.clear();
		accounts.addAll(updatedAccountsCollection);
		currentDate = newCurrentDate;
	}

	public void topUpAccount(
			@NotNull UUID accountId,
			@NotNull BigDecimal amount
	) {
		Account account = getAccountById(accountId);
		Account updatedAccount = account.topUp(amount);
		accounts.remove(account);
		accounts.add(updatedAccount);
	}

	public void withdrawAccount(
			@NotNull UUID accountId,
			@NotNull BigDecimal amount
	) {
		Account account = getAccountById(accountId);
		Account updatedAccount = account.withdraw(amount);
		accounts.remove(account);
		accounts.add(updatedAccount);
	}

	public Collection<Account> getAccounts() {
		return accounts.getAll();
	}

	public Collection<Client> getClients() {
		return clients.getAll();
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public BankModel getBankModel() {
		return bankModel;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Bank bank = (Bank) o;
		return name.equals(bank.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	public Optional<Account> findAccountById(UUID accountId) {
		Optional<Account> accountSearchResult = accounts.getAll().stream().
				filter(currentAccount -> currentAccount.getId().equals(accountId)).
				findFirst();
		return accountSearchResult;
	}

	public Client getClientById(UUID clientId) {
		Optional<Client> clientSearchResult = clients.getAll().stream().
				filter(currentClient -> currentClient.getId().equals(clientId)).
				findFirst();
		if (clientSearchResult.isEmpty())
			throw new BanksException(ExceptionMessages.INVALID_CLIENT_ID);
		return clientSearchResult.get();
	}

	public Account getAccountById(UUID accountId) {
		Optional<Account> accountSearchResult = findAccountById(accountId);
		if (accountSearchResult.isEmpty())
			throw new BanksException(ExceptionMessages.INVALID_ACCOUNT);
		return accountSearchResult.get();
	}
}

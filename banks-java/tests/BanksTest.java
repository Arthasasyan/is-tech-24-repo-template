package tests;

import models.account.factory_method.DebitAccountCreator;
import models.bank.Bank;
import models.bank.BankModel;
import models.central_bank.CentralBank;
import models.client.Client;
import models.client.additional_info.Address;
import models.client.additional_info.PassportData;
import models.client.factory_method.ClientCreator;
import models.client.factory_method.IndividualCustomerCreator;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

class BanksTest {

	@org.junit.jupiter.api.Test
	void openBank() {
		CentralBank centralBank = new CentralBank();
		String bankName = "Tinkoff";
		centralBank.openBank(bankName, getBankModel());
		List<Bank> banks = centralBank.getBanks().stream().toList();
		int banksNumber = 1;
		Assertions.assertEquals(banksNumber, banks.size());
		Assertions.assertEquals(bankName, banks.get(0).getName());
	}

	@org.junit.jupiter.api.Test
	void becomeBankClient() {
		CentralBank centralBank = new CentralBank();
		String bankName = "Tinkoff";
		centralBank.openBank(bankName, getBankModel());
		UUID clientId = centralBank.becomeClient(bankName, getClientCreator());
		List<Client> clients = centralBank.getAllClients().stream().toList();
		int clientsNumber = 1;
		Assertions.assertEquals(clientId, clients.get(0).getId());

	}

	@org.junit.jupiter.api.Test
	void openAccount() {
		CentralBank centralBank = new CentralBank();
		String bankName = "Tinkoff";
		centralBank.openBank(bankName, getBankModel());
		UUID clientId = centralBank.becomeClient(bankName, getClientCreator());
		centralBank.openAccount(bankName, clientId, new DebitAccountCreator());
	}

	private BankModel getBankModel() {
		BigDecimal debitInterest = BigDecimal.TEN;
		SortedMap<BigDecimal, BigDecimal> depositRates = new TreeMap<>();
		depositRates.put(BigDecimal.TEN, BigDecimal.valueOf(1000));
		depositRates.put(BigDecimal.valueOf(15), BigDecimal.valueOf(10000));
		depositRates.put(BigDecimal.valueOf(20), BigDecimal.valueOf(100000));
		long depositTerm = 365;
		BigDecimal fixedCommission = BigDecimal.valueOf(50);
		BigDecimal creditLimit = BigDecimal.valueOf(1000);
		BigDecimal transactionLimit = BigDecimal.valueOf(5000);
		BankModel bankModel = new BankModel(
				debitInterest,
				depositRates,
				depositTerm,
				fixedCommission,
				creditLimit,
				transactionLimit
		);
		return bankModel;
	}

	private ClientCreator getClientCreator() {
		String name = "Alex";
		String surname = "Markov";
		Address address = new Address("Kazan", "Baumann", "17A");
		PassportData passportData = new PassportData("12-12-123456");
		ClientCreator clientCreator = new IndividualCustomerCreator(
				name,
				surname,
				address,
				passportData
		);
		return clientCreator;
	}
}
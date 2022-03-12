package ui.command;

import models.bank.BankModel;
import models.central_bank.CentralBank;
import ui.UserInterface;

import java.math.BigDecimal;
import java.util.SortedMap;
import java.util.TreeMap;

public class UpdateBankModelCommand extends NonTerminatingCommand {
	private final CentralBank centralBank;

	public UpdateBankModelCommand(UserInterface userInterface, CentralBank centralBank) {
		super(userInterface);
		this.centralBank = centralBank;
	}

	@Override
	public void run() {
		String bankName = getUserInterface().readStringValue("bank name");
		BigDecimal debitInterest = getUserInterface().readBigDecimalValue("debit interest");
		long count = getUserInterface().readLongValue("deposit rates number");
		SortedMap<BigDecimal, BigDecimal> depositRates = new TreeMap<>();
		for (int i = 0; i < count; ++i) {
			BigDecimal rate = getUserInterface().readBigDecimalValue("rate");
			BigDecimal bound = getUserInterface().readBigDecimalValue("bound");
			depositRates.put(rate, bound);
		}
		long depositTerm = getUserInterface().readLongValue("deposit term in days");
		BigDecimal fixedCommission = getUserInterface().readBigDecimalValue("credit fixed commission");
		BigDecimal creditLimit = getUserInterface().readBigDecimalValue("credit limit");
		BigDecimal transactionLimit = getUserInterface().readBigDecimalValue("transaction limit");
		BankModel newBankModel = new BankModel(
				debitInterest,
				depositRates,
				depositTerm,
				fixedCommission,
				creditLimit,
				transactionLimit
		);
		centralBank.updateBankModel(bankName, newBankModel);
		getUserInterface().writeMessage("Successfully updated");
	}
}

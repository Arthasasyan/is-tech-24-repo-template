package models.account;

import models.bank.BankModel;
import models.client.Client;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface Account {
	Account topUp(@NotNull BigDecimal amount);
	Account withdraw(@NotNull BigDecimal amount);
	Account updateCurrentTime(@NotNull LocalDate newCurrentDate);
	Account updateTermsOfUse(@NotNull BankModel newBankModel);
	UUID getId();
	String getBankAccountType();
	BigDecimal getBalance();
	LocalDate getCurrentDate();
	Client getClient();
	BankModel getBankModel();
}

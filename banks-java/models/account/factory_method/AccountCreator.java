package models.account.factory_method;

import models.account.Account;
import models.bank.BankModel;
import models.client.Client;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public interface AccountCreator {
	Account create(@NotNull BankModel bankModel,
				   @NotNull Client client,
				   @NotNull LocalDate currentDate);
}

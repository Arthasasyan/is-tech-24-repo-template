package models.repository;

import models.account.Account;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class AccountRepository implements Repository<Account> {
	private final HashSet<Account> accounts = new HashSet<>();

	@Override
	public Collection<Account> getAll() {
		return Collections.unmodifiableSet(accounts);
	}

	@Override
	public boolean add(@NotNull Account account) {
		return accounts.add(account);
	}

	@Override
	public boolean remove(@NotNull Account account) {
		return accounts.remove(account);
	}

	@Override
	public void clear() {
		accounts.clear();
	}

	@Override
	public void addAll(@NotNull Collection<Account> accounts) {
		this.accounts.addAll(accounts);
	}
}

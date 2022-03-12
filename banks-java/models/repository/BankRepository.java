package models.repository;

import models.bank.Bank;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class BankRepository implements Repository<Bank> {
	private final HashSet<Bank> banks = new HashSet<>();

	@Override
	public Collection<Bank> getAll() {
		return Collections.unmodifiableSet(banks);
	}

	@Override
	public boolean add(@NotNull Bank bank) {
		return banks.add(bank);
	}

	@Override
	public boolean remove(@NotNull Bank bank) {
		return banks.remove(bank);
	}

	@Override
	public void clear() {
		banks.clear();
	}

	@Override
	public void addAll(@NotNull Collection<Bank> banks) {
		this.banks.addAll(banks);
	}
}

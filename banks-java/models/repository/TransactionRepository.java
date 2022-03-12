package models.repository;

import models.central_bank.Transaction;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class TransactionRepository implements Repository<Transaction> {
	private HashSet<Transaction> transactions = new HashSet<>();

	@Override
	public Collection<Transaction> getAll() {
		return Collections.unmodifiableSet(transactions);
	}

	@Override
	public boolean add(@NotNull Transaction transaction) {
		return transactions.add(transaction);
	}

	@Override
	public boolean remove(@NotNull Transaction transaction) {
		return transactions.remove(transaction);
	}

	@Override
	public void clear() {
		transactions.clear();
	}

	@Override
	public void addAll(@NotNull Collection<Transaction> transactions) {
		this.transactions.addAll(transactions);
	}
}

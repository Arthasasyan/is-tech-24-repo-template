package models.central_bank;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tool.BanksException;
import tool.ExceptionMessages;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
	private final UUID id;
	private final UUID _from;
	private final UUID _to;
	private final BigDecimal amount;

	public Transaction(
			@Nullable UUID _from,
			@Nullable UUID _to,
			@NotNull BigDecimal amount
	) {
		if (_from == null && _to == null)
			throw new BanksException(ExceptionMessages.INVALID_TRANSACTION);
		if (amount.compareTo(BigDecimal.ZERO) <= 0)
			throw new BanksException(ExceptionMessages.INVALID_AMOUNT);
		id = UUID.randomUUID();
		this._from = _from;
		this._to = _to;
		this.amount = amount;
	}

	public Transaction reverse() {
		return new Transaction(_to, _from, amount);
	}

	public UUID getId() {
		return id;
	}

	public UUID getFrom() {
		return _from;
	}

	public UUID getTo() {
		return _to;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Transaction that = (Transaction) o;
		return _from.equals(that._from) && _to.equals(that._to) && amount.equals(that.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(_from, _to, amount);
	}
}

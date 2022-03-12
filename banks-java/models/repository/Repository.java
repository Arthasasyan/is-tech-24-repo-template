package models.repository;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface Repository<T> {
	Collection<T> getAll();

	boolean add(@NotNull T item);

	boolean remove(@NotNull T item);

	void clear();

	void addAll(@NotNull Collection<T> items);
}

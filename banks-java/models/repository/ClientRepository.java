package models.repository;

import models.client.Client;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class ClientRepository implements Repository<Client> {
	private final HashSet<Client> clients = new HashSet<>();

	@Override
	public Collection<Client> getAll() {
		return Collections.unmodifiableSet(clients);
	}

	@Override
	public boolean add(@NotNull Client client) {
		return clients.add(client);
	}

	@Override
	public boolean remove(@NotNull Client client) {
		return clients.remove(client);
	}

	@Override
	public void clear() {
		clients.clear();
	}

	@Override
	public void addAll(@NotNull Collection<Client> clients) {
		this.clients.addAll(clients);
	}
}

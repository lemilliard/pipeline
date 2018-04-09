package fr.epsi.i4.pipeline.model.registry;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

public class Registry {

	private RegistryType registryType;

	private String entityPK;

	private List<RegistryEntry> entries;

	public Registry(RegistryType registryType) {
		this.registryType = registryType;
		this.entries = new ArrayList<>();
	}

	public Registry(RegistryType registryType, String entityPK) {
		this.registryType = registryType;
		this.entityPK = entityPK;
		this.entries = new ArrayList<>();
	}

	public RegistryType getRegistryType() {
		return registryType;
	}

	public void setRegistryType(RegistryType registryType) {
		this.registryType = registryType;
	}

	public String getEntityPK() {
		return entityPK;
	}

	public void setEntityPK(String entityPK) {
		this.entityPK = entityPK;
	}

	public List<RegistryEntry> getEntries() {
		return entries;
	}

	public void addEntry(RegistryEntry registryEntry) {
		if (!isEntryAlreadyRegistered(registryEntry)) {
			this.entries.add(registryEntry);
		}
	}

	public List<RegistryEntry> getEntriesByPKalue(Object pkValue) {
		List<RegistryEntry> entries = new ArrayList<>();
		for (RegistryEntry registryEntry : this.entries) {
			if (registryEntry.getPkValue() != null && registryEntry.getPkValue().equals(pkValue)) {
				entries.add(registryEntry);
			}
		}
		return entries;
	}

	private boolean isEntryAlreadyRegistered(RegistryEntry registryEntry) {
		return getEntryByPkValueAndSession(registryEntry.getPkValue(), registryEntry.getSession()) != null;
	}

	private RegistryEntry getEntryByPkValueAndSession(Object pkValue, Session session) {
		RegistryEntry registryEntry = null;
		int i = 0;
		while (i < entries.size() && registryEntry == null) {
			if ((entries.get(i).getPkValue() == null || entries.get(i).getPkValue().equals(pkValue))
					&& entries.get(i).getSession().getId().equals(session.getId())) {
				registryEntry = entries.get(i);
			}
			i++;
		}
		return registryEntry;
	}

	public void removeSession(Session session) {
		int i = 0;
		while (i < entries.size()) {
			if (entries.get(i).getSession().getId().equals(session.getId())) {
				entries.remove(i);
				i--;
			}
			i++;
		}
	}
}

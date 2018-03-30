package fr.epsi.i4.pipeline.model.registry;

import java.util.ArrayList;
import java.util.List;
import javax.websocket.Session;

public class Registry {

	private RegistryEntity registryEntity;

	private String entityPK;

	private List<Entry> entries;

	public Registry(RegistryEntity registryEntity) {
		this.registryEntity = registryEntity;
		this.entries = new ArrayList<>();
	}

	public Registry(RegistryEntity registryEntity, String entityPK) {
		this.registryEntity = registryEntity;
		this.entityPK = entityPK;
		this.entries = new ArrayList<>();
	}

	public RegistryEntity getRegistryEntity() {
		return registryEntity;
	}

	public void setRegistryEntity(RegistryEntity registryEntity) {
		this.registryEntity = registryEntity;
	}

	public String getEntityPK() {
		return entityPK;
	}

	public void setEntityPK(String entityPK) {
		this.entityPK = entityPK;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void addEntry(Entry entry) {
		if (!isEntryAlreadyRegistered(entry)) {
			this.entries.add(entry);
		}
	}

	public List<Entry> getEntriesByPKalue(Object pkValue) {
		List<Entry> entries = new ArrayList<>();
		for (Entry entry : this.entries) {
			if (entry.getPkValue().equals(pkValue)) {
				entries.add(entry);
			}
		}
		return entries;
	}

	private boolean isEntryAlreadyRegistered(Entry entry) {
		return getEntryByPkValueAndSession(entry.getPkValue(), entry.getSession()) != null;
	}

	private Entry getEntryByPkValueAndSession(Object pkValue, Session session) {
		Entry entry = null;
		int i = 0;
		while (i < entries.size() && entry == null) {
			if ((entries.get(i).getPkValue() == null || entries.get(i).getPkValue().equals(pkValue))
					&& entries.get(i).getSession().getId().equals(session.getId())) {
				entry = entries.get(i);
			}
			i++;
		}
		return entry;
	}
}

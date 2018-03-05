package fr.epsi.i4.pipeline.model;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

public class Registry {

	private String entity;

	private String entityPK;

	private List<Entry> entries;

	public Registry(String entity) {
		this.entity = entity;
		this.entries = new ArrayList<>();
	}

	public Registry(String entity, String entityPK) {
		this.entity = entity;
		this.entityPK = entityPK;
		this.entries = new ArrayList<>();
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
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

	public List<Entry> getEntriesByPKalue(String pkValue) {
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

	private Entry getEntryByPkValueAndSession(String pkValue, Session session) {
		Entry entry = null;
		int i = 0;
		while (i < entries.size() && entry == null) {
			if (entries.get(i).getPkValue().equals(pkValue)
					&& entries.get(i).getSession().getId().equals(session.getId())) {
				entry = entries.get(i);
			}
			i++;
		}
		return entry;
	}
}

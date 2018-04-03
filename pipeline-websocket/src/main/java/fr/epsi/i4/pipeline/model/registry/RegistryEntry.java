package fr.epsi.i4.pipeline.model.registry;

import javax.websocket.Session;

public class RegistryEntry {

	private Object pkValue;

	private Session session;

	public RegistryEntry(Session session) {
		this.session = session;
	}

	public RegistryEntry(Object pkValue, Session session) {
		this.pkValue = pkValue;
		this.session = session;
	}

	public Object getPkValue() {
		return pkValue;
	}

	public void setPkValue(Object pkValue) {
		this.pkValue = pkValue;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}

package fr.epsi.i4.pipeline.model;

import javax.websocket.Session;

public class Entry {

	private String pkValue;

	private Session session;

	public Entry(Session session) {
		this.session = session;
	}

	public Entry(String pkValue, Session session) {
		this.pkValue = pkValue;
		this.session = session;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}

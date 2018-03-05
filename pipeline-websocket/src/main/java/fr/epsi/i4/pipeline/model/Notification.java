package fr.epsi.i4.pipeline.model;

public class Notification {

	private String entity;

	private String entityPk;

	private String content;

	public Notification(String entity, String entityPk, String content) {
		this.entity = entity;
		this.entityPk = entityPk;
		this.content = content;
	}
}

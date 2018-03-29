package fr.epsi.i4.pipeline.model;

import fr.epsi.i4.pipeline.model.registry.Entity;

public class Notification {

	private Entity entity;

	private String entityPk;

	private String content;

	public Notification(Entity entity, String entityPk, String content) {
		this.entity = entity;
		this.entityPk = entityPk;
		this.content = content;
	}
}

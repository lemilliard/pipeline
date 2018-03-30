package fr.epsi.i4.pipeline.model;

import fr.epsi.i4.pipeline.model.registry.RegistryEntity;

public class Notification {

	private RegistryEntity registryEntity;

	private String entityPk;

	private Object content;

	public Notification(RegistryEntity registryEntity, String entityPk, Object content) {
		this.registryEntity = registryEntity;
		this.entityPk = entityPk;
		this.content = content;
	}
}

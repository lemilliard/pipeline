package fr.epsi.i4.pipeline.model;

import fr.epsi.i4.pipeline.model.registry.RegistryType;

public class Notification {

	private RegistryType registryType;

	private String entityPk;

	private Object content;

	public Notification(RegistryType registryType, String entityPk, Object content) {
		this.registryType = registryType;
		this.entityPk = entityPk;
		this.content = content;
	}
}

package fr.epsi.i4.pipeline.model;

import fr.epsi.i4.pipeline.model.registry.RegistryType;

public class Notification {

	private RegistryType registryType;

	private String entityPk;

	private Object content;

	private Object pkValue;

	public Notification(RegistryType registryType, String entityPk, Object content) {
		this.registryType = registryType;
		this.entityPk = entityPk;
		setContent(content);
	}

	public void setContent(Object object) {
		this.content = object;
	}

	public void setPkValue(Object pkValue) {
		this.pkValue = pkValue;
	}
}

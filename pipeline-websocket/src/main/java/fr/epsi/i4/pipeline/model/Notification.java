package fr.epsi.i4.pipeline.model;

import com.google.gson.internal.LinkedTreeMap;
import fr.epsi.i4.pipeline.model.registry.RegistryType;

import java.util.ArrayList;

public class Notification {

	private RegistryType registryType;

	private String entityPk;

	private ArrayList contents;

	private LinkedTreeMap content;

	public Notification(RegistryType registryType, String entityPk, Object content) {
		this.registryType = registryType;
		this.entityPk = entityPk;
		setContent(content);
	}

	public void setContent(Object object) {
		if (object instanceof ArrayList) {
			this.contents = (ArrayList) object;
		} else if (object instanceof LinkedTreeMap) {
			this.content = (LinkedTreeMap) object;
		}
	}
}

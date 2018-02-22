package fr.epsi.i4.pipeline.model;

/**
 * Created by tkint on 21/02/2018.
 */
public class Demand {

	private String entity;

	private String entityId;

	public Demand(String entity, String entityId) {
		this.entity = entity;
		this.entityId = entityId;
	}

	public String getEntity() {
		return entity;
	}

	public String getEntityId() {
		return entityId;
	}

	public static String fromMessage(String message) {
//		String[] textArray = message.split(";");
//		String entity = textArray[0];
//		String entityId = textArray[1];
//		Demand demand = new Demand(entity, entityId);
		return message;
	}
}

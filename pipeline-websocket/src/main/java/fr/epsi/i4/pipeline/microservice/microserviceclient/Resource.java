package fr.epsi.i4.pipeline.microservice.microserviceclient;

/**
 * @author Thomas Kint
 */
public enum Resource {
	USERS("user"),
	USER("user/{id_user}"),
	CONNECT("user/connect");

	private String value;

	Resource(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

package fr.epsi.i4.pipeline.microservice.microserviceclient;

/**
 * @author Thomas Kint
 */
public enum Resource {
	USERS("user"),
	USER("user/{id_user}"),
	UPDATE_USER("user"),
	ROLES("role"),
	ROLE("role/{name}"),
	CONNECT("user/connect"),
	CONNECT_BY_ID("user/{id_user}"),
	REGISTER("user/register"),
	TOURNAMENTS("tournament"),
	MATCHS("match");

	private String value;

	Resource(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

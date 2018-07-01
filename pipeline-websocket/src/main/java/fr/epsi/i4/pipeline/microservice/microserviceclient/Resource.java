package fr.epsi.i4.pipeline.microservice.microserviceclient;

/**
 * @author Thomas Kint
 */
public enum Resource {
	USERS("user"),
	USER("user/{idUser}"),
	UPDATE_USER("user"),
	ROLES("role"),
	ROLE("role/{value}"),
	CONNECT("user/connect"),
	CONNECT_BY_ID("user/{idUser}"),
	REGISTER("user/register"),
	TOURNAMENTS("tournoi"),
	TOURNAMENT("tournoi/{idTournoi}"),
	MATCHS("rencontre"),
	MATCH("rencontre/{idRencontre}"),
	SCORE("score/{idRencontre}"),
	SCORE_ADD("score/addPoint");

	private String value;

	Resource(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

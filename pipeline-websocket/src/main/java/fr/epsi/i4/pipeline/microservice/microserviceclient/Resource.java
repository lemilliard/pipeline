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
	MATCH_PLAY("rencontre/{idRencontre}/play"),
	MATCH_PAUSE("rencontre/{idRencontre}/pause"),
	MATCH_END("rencontre/{idRencontre}/end"),
	SCORE("score/{idRencontre}"),
	SCORE_ADD("score/addPoint"),
	ABONNEMENTS("abonnement/user/{idUser}"),
	ABONNEMENT("abonnement"),
	DELETE_ABONNEMENT("abonnement/{idUser}/{idRencontre}");

	private String value;

	Resource(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

package fr.epsi.i4.pipeline.model;

import fr.epsi.i4.pipeline.model.bdd.user.User;

public class Registrator {

	public String email;

	public String password;

	public String confirmPassword;

	public String nom;

	public String prenom;

	public boolean isValid() {
		boolean valid = true;
		if (email == null || email.isEmpty()) {
			valid = false;
		}
		if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
			valid = false;
		}
		if (nom == null || nom.isEmpty()) {
			valid = false;
		}
		if (prenom == null || prenom.isEmpty()) {
			valid = false;
		}
		return valid;
	}

	public User toUser() {
		User user = new User();
		user.id_user = null;
		user.email = email;
		user.password = password;
		user.nom = nom;
		user.prenom = prenom;
		return user;
	}

	public Connector toConnector() {
		Connector connector = new Connector();
		connector.email = email;
		connector.password = password;
		return connector;
	}
}

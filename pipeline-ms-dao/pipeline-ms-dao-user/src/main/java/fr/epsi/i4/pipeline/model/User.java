package fr.epsi.i4.pipeline.model;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import java.math.BigDecimal;

import static com.thomaskint.minidao.enumeration.MDVerb.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(name = "utilisateur")
public class User {

	public static final String emailField = "email";

	public static final String passwordField = "password";

	@MDId
	@MDField(name = "id_utilisateur", verbs = SELECT)
	public BigDecimal id_user;

	@MDField(name = emailField)
	public String email;

	@MDField(name = passwordField)
	public String password;

	@MDField(name = "nom")
	public String nom;

	@MDField(name = "prenom")
	public String prenom;

	@MDField(name = "role")
	public String roleName;
}

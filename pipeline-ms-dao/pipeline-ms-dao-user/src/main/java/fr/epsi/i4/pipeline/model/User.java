package fr.epsi.i4.pipeline.model;

import com.thomaskint.minidao.annotations.MDEntity;
import com.thomaskint.minidao.annotations.MDField;
import com.thomaskint.minidao.annotations.MDId;

import java.math.BigDecimal;

import static com.thomaskint.minidao.enumeration.MDParam.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(name = "utilisateur")
public class User {

	public static final String emailField = "email";

	public static final String passwordField = "password";

	@MDId
	@MDField(name = "id_utilisateur", params = SELECT)
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

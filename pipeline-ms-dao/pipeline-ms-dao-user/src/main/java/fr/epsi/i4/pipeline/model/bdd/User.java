package fr.epsi.i4.pipeline.model.bdd;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import java.math.BigDecimal;

import static com.thomaskint.minidao.enumeration.MDSQLAction.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(tableName = "utilisateur")
public class User {

	public static final String emailField = "email";

	public static final String passwordField = "password";

	@MDId
	@MDField(fieldName = "id_utilisateur", allowedSQLActions = SELECT)
	public BigDecimal id_user;

	@MDField(fieldName = emailField)
	public String email;

	@MDField(fieldName = passwordField)
	public String password;

	@MDField(fieldName = "nom")
	public String nom;

	@MDField(fieldName = "prenom")
	public String prenom;

	@MDField(fieldName = "role")
	public String roleName;
}

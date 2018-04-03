package fr.epsi.i4.pipeline.model.bdd.user;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDManyToOne;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;

import java.math.BigDecimal;

import static com.thomaskint.minidao.enumeration.MDSQLAction.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(tableName = "utilisateur")
public class User {

	public static final String emailFieldName = "email";

	public static final String passwordFieldName = "password";

	public static final String roleFieldName = "role";

	@MDId
	@MDField(fieldName = "id_utilisateur", allowedSQLActions = SELECT)
	public BigDecimal id_user;

	@MDField(fieldName = emailFieldName)
	public String email;

	@MDField(fieldName = passwordFieldName)
	public String password;

	@MDField(fieldName = "nom")
	public String nom;

	@MDField(fieldName = "prenom")
	public String prenom;

	@MDManyToOne(fieldName = roleFieldName, targetFieldName = Role.valeurFieldName, target = Role.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Role role;
}

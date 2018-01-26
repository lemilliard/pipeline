package fr.epsi.i4.pipeline.model;

import com.thomaskint.minidao.annotations.MDEntity;
import com.thomaskint.minidao.annotations.MDField;
import com.thomaskint.minidao.annotations.MDId;

import static com.thomaskint.minidao.enumeration.MDParam.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(name = "user")
public class User {

	public static final String emailFieldName = "email";

	public static final String passwordFieldName = "password";

	@MDId
	@MDField(name = "id_user", params = SELECT)
	public int id_user;

	@MDField(name = emailFieldName)
	public String email;

	@MDField(name = passwordFieldName)
	public String password;

	@MDField(name = "role")
	public String role;

	@MDField(name = "pseudo")
	public String pseudo;
}

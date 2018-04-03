package fr.epsi.i4.pipeline.model.bdd.user;


import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDOneToMany;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;

import java.util.List;

import static com.thomaskint.minidao.enumeration.MDSQLAction.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(tableName = "role")
public class Role {

	public static final String valeurFieldName = "valeur";

	@MDId
	@MDField(fieldName = valeurFieldName, allowedSQLActions = SELECT)
	public String value;

	@MDOneToMany(fieldName = valeurFieldName, target = User.class, targetFieldName = User.roleFieldName, loadPolicy = MDLoadPolicy.HEAVY)
	public List<User> users;

	@MDOneToMany(fieldName = valeurFieldName, target = RightRole.class, targetFieldName = RightRole.roleFieldName, loadPolicy = MDLoadPolicy.HEAVY)
	public List<RightRole> droitRoles;
}

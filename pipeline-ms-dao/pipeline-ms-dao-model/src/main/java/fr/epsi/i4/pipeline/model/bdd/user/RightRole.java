package fr.epsi.i4.pipeline.model.bdd.user;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDManyToOne;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(tableName = "droit_role")
public class RightRole {

	public static final String roleFieldName = "role";

	public static final String idDroitFieldName = "id_droit";

	@MDManyToOne(fieldName = roleFieldName, target = Role.class, targetFieldName = Role.valeurFieldName, loadPolicy = MDLoadPolicy.HEAVY)
	public Role role;

	@MDManyToOne(fieldName = idDroitFieldName, target = Right.class, targetFieldName = Right.idFieldName, loadPolicy = MDLoadPolicy.HEAVY)
	public Right right;
}

package fr.epsi.i4.pipeline.model.bdd;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;

import java.math.BigDecimal;

import static com.thomaskint.minidao.enumeration.MDSQLAction.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(tableName = "droit_role")
public class RightRole {

	@MDField(fieldName = "role", allowedSQLActions = SELECT)
	public String role;

	@MDField(fieldName = "id_droit", allowedSQLActions = SELECT)
	public BigDecimal id_right;
}

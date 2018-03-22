package fr.epsi.i4.pipeline.model.bdd;


import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import static com.thomaskint.minidao.enumeration.MDSQLAction.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(tableName = "role")
public class Role {

	@MDId
	@MDField(fieldName = "valeur", allowedSQLActions = SELECT)
	public String value;
}

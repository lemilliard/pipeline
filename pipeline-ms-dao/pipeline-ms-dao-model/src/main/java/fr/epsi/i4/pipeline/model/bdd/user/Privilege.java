package fr.epsi.i4.pipeline.model.bdd.user;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import static com.thomaskint.minidao.enumeration.MDSQLAction.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(tableName = "privilege")
public class Privilege {

	public static final String valeurFieldName = "valeur";

	@MDId
	@MDField(fieldName = valeurFieldName, allowedSQLActions = SELECT)
	public String value;
}

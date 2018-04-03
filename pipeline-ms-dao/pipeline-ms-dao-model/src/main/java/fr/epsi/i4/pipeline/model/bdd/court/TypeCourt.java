package fr.epsi.i4.pipeline.model.bdd.court;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "type_court")
public class TypeCourt {

	public static final String idFieldName = "valeur";

	@MDId
	@MDField(fieldName = idFieldName)
	public String valeur;
}

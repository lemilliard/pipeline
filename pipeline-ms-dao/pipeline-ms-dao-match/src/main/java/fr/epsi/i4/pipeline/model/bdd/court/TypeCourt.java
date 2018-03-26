package fr.epsi.i4.pipeline.model.bdd.court;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "type_court")
public class TypeCourt {

	@MDId
	@MDField(fieldName = "valeur")
	public String valeur;
}

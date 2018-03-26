package fr.epsi.i4.pipeline.model.bdd.complexe;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "ville")
public class Ville {

	@MDId
	@MDField(fieldName = "valeur")
	public String valeur;
}

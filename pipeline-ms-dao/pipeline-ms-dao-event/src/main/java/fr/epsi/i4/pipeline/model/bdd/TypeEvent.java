package fr.epsi.i4.pipeline.model.bdd;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "type_evenement")
public class TypeEvent {

	@MDId
	@MDField(fieldName = "valeur")
	private String value;
}

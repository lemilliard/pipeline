package fr.epsi.i4.pipeline.model.bdd.score;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import java.math.BigDecimal;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "set_match")
public class SetMatch {

	@MDId
	@MDField(fieldName = "")
	public BigDecimal idSet;
}

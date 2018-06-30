package fr.epsi.i4.pipeline.model.bdd.score;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import java.math.BigDecimal;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "point")
public class Point {

	public static final String idPointEnumFieldName = "id_point_enum";

	@MDId
	@MDField(fieldName = idPointEnumFieldName)
	public BigDecimal idPoint;

	@MDField(fieldName = "valeur")
	public String value;
}

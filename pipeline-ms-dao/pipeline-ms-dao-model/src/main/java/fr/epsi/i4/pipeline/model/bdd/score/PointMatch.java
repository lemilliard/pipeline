package fr.epsi.i4.pipeline.model.bdd.score;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDManyToOne;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;

import java.math.BigDecimal;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "point_match")
public class PointMatch {

	public static final String idJeuFieldName = "id_jeu";

	public static final String idPointEnumFieldName = "id_point_enumeration";

	@MDId
	@MDField(fieldName = "id_point")
	public BigDecimal idPointMatch;

	@MDField(fieldName = idJeuFieldName)
	public BigDecimal idJeuMatch;

	@MDManyToOne(fieldName = PointMatch.idJeuFieldName, targetFieldName = JeuMatch.idJeuFieldName, target = JeuMatch.class, loadPolicy = MDLoadPolicy.HEAVY)
	public JeuMatch jeuMatch;

	@MDManyToOne(fieldName = PointMatch.idPointEnumFieldName, targetFieldName = Point.idPointEnumFieldName, target = Point.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Point point;

	@MDField(fieldName = idPointEnumFieldName)
	public BigDecimal idPointEnum;

	public BigDecimal getIdPointMatch() {
		return idPointMatch;
	}

	public BigDecimal getIdPointEnum() {
		return idPointEnum;
	}
}

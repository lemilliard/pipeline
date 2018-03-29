package fr.epsi.i4.pipeline.model.bdd.court;

import java.math.BigDecimal;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDManyToOne;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import fr.epsi.i4.pipeline.model.bdd.complexe.Complexe;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "court")
public class Court {

	public static final String idComplexeFieldName = "id_complexe";

	@MDId
	@MDField(fieldName = "id_court")
	public BigDecimal idCourt;

	@MDField(fieldName = "nom")
	public String nom;

	@MDManyToOne(fieldName = "type", targetFieldName = TypeCourt.idFieldName, target = TypeCourt.class)
	public TypeCourt typeCourt;

	@MDField(fieldName = Court.idComplexeFieldName)
	public BigDecimal idComplexe;

	@MDManyToOne(fieldName = Court.idComplexeFieldName, targetFieldName = Complexe.idComplexeFieldName, target = Complexe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Complexe complexe;
}

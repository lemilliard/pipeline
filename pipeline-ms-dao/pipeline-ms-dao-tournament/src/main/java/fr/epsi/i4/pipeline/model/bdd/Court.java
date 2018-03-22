package fr.epsi.i4.pipeline.model.bdd;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDManyToOne;

import java.math.BigDecimal;

import static com.thomaskint.minidao.enumeration.MDLoadPolicy.HEAVY;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "court")
public class Court {

	@MDId
	@MDField(fieldName = "id_court")
	public BigDecimal idCourt;

	@MDField(fieldName = "nom")
	public String nom;

	@MDField(fieldName = "type")
	public String type;

	@MDField(fieldName = "id_complexe")
	public BigDecimal idComplexe;

	@MDManyToOne(fieldName = "id_complexe", target = Complexe.class, loadPolicy = HEAVY)
	public Complexe complexe;
}

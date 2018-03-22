package fr.epsi.i4.pipeline.model.bdd;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import java.math.BigDecimal;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "complexe")
public class Complexe {

	@MDId
	@MDField(fieldName = "id_complexe")
	public BigDecimal idComplexe;

	@MDField(fieldName = "nom")
	public String nom;
}

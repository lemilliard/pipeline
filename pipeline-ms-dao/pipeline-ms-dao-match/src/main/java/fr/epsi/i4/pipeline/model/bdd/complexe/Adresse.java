package fr.epsi.i4.pipeline.model.bdd.complexe;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import java.math.BigDecimal;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "adresse")
public class Adresse {

	@MDId
	@MDField(fieldName = "id_adresse")
	public BigDecimal idAdresse;

	@MDField(fieldName = "nom")
	public String nom;
}

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

	public static final String idAdresseFieldName = "id_adresse";

	@MDId
	@MDField(fieldName = idAdresseFieldName)
	public BigDecimal idAdresse;

	@MDField(fieldName = "code_postal")
	public String codePostal;

	@MDField(fieldName = "adresse1")
	public String adresse1;

	@MDField(fieldName = "adresse2")
	public String adresse2;
}

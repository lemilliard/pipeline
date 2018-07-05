package fr.epsi.i4.pipeline.model.bdd.rencontre;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;

import java.math.BigDecimal;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "organisation")
public class Organisation {

	public static final String idRencontreFieldName = "id_rencontre";

	@MDField(fieldName = idRencontreFieldName)
	public BigDecimal idRencontre;

	@MDField(fieldName = "id_tournoi")
	public BigDecimal idTournoi;
}

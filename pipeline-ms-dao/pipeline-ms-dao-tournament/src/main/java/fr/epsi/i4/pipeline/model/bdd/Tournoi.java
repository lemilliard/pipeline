package fr.epsi.i4.pipeline.model.bdd;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDManyToOne;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "tournoi")
public class Tournoi {

	public static final String idComplexeFieldName = "id_complexe";

	public static final String niveauFieldName = "niveau";

	@MDId
	@MDField(fieldName = "id_tournoi")
	public BigDecimal idTournoi;

	@MDField(fieldName = "nom")
	public String nom;

	@MDField(fieldName = "date_tournoi")
	public Date date;

	@MDManyToOne(fieldName = Tournoi.niveauFieldName, targetFieldName = Niveau.valueFieldName, target = Niveau.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Niveau niveau;

	@MDField(fieldName = Tournoi.idComplexeFieldName)
	public BigDecimal idComplexe;

	@MDManyToOne(fieldName = Tournoi.idComplexeFieldName, targetFieldName = Complexe.idComplexeFieldName, target = Complexe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Complexe complexe;
}

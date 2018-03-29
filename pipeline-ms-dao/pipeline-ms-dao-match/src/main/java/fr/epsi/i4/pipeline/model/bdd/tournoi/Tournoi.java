package fr.epsi.i4.pipeline.model.bdd.tournoi;

import java.math.BigDecimal;
import java.util.Date;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDManyToOne;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import fr.epsi.i4.pipeline.model.bdd.complexe.Complexe;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "tournoi")
public class Tournoi {

	public static final String idComplexeFieldName = "id_complexe";

	@MDId
	@MDField(fieldName = "id_tournoi")
	public BigDecimal idTournoi;

	@MDField(fieldName = "nom")
	public String nom;

	@MDField(fieldName = "date_tournoi")
	public Date date;

	@MDField(fieldName = "niveau")
	public String niveau;

	@MDField(fieldName = Tournoi.idComplexeFieldName)
	public BigDecimal idComplexe;

	@MDManyToOne(fieldName = Tournoi.idComplexeFieldName, targetFieldName = Complexe.idComplexeFieldName, target = Complexe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Complexe complexe;
}

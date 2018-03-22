package fr.epsi.i4.pipeline.model.bdd;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import java.math.BigDecimal;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "tournoi")
public class Tournoi {

	@MDId
	@MDField(fieldName = "id_tournoi")
	public BigDecimal idTournoi;

	@MDField(fieldName = "nom")
	public String nom;

	@MDField(fieldName = "niveau")
	public String niveau;
}

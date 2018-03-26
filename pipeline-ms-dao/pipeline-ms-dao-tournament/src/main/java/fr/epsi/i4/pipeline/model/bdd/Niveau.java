package fr.epsi.i4.pipeline.model.bdd;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDOneToMany;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;

import java.util.List;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "niveau")
public class Niveau {

	public static final String valueFieldName = "valeur";

	@MDId
	@MDField(fieldName = valueFieldName)
	public String valeur;

	@MDOneToMany(fieldName = Niveau.valueFieldName, targetFieldName = Tournoi.niveauFieldName, target = Tournoi.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<Tournoi> tournois;
}

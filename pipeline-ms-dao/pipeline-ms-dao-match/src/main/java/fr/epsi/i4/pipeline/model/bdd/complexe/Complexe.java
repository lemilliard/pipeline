package fr.epsi.i4.pipeline.model.bdd.complexe;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDOneToMany;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import fr.epsi.i4.pipeline.model.bdd.court.Court;
import fr.epsi.i4.pipeline.model.bdd.tournoi.Tournoi;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "complexe")
public class Complexe {

	public static final String idComplexeFieldName = "id_complexe";

	@MDId
	@MDField(fieldName = idComplexeFieldName)
	public BigDecimal idComplexe;

	@MDField(fieldName = "nom")
	public String nom;

	@MDOneToMany(fieldName = Complexe.idComplexeFieldName, targetFieldName = Tournoi.idComplexeFieldName, target = Tournoi.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<Tournoi> tournois;

	@MDOneToMany(fieldName = Complexe.idComplexeFieldName, targetFieldName = Court.idComplexeFieldName, target = Court.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<Court> courts;
}

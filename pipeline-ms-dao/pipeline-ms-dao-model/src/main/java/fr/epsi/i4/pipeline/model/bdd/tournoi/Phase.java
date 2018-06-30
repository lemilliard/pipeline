package fr.epsi.i4.pipeline.model.bdd.tournoi;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDOneToMany;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;

import java.math.BigDecimal;
import java.util.List;

import static com.thomaskint.minidao.enumeration.MDSQLAction.SELECT;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "phase")
public class Phase {

	public static final String idPhaseFieldName = "id_phase";

	@MDId
	@MDField(fieldName = idPhaseFieldName, allowedSQLActions = SELECT)
	public BigDecimal idPhase;

	@MDField(fieldName = "nom")
	public String nom;

	@MDField(fieldName = "nombre_max_rencontre")
	public BigDecimal nombreMaxRencontres;

	@MDOneToMany(fieldName = idPhaseFieldName, targetFieldName = Rencontre.idPhaseFieldName, target = Rencontre.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<Rencontre> rencontres;
}

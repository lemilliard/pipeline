package fr.epsi.i4.pipeline.model.bdd.rencontre;

import com.thomaskint.minidao.annotation.*;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import com.thomaskint.minidao.enumeration.MDSQLAction;
import fr.epsi.i4.pipeline.model.bdd.court.Court;
import fr.epsi.i4.pipeline.model.bdd.equipe.Equipe;
import fr.epsi.i4.pipeline.model.bdd.tournoi.Phase;
import fr.epsi.i4.pipeline.model.bdd.user.User;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "rencontre")
public class Rencontre {
	public static final String idRencontreFieldName = "id_rencontre";

	public static final String idEquipeUneFieldName = "id_rencontre";

	public static final String idEquipeDeuxFieldName = "id_equipe_deux";

	public static final String idArbitreFieldName = "id_arbitre";

	public static final String idCourtFieldName = "id_court";

	public static final String idPhaseFieldName = "id_phase";

	@MDId
	@MDField(fieldName = Rencontre.idRencontreFieldName, allowedSQLActions = MDSQLAction.SELECT)
	public BigDecimal idRencontre;

	@MDField(fieldName = "date_debut")
	public Date date;

	@MDManyToOne(fieldName = Rencontre.idEquipeUneFieldName, targetFieldName = Equipe.idEquipeFieldName, target = Equipe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Equipe equipeUne;

	@MDManyToOne(fieldName = Rencontre.idEquipeDeuxFieldName, targetFieldName = Equipe.idEquipeFieldName, target = Equipe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Equipe equipeDeux;

	@MDManyToOne(fieldName = Rencontre.idArbitreFieldName, targetFieldName = User.idUserFieldName, target = User.class, loadPolicy = MDLoadPolicy.HEAVY)
	public User arbitre;

	@MDManyToOne(fieldName = Rencontre.idCourtFieldName, targetFieldName = Court.idCourtFieldName, target = Court.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Court court;

	@MDManyToOne(fieldName = Rencontre.idPhaseFieldName, targetFieldName = Phase.idPhaseFieldName, target = Phase.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Phase phase;
}

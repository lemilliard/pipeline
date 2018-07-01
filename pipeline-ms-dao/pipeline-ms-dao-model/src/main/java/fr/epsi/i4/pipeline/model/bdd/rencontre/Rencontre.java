package fr.epsi.i4.pipeline.model.bdd.rencontre;

import com.thomaskint.minidao.annotation.*;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import com.thomaskint.minidao.enumeration.MDSQLAction;
import fr.epsi.i4.pipeline.model.bdd.court.Court;
import fr.epsi.i4.pipeline.model.bdd.equipe.Equipe;
import fr.epsi.i4.pipeline.model.bdd.score.SetMatch;
import fr.epsi.i4.pipeline.model.bdd.tournoi.Phase;
import fr.epsi.i4.pipeline.model.bdd.user.User;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "rencontre")
public class Rencontre {

	public static final String idRencontreFieldName = "id_rencontre";

	public static final String idEquipeUneFieldName = "id_equipe_une";

	public static final String idEquipeDeuxFieldName = "id_equipe_deux";

	public static final String idArbitreFieldName = "id_arbitre";

	public static final String idCourtFieldName = "id_court";

	public static final String idPhaseFieldName = "id_phase";

	@MDId
	@MDField(fieldName = Rencontre.idRencontreFieldName, allowedSQLActions = MDSQLAction.SELECT)
	public BigDecimal idRencontre;

	@MDField(fieldName = "date_debut")
	public Date dateDebut;

	@MDManyToOne(fieldName = RencontreDetail.idEquipeUneFieldName, targetFieldName = Equipe.idEquipeFieldName, target = Equipe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Equipe equipeUne;

	@MDManyToOne(fieldName = RencontreDetail.idEquipeDeuxFieldName, targetFieldName = Equipe.idEquipeFieldName, target = Equipe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Equipe equipeDeux;
}

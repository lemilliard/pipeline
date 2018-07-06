package fr.epsi.i4.pipeline.model.bdd.rencontre;

import com.thomaskint.minidao.annotation.*;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import com.thomaskint.minidao.enumeration.MDSQLAction;
import fr.epsi.i4.pipeline.model.bdd.equipe.Equipe;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

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

	@MDId
	@MDField(fieldName = Rencontre.idRencontreFieldName, allowedSQLActions = MDSQLAction.SELECT)
	public BigDecimal idRencontre;

	@MDField(fieldName = "date_debut")
	public Timestamp dateDebut;

	@MDField(fieldName = "date_derniere_pause")
	public Timestamp dateDernierePause;

	@MDField(fieldName = "date_derniere_reprise")
	public Timestamp dateDerniereReprise;

	@MDField(fieldName = "duree_jeu")
	public BigDecimal dureeJeu;

	@MDField(fieldName = "date_fin")
	public Timestamp dateFin;

	@MDField(fieldName = "en_pause")
	public BigDecimal enPause;

	@MDManyToOne(fieldName = RencontreDetail.idEquipeUneFieldName, targetFieldName = Equipe.idEquipeFieldName, target = Equipe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Equipe equipeUne;

	@MDField(fieldName = idEquipeUneFieldName)
	public BigDecimal idEquipeUne;

	@MDManyToOne(fieldName = RencontreDetail.idEquipeDeuxFieldName, targetFieldName = Equipe.idEquipeFieldName, target = Equipe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Equipe equipeDeux;

	@MDField(fieldName = idEquipeDeuxFieldName)
	public BigDecimal idEquipeDeux;

	@MDField(fieldName = idArbitreFieldName)
	public BigDecimal idArbitre;

	@MDField(fieldName = idCourtFieldName)
	public BigDecimal idCourt;

	@MDOneToMany(fieldName = Rencontre.idRencontreFieldName, targetFieldName = Organisation.idRencontreFieldName, target = Organisation.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<Organisation> organisations;

	public boolean isPaused() {
		return enPause.equals(new BigDecimal(1));
	}

	public boolean isStarted() {
		return dateDebut != null;
	}

	public boolean isFinished() {
		return dateFin != null;
	}
}

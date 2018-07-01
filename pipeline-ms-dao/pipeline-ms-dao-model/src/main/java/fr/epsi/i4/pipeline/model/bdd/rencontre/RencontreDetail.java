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
import java.util.stream.Collectors;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "rencontre")
public class RencontreDetail extends Rencontre {

	@MDManyToOne(fieldName = RencontreDetail.idArbitreFieldName, targetFieldName = User.idUserFieldName, target = User.class, loadPolicy = MDLoadPolicy.HEAVY)
	public User arbitre;

	@MDManyToOne(fieldName = RencontreDetail.idCourtFieldName, targetFieldName = Court.idCourtFieldName, target = Court.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Court court;

	@MDManyToOne(fieldName = RencontreDetail.idPhaseFieldName, targetFieldName = Phase.idPhaseFieldName, target = Phase.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Phase phase;

	@MDOneToMany(fieldName = RencontreDetail.idRencontreFieldName, targetFieldName = SetMatch.idRencontreFieldName, target = SetMatch.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<SetMatch> sets;

	public List<SetMatch> getOrderedSets() {
		return sets.stream().sorted(Comparator.comparing(SetMatch::getIdSet)).collect(Collectors.toList());
	}

	public SetMatch getLastSet() {
		SetMatch setMatch = null;
		List<SetMatch> orderedSets = getOrderedSets();
		if (orderedSets.size() > 0) {
			setMatch = orderedSets.get(orderedSets.size() - 1);
		}
		return setMatch;
	}
}

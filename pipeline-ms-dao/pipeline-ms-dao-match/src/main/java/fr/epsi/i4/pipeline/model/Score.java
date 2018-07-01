package fr.epsi.i4.pipeline.model;

import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;
import fr.epsi.i4.pipeline.model.bdd.rencontre.RencontreDetail;
import fr.epsi.i4.pipeline.model.bdd.score.Point;
import fr.epsi.i4.pipeline.model.bdd.score.SetMatch;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Score {

	public BigDecimal idRencontre;

	public HashMap<Integer, Set> sets;

	public Point pointActuelEquipeUne;

	public Point pointActuelEquipeDeux;

	public int setsGagnantsEquipeUne;

	public int setsGagnantsEquipeDeux;

	public static Score fromRencontre(RencontreDetail rencontre) {
		Score score = new Score();
		score.idRencontre = rencontre.idRencontre;
		score.sets = new HashMap<>();
		score.setsGagnantsEquipeUne = 0;
		score.setsGagnantsEquipeDeux = 0;

		Set set;
		List<SetMatch> setMatches = rencontre.getOrderedSets();
		for (int i = 0; i < setMatches.size(); i++) {
			set = Set.fromSetMatch(rencontre, setMatches.get(i));
			score.sets.put(i, set);

			if (set.idEquipeGagnante.equals(rencontre.equipeUne.idEquipe)) {
				score.setsGagnantsEquipeUne++;
			} else {
				score.setsGagnantsEquipeDeux++;
			}
		}

		SetMatch lastSetMatch = rencontre.getLastSet();
		if (lastSetMatch != null) {
			score.pointActuelEquipeUne = lastSetMatch.getPointDernierJeu(rencontre.equipeUne);
			score.pointActuelEquipeDeux = lastSetMatch.getPointDernierJeu(rencontre.equipeDeux);
		}

		return score;
	}

	public Set getDernierSet() {
		Set set = null;
		if (sets.size() > 0) {
			set = sets.get(sets.keySet().stream().max(Comparator.comparing(Integer::intValue)).get());
		}
		return set;
	}
}

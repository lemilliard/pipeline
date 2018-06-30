package fr.epsi.i4.pipeline.model;

import fr.epsi.i4.pipeline.model.bdd.equipe.Equipe;
import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;
import fr.epsi.i4.pipeline.model.bdd.score.SetMatch;

import java.util.List;
import java.util.Stack;

public class Score {

	public Stack<Set> sets;

	public static Score fromRencontre(Rencontre rencontre) {
		Score score = new Score();
		score.sets = new Stack<>();

		Equipe equipeUne = rencontre.equipeUne;
		Equipe equipeDeux = rencontre.equipeDeux;
		List<SetMatch> setsEquipeUne = rencontre.getSetsByEquipe(equipeUne);
		List<SetMatch> setsEquipeDeux = rencontre.getSetsByEquipe(equipeDeux);

		for (int i = 0; i < setsEquipeUne.size() && i < setsEquipeDeux.size(); i++) {
			score.sets.add(Set.fromSetMatchs(setsEquipeUne.get(i), setsEquipeDeux.get(i)));
		}

		return score;
	}
}

package fr.epsi.i4.pipeline.model;

import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;
import fr.epsi.i4.pipeline.model.bdd.score.SetMatch;

public class Set {

	public int jeuxEquipeUne;

	public int jeuxEquipeDeux;

	public static Set fromSetMatch(Rencontre rencontre, SetMatch setMatch) {
		Set set = new Set();
		set.jeuxEquipeUne = setMatch.getJeuxGagnantsEquipe(rencontre.equipeUne).size();
		set.jeuxEquipeDeux = setMatch.getJeuxGagnantsEquipe(rencontre.equipeDeux).size();
//		set.jeuxEquipeUne = setMatchEquipeUne.jeux.size();
//		set.jeuxEquipeDeux = setMatchEquipeDeux.jeux.size();
		return set;
	}
}

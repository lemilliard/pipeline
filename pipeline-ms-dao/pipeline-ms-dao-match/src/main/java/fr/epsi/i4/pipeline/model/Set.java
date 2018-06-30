package fr.epsi.i4.pipeline.model;

import fr.epsi.i4.pipeline.model.bdd.score.SetMatch;

public class Set {

	public int jeuxEquipeUne;

	public int jeuxEquipeDeux;

	public static Set fromSetMatchs(SetMatch setMatchEquipeUne, SetMatch setMatchEquipeDeux) {
		Set set = new Set();
		set.jeuxEquipeUne = setMatchEquipeUne.jeux.size();
		set.jeuxEquipeDeux = setMatchEquipeDeux.jeux.size();
		return set;
	}
}

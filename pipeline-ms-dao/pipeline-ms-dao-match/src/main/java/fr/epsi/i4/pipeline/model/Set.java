package fr.epsi.i4.pipeline.model;

import fr.epsi.i4.pipeline.model.bdd.rencontre.RencontreDetail;
import fr.epsi.i4.pipeline.model.bdd.score.SetMatch;

import java.math.BigDecimal;

public class Set {

	public int jeuxEquipeUne;

	public int jeuxEquipeDeux;

	public BigDecimal idEquipeGagnante;

	public static Set fromSetMatch(RencontreDetail rencontre, SetMatch setMatch) {
		Set set = new Set();
		set.jeuxEquipeUne = setMatch.getJeuxGagnantsEquipe(rencontre.equipeUne).size();
		set.jeuxEquipeDeux = setMatch.getJeuxGagnantsEquipe(rencontre.equipeDeux).size();
		if (set.jeuxEquipeUne > set.jeuxEquipeDeux) {
			set.idEquipeGagnante = rencontre.equipeUne.idEquipe;
		} else {
			set.idEquipeGagnante = rencontre.equipeDeux.idEquipe;
		}
		return set;
	}
}

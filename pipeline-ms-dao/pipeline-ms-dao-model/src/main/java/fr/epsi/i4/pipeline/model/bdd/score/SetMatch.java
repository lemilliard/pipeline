package fr.epsi.i4.pipeline.model.bdd.score;

import com.thomaskint.minidao.annotation.*;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import fr.epsi.i4.pipeline.model.bdd.equipe.Equipe;
import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "set_match")
public class SetMatch {

	private static final BigDecimal maxPoint = new BigDecimal(5);

	public static final String idSetFieldName = "id_set";

	public static final String idRencontreFieldName = "id_rencontre";

	@MDId
	@MDField(fieldName = idSetFieldName)
	public BigDecimal idSet;

	@MDField(fieldName = idRencontreFieldName)
	public BigDecimal idRencontre;

	@MDField(fieldName = "date_set")
	public Date dateSet;

	@MDManyToOne(fieldName = SetMatch.idRencontreFieldName, targetFieldName = Rencontre.idRencontreFieldName, target = Rencontre.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Rencontre rencontre;

	@MDOneToMany(fieldName = SetMatch.idSetFieldName, targetFieldName = JeuMatch.idSetFieldName, target = JeuMatch.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<JeuMatch> jeux;

	public BigDecimal getIdSet() {
		return idSet;
	}

	public List<JeuMatch> getJeuxEquipe(Equipe equipe) {
		return jeux.stream()
				.filter(jeuMatch -> jeuMatch.equipe.idEquipe.equals(equipe.idEquipe))
				.collect(Collectors.toList());
	}

	public List<JeuMatch> getJeuxGagnantsEquipe(Equipe equipe) {
		return jeux.stream()
				.filter(jeuMatch -> {
					Point dernierPoint = jeuMatch.getDernierPoint();
					return jeuMatch.equipe.idEquipe.equals(equipe.idEquipe)
							&& dernierPoint != null
							&& dernierPoint.idPoint.equals(maxPoint);
				})
				.collect(Collectors.toList());
	}

	public JeuMatch getDernierJeu(Equipe equipe) {
		JeuMatch jeuMatch = null;
		List<JeuMatch> jeuxEquipe = getJeuxEquipe(equipe);
		if (jeuxEquipe.size() > 0) {
			jeuMatch = jeuxEquipe.stream().max(Comparator.comparing(JeuMatch::getIdJeu)).get();
		}
		return jeuMatch;
	}

	public Point getPointDernierJeu(Equipe equipe) {
		Point point = null;
		JeuMatch jeuMatch = getDernierJeu(equipe);
		if (jeuMatch != null) {
			point = jeuMatch.getDernierPoint();
		}
		return point;
	}
}

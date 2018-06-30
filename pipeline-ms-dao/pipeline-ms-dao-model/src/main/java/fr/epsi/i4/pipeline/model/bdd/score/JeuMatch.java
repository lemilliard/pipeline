package fr.epsi.i4.pipeline.model.bdd.score;

import com.thomaskint.minidao.annotation.*;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import fr.epsi.i4.pipeline.model.bdd.equipe.Equipe;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "jeu_match")
public class JeuMatch {

	public static final String idJeuFieldName = "id_jeu";

	public static final String idSetFieldName = "id_set";

	public static final String idEquipeFieldName = "id_equipe";

	@MDId
	@MDField(fieldName = idJeuFieldName)
	public BigDecimal idJeu;

	@MDField(fieldName = idSetFieldName)
	public BigDecimal idSet;

	@MDField(fieldName = idEquipeFieldName)
	public BigDecimal idEquipe;

	@MDField(fieldName = "date_jeu")
	public Date date;

	@MDManyToOne(fieldName = JeuMatch.idSetFieldName, targetFieldName = SetMatch.idSetFieldName, target = SetMatch.class, loadPolicy = MDLoadPolicy.HEAVY)
	public SetMatch setMatch;

	@MDManyToOne(fieldName = JeuMatch.idEquipeFieldName, targetFieldName = Equipe.idEquipeFieldName, target = Equipe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Equipe equipe;

	@MDOneToMany(fieldName = JeuMatch.idJeuFieldName, targetFieldName = PointMatch.idJeuFieldName, target = PointMatch.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<PointMatch> points;

	public BigDecimal getIdJeu() {
		return idJeu;
	}

	public Point getDernierPoint() {
		Point point = null;
		if (points.size() > 0) {
			point = points.stream().max(Comparator.comparing(PointMatch::getIdPointEnum)).get().point;
		}
		return point;
	}
}

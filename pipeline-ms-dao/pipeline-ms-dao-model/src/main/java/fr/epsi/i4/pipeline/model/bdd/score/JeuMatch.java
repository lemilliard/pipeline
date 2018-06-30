package fr.epsi.i4.pipeline.model.bdd.score;

import com.thomaskint.minidao.annotation.*;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import fr.epsi.i4.pipeline.model.bdd.equipe.Equipe;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "jeu_match")
public class JeuMatch {

	public static final String idJeuFieldName = "id_jeu";

	public static final String idSetFieldName = "id_set";

	@MDId
	@MDField(fieldName = idJeuFieldName)
	public BigDecimal idJeu;

	@MDField(fieldName = "date_jeu")
	public Date date;

	@MDManyToOne(fieldName = JeuMatch.idSetFieldName, targetFieldName = SetMatch.idSetFieldName, target = SetMatch.class, loadPolicy = MDLoadPolicy.HEAVY)
	public SetMatch setMatch;

	@MDOneToMany(fieldName = JeuMatch.idJeuFieldName, targetFieldName = PointMatch.idJeuFieldName, target = PointMatch.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<PointMatch> points;
}

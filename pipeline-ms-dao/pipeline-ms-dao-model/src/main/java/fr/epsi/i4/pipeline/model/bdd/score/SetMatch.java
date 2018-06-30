package fr.epsi.i4.pipeline.model.bdd.score;

import com.thomaskint.minidao.annotation.*;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import fr.epsi.i4.pipeline.model.bdd.equipe.Equipe;
import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;
import fr.epsi.i4.pipeline.model.bdd.tournoi.Phase;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "set_match")
public class SetMatch {

	public static final String idSetFieldName = "id_set";

	public static final String idRencontreFieldName = "id_rencontre";

	public static final String idEquipeFieldName = "id_equipe";

	@MDId
	@MDField(fieldName = idSetFieldName)
	public BigDecimal idSet;

	@MDField(fieldName = "date_set")
	public Date dateSet;

	@MDManyToOne(fieldName = SetMatch.idRencontreFieldName, targetFieldName = Rencontre.idRencontreFieldName, target = Rencontre.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Rencontre rencontre;

	@MDManyToOne(fieldName = SetMatch.idEquipeFieldName, targetFieldName = Equipe.idEquipeFieldName, target = Equipe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Equipe equipe;

	@MDOneToMany(fieldName = SetMatch.idSetFieldName, targetFieldName = JeuMatch.idSetFieldName, target = JeuMatch.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<JeuMatch> jeux;
}

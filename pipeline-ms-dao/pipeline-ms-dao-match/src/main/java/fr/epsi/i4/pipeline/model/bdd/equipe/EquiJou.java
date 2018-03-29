package fr.epsi.i4.pipeline.model.bdd.equipe;

import java.math.BigDecimal;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDManyToOne;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "equijou")
public class EquiJou {

	public static final String idJoueurFieldName = "id_joueur";

	public static final String idEquipeFieldName = "id_equipe";

	@MDId
	@MDField(fieldName = idJoueurFieldName)
	public BigDecimal idJoueur;

	@MDManyToOne(fieldName = idJoueurFieldName, targetFieldName = Joueur.idJoueurFieldName, target = Joueur.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Joueur joueur;

	@MDField(fieldName = idEquipeFieldName)
	public BigDecimal idEquipe;

	@MDManyToOne(fieldName = idEquipeFieldName, targetFieldName = Equipe.idEquipeFieldName, target = Equipe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Equipe equipe;
}

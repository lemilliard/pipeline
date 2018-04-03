package fr.epsi.i4.pipeline.model.bdd.equipe;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDOneToMany;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "joueur")
public class Joueur {

	public final static String idJoueurFieldName = "id_joueur";

	@MDId
	@MDField(fieldName = idJoueurFieldName)
	public BigDecimal idJoueur;

	@MDField(fieldName = "classement")
	public String classement;

	@MDField(fieldName = "nationalite")
	public String nationalite;

	@MDField(fieldName = "nom")
	public String nom;

	@MDField(fieldName = "prenom")
	public String prenom;

	@MDOneToMany(fieldName = Joueur.idJoueurFieldName, targetFieldName = EquiJou.idJoueurFieldName, target = EquiJou.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<EquiJou> equiJous;
}

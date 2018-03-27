package fr.epsi.i4.pipeline.model.bdd;

import com.thomaskint.minidao.annotation.*;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "complexe")
public class Complexe {

	public static final String idComplexeFieldName = "id_complexe";

	public static final String idAdresseFieldName = "id_adresse";

	@MDId
	@MDField(fieldName = idComplexeFieldName)
	public BigDecimal idComplexe;

	@MDField(fieldName = "nom")
	public String nom;

	@MDField(fieldName = idAdresseFieldName)
	public BigDecimal idAdresse;

	@MDManyToOne(fieldName = Complexe.idAdresseFieldName, targetFieldName = Adresse.idAdresseFieldName, target = Adresse.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Adresse adresse;

	@MDOneToMany(fieldName = Complexe.idComplexeFieldName, targetFieldName = Tournoi.idComplexeFieldName, target = Tournoi.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<Tournoi> tournois;
}

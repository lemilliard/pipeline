package fr.epsi.i4.pipeline.model.bdd.abonnement;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDManyToOne;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;

import java.math.BigDecimal;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "abonnement")
public class Abonnement {

	public static final String idUserFieldName = "id_utilisateur";

	public static final String idRencontreFieldName = "id_rencontre";

	@MDField(fieldName = idUserFieldName)
	public BigDecimal idUser;

	@MDField(fieldName = idRencontreFieldName)
	public BigDecimal idRencontre;

	@MDManyToOne(fieldName = idUserFieldName, targetFieldName = UserLight.idUserFieldName, target = UserLight.class, loadPolicy = MDLoadPolicy.HEAVY)
	public UserLight user;
}

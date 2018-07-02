package fr.epsi.i4.pipeline.model.bdd.abonnement;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDManyToOne;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;

import java.math.BigDecimal;

import static com.thomaskint.minidao.enumeration.MDSQLAction.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(tableName = "utilisateur")
public class UserLight {

	public static final String idUserFieldName = "id_utilisateur";

	public static final String emailFieldName = "email";

	@MDId
	@MDField(fieldName = idUserFieldName, allowedSQLActions = SELECT)
	public BigDecimal idUser;

	@MDField(fieldName = emailFieldName)
	public String email;
}

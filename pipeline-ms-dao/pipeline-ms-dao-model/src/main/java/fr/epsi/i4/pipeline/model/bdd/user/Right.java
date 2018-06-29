package fr.epsi.i4.pipeline.model.bdd.user;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDManyToOne;

import java.math.BigDecimal;

import static com.thomaskint.minidao.enumeration.MDSQLAction.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(tableName = "droit")
public class Right {

	public static final String idFieldName = "id_droit";

	public static final String privilegeFieldName = "privilege";

	@MDId
	@MDField(fieldName = idFieldName, allowedSQLActions = SELECT)
	public BigDecimal idRight;

	@MDField(fieldName = "table_droit")
	public String tableRight;

	@MDManyToOne(fieldName = privilegeFieldName, target = Privilege.class, targetFieldName = Privilege.valeurFieldName)
	public Privilege privilege;
}

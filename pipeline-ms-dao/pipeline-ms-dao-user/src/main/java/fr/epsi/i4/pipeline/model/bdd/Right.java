package fr.epsi.i4.pipeline.model.bdd;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import java.math.BigDecimal;

import static com.thomaskint.minidao.enumeration.MDSQLAction.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(tableName = "droit")
public class Right {

	@MDId
	@MDField(fieldName = "id_droit", allowedSQLActions = SELECT)
	public BigDecimal id_right;

	@MDField(fieldName = "table_droit")
	public String tableRight;

	@MDField(fieldName = "privilege")
	public String privilege;
}

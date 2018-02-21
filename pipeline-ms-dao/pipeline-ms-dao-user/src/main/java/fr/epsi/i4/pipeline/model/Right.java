package fr.epsi.i4.pipeline.model;

import com.thomaskint.minidao.annotations.MDEntity;
import com.thomaskint.minidao.annotations.MDField;
import com.thomaskint.minidao.annotations.MDId;

import java.math.BigDecimal;

import static com.thomaskint.minidao.enumeration.MDParam.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(name = "droit")
public class Right {

	@MDId
	@MDField(name = "id_droit", params = SELECT)
	public BigDecimal id_right;

	@MDField(name = "table_droit")
	public String tableRight;

	@MDField(name = "privilege")
	public String privilege;
}

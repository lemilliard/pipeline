package fr.epsi.i4.pipeline.model;

import com.thomaskint.minidao.annotations.MDEntity;
import com.thomaskint.minidao.annotations.MDField;
import com.thomaskint.minidao.annotations.MDId;

import java.math.BigDecimal;

import static com.thomaskint.minidao.enumeration.MDParam.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(name = "privilege")
public class Privilege {

	@MDId
	@MDField(name = "valeur", params = SELECT)
	public String value;
}
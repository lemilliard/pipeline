package fr.epsi.i4.pipeline.model;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;

import java.math.BigDecimal;

import static com.thomaskint.minidao.enumeration.MDVerb.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(name = "droit_role")
public class RightRole {

	@MDField(name = "role", verbs = SELECT)
	public String role;

	@MDField(name = "id_droit", verbs = SELECT)
	public BigDecimal id_right;
}

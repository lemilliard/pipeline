package fr.epsi.i4.pipeline.model;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import static com.thomaskint.minidao.enumeration.MDVerb.SELECT;

/**
 * Created by tkint on 25/01/2018.
 */
@MDEntity(name = "privilege")
public class Privilege {

	@MDId
	@MDField(name = "valeur", verbs = SELECT)
	public String value;
}

package fr.epsi.i4.pipeline.model.bdd.score;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "jeu_match")
public class JeuMatch {

	@MDId
	@MDField(fieldName = "id_jeu")
	public BigDecimal idJeu;

	@MDField(fieldName = "id_set")
	public BigDecimal idSet;

	@MDField(fieldName = "date_jeu")
	public Date date;
}

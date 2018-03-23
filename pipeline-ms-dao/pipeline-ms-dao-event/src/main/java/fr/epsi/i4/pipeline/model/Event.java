package fr.epsi.i4.pipeline.model;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import java.math.BigDecimal;

@MDEntity(tableName = "evenement")
public class Event {

	@MDId
	@MDField(fieldName = "id_evenement")
	public BigDecimal idEvent;

	@MDField(fieldName = "id_joueur")
	public BigDecimal idPlayer;

	@MDField(fieldName = "type")
	public String type;

	@MDField(fieldName = "reussis")
	public boolean state;

	@MDField(fieldName = "score")
	public int score;
}

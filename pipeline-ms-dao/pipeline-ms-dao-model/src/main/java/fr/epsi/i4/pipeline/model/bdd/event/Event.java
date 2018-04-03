package fr.epsi.i4.pipeline.model.bdd.event;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import java.math.BigDecimal;
import java.util.Date;

@MDEntity(tableName = "evenement")
public class Event {

	@MDId
	@MDField(fieldName = "id_evenement")
	public BigDecimal idEvent;

	@MDField(fieldName = "id_joueur")
	public BigDecimal idPlayer;

	@MDField(fieldName = "type")
	public String type;

	@MDField(fieldName = "score")
	public boolean score;

	@MDField(fieldName = "reussis")
	public int reussis;

	@MDField(fieldName = "date_evenement")
	public Date date;
}

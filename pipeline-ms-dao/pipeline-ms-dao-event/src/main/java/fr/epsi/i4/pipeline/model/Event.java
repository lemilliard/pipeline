package fr.epsi.i4.pipeline.model;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;

import java.math.BigDecimal;

@MDEntity(name = "evenement")
public class Event {

	@MDId
	@MDField(name = "id_evenement")
	public BigDecimal idEvent;

	@MDField(name = "id_joueur")
	public BigDecimal idPlayer;

	@MDField(name = "type")
	public String type;

	@MDField(name = "reussis")
	public boolean state;

	@MDField(name = "score")
	public int score;
}

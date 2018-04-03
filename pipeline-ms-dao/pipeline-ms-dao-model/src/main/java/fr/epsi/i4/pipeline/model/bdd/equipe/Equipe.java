package fr.epsi.i4.pipeline.model.bdd.equipe;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDId;
import com.thomaskint.minidao.annotation.MDOneToMany;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "equipe")
public class Equipe {

	public static final String idEquipeFieldName = "id_equipe";

	@MDId
	@MDField(fieldName = Equipe.idEquipeFieldName)
	public BigDecimal idEquipe;

	@MDOneToMany(fieldName = Equipe.idEquipeFieldName, targetFieldName = EquiJou.idEquipeFieldName, target = EquiJou.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<EquiJou> equiJous;
}

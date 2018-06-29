package fr.epsi.i4.pipeline.model.bdd.court;

import com.thomaskint.minidao.annotation.*;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import fr.epsi.i4.pipeline.model.bdd.complexe.Complexe;
import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Thomas Kint
 */
@MDEntity(tableName = "court")
public class Court {

	public static final String idCourtFieldName = "id_court";

	public static final String idComplexeFieldName = "id_complexe";

	@MDId
	@MDField(fieldName = Court.idCourtFieldName)
	public BigDecimal idCourt;

	@MDField(fieldName = "nom")
	public String nom;

	@MDManyToOne(fieldName = "type", targetFieldName = TypeCourt.idFieldName, target = TypeCourt.class)
	public TypeCourt typeCourt;

	@MDManyToOne(fieldName = Court.idComplexeFieldName, targetFieldName = Complexe.idComplexeFieldName, target = Complexe.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Complexe complexe;

	@MDOneToMany(fieldName = Court.idCourtFieldName, targetFieldName = Rencontre.idCourtFieldName, target = Rencontre.class, loadPolicy = MDLoadPolicy.HEAVY)
	public List<Rencontre> rencontres;
}

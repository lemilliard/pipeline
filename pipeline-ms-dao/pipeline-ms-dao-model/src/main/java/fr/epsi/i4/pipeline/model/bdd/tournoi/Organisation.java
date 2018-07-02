/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.i4.pipeline.model.bdd.tournoi;

import com.thomaskint.minidao.annotation.MDEntity;
import com.thomaskint.minidao.annotation.MDField;
import com.thomaskint.minidao.annotation.MDOneToMany;
import com.thomaskint.minidao.enumeration.MDLoadPolicy;
import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;
import static fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre.idCourtFieldName;
import java.math.BigDecimal;

/**
 *
 * @author kbouzan
 */
@MDEntity(tableName = "organisation")
public class Organisation {

	@MDOneToMany(fieldName = "ID_TOURNOI", targetFieldName = "ID_TOURNOI", target = Tournoi.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Tournoi tournoi;
        
        @MDField(fieldName = "ID_TOURNOI")
	public BigDecimal idTournoi;
        
        @MDOneToMany(fieldName = "ID_RENCONTRE", targetFieldName = "ID_RENCONTRE", target = Rencontre.class, loadPolicy = MDLoadPolicy.HEAVY)
	public Rencontre rencontre;
        
        @MDField(fieldName = "ID_RENCONTRE")
	public BigDecimal idRencontre;
        
        @MDField(fieldName = "ID_USER")
	public BigDecimal idUser;
}

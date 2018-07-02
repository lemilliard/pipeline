package fr.epsi.i4.pipeline.ws;

import com.thomaskint.minidao.enumeration.MDConditionLink;
import com.thomaskint.minidao.enumeration.MDConditionOperator;
import com.thomaskint.minidao.exception.MDException;
import com.thomaskint.minidao.querybuilder.MDCondition;
import fr.epsi.i4.pipeline.model.bdd.abonnement.Abonnement;
import fr.epsi.i4.pipeline.model.bdd.tournoi.Tournoi;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class AbonnementWS extends WebService {

	@GetMapping("/abonnement")
	public List<Abonnement> getAbonnements() {
		return getEntities(Abonnement.class);
	}

	@GetMapping("/abonnement/user/{id}")
	public List<Abonnement> getAbonnementsByUser(@PathVariable("id") int id) {
		MDCondition condition = new MDCondition(Abonnement.idUserFieldName, MDConditionOperator.EQUAL, id);
		return getEntities(Abonnement.class, condition);
	}

	@GetMapping("/abonnement/rencontre/{id}")
	public List<Abonnement> getAbonnementsByRencontre(@PathVariable("id") int id) {
		MDCondition condition = new MDCondition(Abonnement.idRencontreFieldName, MDConditionOperator.EQUAL, id);
		return getEntities(Abonnement.class, condition);
	}

	@PostMapping("/abonnement")
	public List<Abonnement> createAbonnement(@RequestBody Abonnement abonnement) {
		createEntity(abonnement);
		return getAbonnementsByUser(abonnement.idUser.intValue());
	}

	@DeleteMapping("/abonnement/{idUser}/{idRencontre}")
	public List<Abonnement> deleteAbonnement(@PathVariable("idUser") int idUser, @PathVariable("idRencontre") int idRencontre) {
		MDCondition condition1 = new MDCondition(Abonnement.idUserFieldName, MDConditionOperator.EQUAL, idUser);
		MDCondition condition2 = new MDCondition(Abonnement.idRencontreFieldName, MDConditionOperator.EQUAL, idRencontre, MDConditionLink.AND, condition1);
		try {
			getMiniDAO().delete().deleteEntities(Abonnement.class, condition2);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return getAbonnementsByUser(idUser);
	}
}

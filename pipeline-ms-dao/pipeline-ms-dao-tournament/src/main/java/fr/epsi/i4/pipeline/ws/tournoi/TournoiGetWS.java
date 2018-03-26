package fr.epsi.i4.pipeline.ws.tournoi;

import com.thomaskint.minidao.enumeration.MDConditionOperator;
import com.thomaskint.minidao.exception.MDException;
import com.thomaskint.minidao.querybuilder.MDCondition;
import fr.epsi.i4.pipeline.model.bdd.Tournoi;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Thomas Kint
 */
@RestController
public class TournoiGetWS extends WebService {

	@GetMapping("/tournoi")
	public List<Tournoi> getTournois() {
		List<Tournoi> tournois = null;
		try {
			tournois = getMiniDAO().read().getEntities(Tournoi.class);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return tournois;
	}

	@GetMapping("/tournoi/{id}")
	public Tournoi getTournoiById(@PathVariable("id") int id) {
		Tournoi tournoi = null;
		try {
			tournoi = getMiniDAO().read().getEntityById(Tournoi.class, id);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return tournoi;
	}

	@GetMapping("/tournois/complexe/{id}")
	public List<Tournoi> getTournoisByComplexeId(@PathVariable("id") int id) {
		List<Tournoi> tournois = null;
		try {
			MDCondition condition = new MDCondition(Tournoi.idComplexeFieldName, MDConditionOperator.EQUAL, 1);
			tournois = getMiniDAO().read().getEntities(Tournoi.class);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return tournois;
	}
}

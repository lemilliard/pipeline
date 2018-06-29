package fr.epsi.i4.pipeline.ws;

import com.thomaskint.minidao.enumeration.MDConditionOperator;
import com.thomaskint.minidao.exception.MDException;
import com.thomaskint.minidao.querybuilder.MDCondition;
import fr.epsi.i4.pipeline.model.bdd.tournoi.Tournoi;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thomas Kint
 */
@RestController
public class TournoiWS extends WebService {

	@GetMapping("/tournoi")
	public List<Tournoi> getTournois() {
		return getEntities(Tournoi.class);
	}

	@GetMapping("/tournoi/{id}")
	public Tournoi getTournoiById(@PathVariable("id") int id) {
		return getEntityById(Tournoi.class, id);
	}

	@GetMapping("/tournoi/complexe/{id}")
	public List<Tournoi> getTournoisByComplexeId(@PathVariable("id") int id) {
		List<Tournoi> tournois = null;
		try {
			MDCondition condition = new MDCondition(Tournoi.idComplexeFieldName, MDConditionOperator.EQUAL, id);
			tournois = getMiniDAO().read().getEntities(Tournoi.class, condition);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return tournois;
	}

	@PostMapping("/tournoi")
	public boolean createTournoi(@RequestBody Tournoi tournoi) {
		return createEntity(tournoi);
	}

	@PutMapping("/tournoi")
	public boolean updateTournoi(@RequestBody Tournoi tournoi) {
		return updateEntity(tournoi);
	}
}

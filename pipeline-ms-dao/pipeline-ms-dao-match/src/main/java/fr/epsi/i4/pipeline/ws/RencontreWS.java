package fr.epsi.i4.pipeline.ws;

import com.thomaskint.minidao.enumeration.MDConditionOperator;
import com.thomaskint.minidao.exception.MDException;
import com.thomaskint.minidao.querybuilder.MDCondition;
import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thomas Kint
 */
@RestController
public class RencontreWS extends WebService {

	@GetMapping("/rencontre")
	public List<Rencontre> getRencontres() {
		return getEntities(Rencontre.class);
	}

	@GetMapping("/rencontre/{id}")
	public Rencontre getRencontreById(@PathVariable("id") int id) {
		return getEntityById(Rencontre.class, id);
	}

	@GetMapping("/rencontre/court/{id}")
	public List<Rencontre> getRencontresByCourtId(@PathVariable("id") int id) {
		List<Rencontre> rencontres = null;
		try {
			MDCondition condition = new MDCondition(Rencontre.idCourtFieldName, MDConditionOperator.EQUAL, id);
			rencontres = getMiniDAO().read().getEntities(Rencontre.class, condition);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return rencontres;
	}

	@PostMapping("/rencontre")
	public boolean createRencontre(@RequestBody Rencontre rencontre) {
		return createEntity(rencontre);
	}

	@PutMapping("/rencontre")
	public boolean updateRencontre(@RequestBody Rencontre rencontre) {
		return updateEntity(rencontre);
	}

	@DeleteMapping("/rencontre")
	public boolean deleteRencontre(@RequestBody Rencontre rencontre) {
		return deleteEntity(rencontre);
	}

	@DeleteMapping("/rencontre/{id}")
	public boolean deleteRencontreById(@PathVariable("id") int id) {
		return deleteEntityById(Rencontre.class, id);
	}
}

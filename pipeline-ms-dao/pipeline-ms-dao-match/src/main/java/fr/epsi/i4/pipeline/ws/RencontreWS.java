package fr.epsi.i4.pipeline.ws;

import com.thomaskint.minidao.enumeration.MDConditionOperator;
import com.thomaskint.minidao.exception.MDException;
import com.thomaskint.minidao.querybuilder.MDCondition;
import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;
import fr.epsi.i4.pipeline.model.bdd.rencontre.RencontreDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thomas Kint
 */
@RestController
public class RencontreWS extends WebService {

	@GetMapping("/rencontre")
	public List<RencontreDetail> getRencontres() {
		return getEntities(RencontreDetail.class);
	}

	@GetMapping("/rencontre/{id}")
	public RencontreDetail getRencontreById(@PathVariable("id") int id) {
		return getEntityById(RencontreDetail.class, id);
	}

	@GetMapping("/rencontre/court/{id}")
	public List<RencontreDetail> getRencontresByCourtId(@PathVariable("id") int id) {
		List<RencontreDetail> rencontres = null;
		try {
			MDCondition condition = new MDCondition(Rencontre.idCourtFieldName, MDConditionOperator.EQUAL, id);
			rencontres = getMiniDAO().read().getEntities(RencontreDetail.class, condition);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return rencontres;
	}

	@PostMapping("/rencontre")
	public boolean createRencontre(@RequestBody RencontreDetail rencontre) {
		return createEntity(rencontre);
	}

	@PutMapping("/rencontre")
	public boolean updateRencontre(@RequestBody RencontreDetail rencontre) {
		return updateEntity(rencontre);
	}

	@DeleteMapping("/rencontre")
	public boolean deleteRencontre(@RequestBody RencontreDetail rencontre) {
		return deleteEntity(rencontre);
	}

	@DeleteMapping("/rencontre/{id}")
	public boolean deleteRencontreById(@PathVariable("id") int id) {
		return deleteEntityById(RencontreDetail.class, id);
	}
}

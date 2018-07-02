package fr.epsi.i4.pipeline.ws;

import com.thomaskint.minidao.enumeration.MDConditionOperator;
import com.thomaskint.minidao.exception.MDException;
import com.thomaskint.minidao.querybuilder.MDCondition;
import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;
import fr.epsi.i4.pipeline.model.bdd.rencontre.RencontreDetail;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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

	@PostMapping("/rencontre/{id}/play")
	public Rencontre playRencontre(@PathVariable("id") int id) {
		Rencontre rencontre = getEntityById(Rencontre.class, id);
		Timestamp currentTimestamp = getCurrentTimestamp();
		if (rencontre.isStarted() && rencontre.isPaused()) {
			rencontre.enPause = new BigDecimal(0);
			rencontre.dateDerniereReprise = currentTimestamp;
			updateEntity(rencontre);
		} else if (!rencontre.isStarted()) {
			rencontre.dateDebut = currentTimestamp;
			rencontre.dateDerniereReprise = currentTimestamp;
			updateEntity(rencontre);
		}
		return rencontre;
	}

	@PostMapping("/rencontre/{id}/pause")
	public Rencontre pauseRencontre(@PathVariable("id") int id) {
		Rencontre rencontre = getEntityById(Rencontre.class, id);

		Timestamp currentTimestamp = getCurrentTimestamp();
		long dureeJeu = currentTimestamp.getTime();
		dureeJeu -= rencontre.dateDerniereReprise.getTime();
		if (rencontre.dureeJeu == null) {
			rencontre.dureeJeu = BigDecimal.valueOf(dureeJeu);
		} else {
			rencontre.dureeJeu = rencontre.dureeJeu.add(BigDecimal.valueOf(dureeJeu));
		}

		rencontre.enPause = new BigDecimal(1);
		updateEntity(rencontre);
		return rencontre;
	}

	@PostMapping("/rencontre/{id}/end")
	public Rencontre endRencontre(@PathVariable("id") int id) {
		Rencontre rencontre = getEntityById(Rencontre.class, id);
		rencontre.dateFin = getCurrentTimestamp();
		updateEntity(rencontre);
		return rencontre;
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

	private Timestamp getCurrentTimestamp() {
		return new Timestamp(new Date().getTime());
	}
}

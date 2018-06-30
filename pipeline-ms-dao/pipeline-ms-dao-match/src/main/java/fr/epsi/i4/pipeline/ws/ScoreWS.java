package fr.epsi.i4.pipeline.ws;

import fr.epsi.i4.pipeline.model.Score;
import fr.epsi.i4.pipeline.model.bdd.equipe.Equipe;
import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thomas Kint
 */
@RestController
public class ScoreWS extends WebService {

	@GetMapping("/score/{idRencontre}")
	public Score getScoreRencontre(@PathVariable("idRencontre") int idRencontre) {
		Score score = new Score();
		Rencontre rencontre = getEntityById(Rencontre.class, idRencontre);
		if (rencontre != null) {
			 score = Score.fromRencontre(rencontre);
		}
		return score;
	}
}

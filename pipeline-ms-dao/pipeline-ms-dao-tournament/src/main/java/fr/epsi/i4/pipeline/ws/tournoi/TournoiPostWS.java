package fr.epsi.i4.pipeline.ws.tournoi;

import fr.epsi.i4.pipeline.model.bdd.Tournoi;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class TournoiPostWS extends WebService {

	@PostMapping("/tournoi")
	public boolean createTournoi(@RequestBody Tournoi tournoi) {
		return createEntity(tournoi);
	}
}

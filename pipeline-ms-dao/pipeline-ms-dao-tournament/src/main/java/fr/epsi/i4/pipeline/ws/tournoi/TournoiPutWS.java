package fr.epsi.i4.pipeline.ws.tournoi;

import fr.epsi.i4.pipeline.model.bdd.Tournoi;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class TournoiPutWS extends WebService {

	@PutMapping("/tournoi")
	public boolean updateTournoi(@RequestBody Tournoi tournoi) {
		return updateEntity(tournoi);
	}
}

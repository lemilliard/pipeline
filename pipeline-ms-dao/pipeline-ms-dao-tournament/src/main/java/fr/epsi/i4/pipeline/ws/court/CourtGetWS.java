package fr.epsi.i4.pipeline.ws.court;

import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.bdd.Court;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class CourtGetWS extends WebService {

	@GetMapping("/court")
	public List<Court> getCourts() {
		List<Court> roles = null;
		try {
			roles = getMiniDAO().read().getEntities(Court.class);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return roles;
	}

	@GetMapping("/court/{id}")
	public Court getCourtById(@PathVariable("id") int id) {
		Court role = null;
		try {
			role = getMiniDAO().read().getEntityById(Court.class, id);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return role;
	}
}

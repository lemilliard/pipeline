package fr.epsi.i4.pipeline.ws.niveau;

import fr.epsi.i4.pipeline.model.bdd.tournoi.Niveau;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Thomas Kint
 */
@RestController
public class NiveauGetWS extends WebService {

	@GetMapping("/niveau")
	public List<Niveau> getNiveaux() {
		return getEntities(Niveau.class);
	}

	@GetMapping("/niveau/{name}")
	public Niveau getNiveauByName(@PathVariable("name") String name) {
		return getEntityById(Niveau.class, name.toUpperCase());
	}
}

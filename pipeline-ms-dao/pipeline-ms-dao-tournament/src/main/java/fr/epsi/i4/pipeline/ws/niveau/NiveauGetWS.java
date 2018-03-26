package fr.epsi.i4.pipeline.ws.niveau;

import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.bdd.Niveau;
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
		List<Niveau> niveaux = null;
		try {
			niveaux = getMiniDAO().read().getEntities(Niveau.class);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return niveaux;
	}

	@GetMapping("/niveau/{name}")
	public Niveau getNiveauByName(@PathVariable("name") String name) {
		Niveau niveau = null;
		try {
			niveau = getMiniDAO().read().getEntityById(Niveau.class, name.toUpperCase());
		} catch (MDException e) {
			e.printStackTrace();
		}
		return niveau;
	}
}

package fr.epsi.i4.pipeline.ws.complexe;

import fr.epsi.i4.pipeline.model.bdd.complexe.Complexe;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Thomas Kint
 */
@RestController
public class ComplexeGetWS extends WebService {

	@GetMapping("/complexe")
	public List<Complexe> getComplexes() {
		return getEntities(Complexe.class);
	}

	@GetMapping("/complexe/{id}")
	public Complexe getComplexeById(@PathVariable("id") int id) {
		return getEntityById(Complexe.class, id);
	}
}

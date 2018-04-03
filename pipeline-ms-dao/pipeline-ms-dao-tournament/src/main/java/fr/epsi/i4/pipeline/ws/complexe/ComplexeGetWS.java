package fr.epsi.i4.pipeline.ws.complexe;

import com.thomaskint.minidao.exception.MDException;
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
		List<Complexe> complexes = null;
		try {
			complexes = getMiniDAO().read().getEntities(Complexe.class);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return complexes;
	}

	@GetMapping("/complexe/{id}")
	public Complexe getComplexeById(@PathVariable("id") int id) {
		Complexe complexe = null;
		try {
			complexe = getMiniDAO().read().getEntityById(Complexe.class, id);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return complexe;
	}
}

package fr.epsi.i4.pipeline.ws;

import com.thomaskint.minidao.enumeration.MDConditionOperator;
import com.thomaskint.minidao.exception.MDException;
import com.thomaskint.minidao.querybuilder.MDCondition;
import fr.epsi.i4.pipeline.model.bdd.equipe.Equipe;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thomas Kint
 */
@RestController
public class EquipeWS extends WebService {

	@GetMapping("/equipe")
	public List<Equipe> getEquipes() {
		return getEntities(Equipe.class);
	}

	@GetMapping("/equipe/{id}")
	public Equipe getEquipeById(@PathVariable("id") int id) {
		return getEntityById(Equipe.class, id);
	}

	@PostMapping("/equipe")
	public boolean createEquipe(@RequestBody Equipe equipe) {
		return createEntity(equipe);
	}

	@PutMapping("/equipe")
	public boolean updateEquipe(@RequestBody Equipe equipe) {
		return updateEntity(equipe);
	}

	@DeleteMapping("/equipe")
	public boolean deleteEquipe(@RequestBody Equipe equipe) {
		return deleteEntity(equipe);
	}

	@DeleteMapping("/equipe/{id}")
	public boolean deleteEquipeById(@PathVariable("id") int id) {
		return deleteEntityById(Equipe.class, id);
	}
}

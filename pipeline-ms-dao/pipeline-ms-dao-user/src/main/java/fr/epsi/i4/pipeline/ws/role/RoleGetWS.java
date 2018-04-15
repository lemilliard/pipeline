package fr.epsi.i4.pipeline.ws.role;

import fr.epsi.i4.pipeline.model.bdd.user.Role;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class RoleGetWS extends WebService {

	@GetMapping("/role")
	public List<Role> getRoles() {
		return getEntities(Role.class);
	}

	@GetMapping("/role/{name}")
	public Role getRole(@PathVariable("name") String name) {
		return getEntityById(Role.class, name.toUpperCase());
	}
}

package fr.epsi.i4.pipeline.ws;

import fr.epsi.i4.pipeline.model.bdd.user.Role;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class RoleWS extends WebService {

	@GetMapping("/role")
	public List<Role> getRoles() {
		return getEntities(Role.class);
	}

	@GetMapping("/role/{value}")
	public Role getRole(@PathVariable("value") String value) {
		return getEntityById(Role.class, value.toUpperCase());
	}

	@PostMapping("/role")
	public Role createRole(@RequestBody Role role) {
		return createAndReturnEntity(role);
	}

	@DeleteMapping("/role/{value}")
	public boolean deleteRoleByName(@PathVariable("value") String value) {
		return deleteEntityById(Role.class, value);
	}
}

package fr.epsi.i4.pipeline.ws.role;

import fr.epsi.i4.pipeline.model.bdd.user.Role;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * Created by tkint on 25/01/2018.
 */
@RestController
public class RoleDeleteWS extends WebService {

	@DeleteMapping("/role/{name}")
	public boolean deleteRole(@PathParam("name") String name) {
		return deleteEntityById(Role.class, name);
	}
}

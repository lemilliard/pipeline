package fr.epsi.i4.pipeline.ws;

import fr.epsi.i4.pipeline.model.bdd.user.Privilege;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class PrivilegeWS extends WebService {

	@GetMapping("/privilege")
	public List<Privilege> getPrivileges() {
		return getEntities(Privilege.class);
	}

	@GetMapping("/privilege/{name}")
	public Privilege getPrivilege(@PathVariable("name") String name) {
		return getEntityById(Privilege.class, name.toUpperCase());
	}

	@PostMapping("/privilege")
	public boolean createPrivilege(@RequestBody Privilege privilege) {
		return createEntity(privilege);
	}

	@DeleteMapping("/privilege/{name}")
	public boolean deletePrivilege(@PathParam("name") String name) {
		return deleteEntityById(Privilege.class, name);
	}
}

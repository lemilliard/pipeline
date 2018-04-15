package fr.epsi.i4.pipeline.ws.privilege;

import fr.epsi.i4.pipeline.model.bdd.user.Privilege;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class PrivilegeGetWS extends WebService {

	@GetMapping("/privilege")
	public List<Privilege> getPrivileges() {
		return getEntities(Privilege.class);
	}

	@GetMapping("/privilege/{name}")
	public Privilege getPrivilege(@PathVariable("name") String name) {
		return getEntityById(Privilege.class, name.toUpperCase());
	}
}

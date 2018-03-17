package fr.epsi.i4.pipeline.ws.role;

import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.Role;
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
		List<Role> roles = null;
		try {
			roles = getMiniDAO().read().getEntities(Role.class);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return roles;
	}

	@GetMapping("/role/{name}")
	public Role getRole(@PathVariable("name") String name) {
		Role role = null;
		try {
			role = getMiniDAO().read().getEntityById(Role.class, name.toUpperCase());
		} catch (MDException e) {
			e.printStackTrace();
		}
		return role;
	}
}

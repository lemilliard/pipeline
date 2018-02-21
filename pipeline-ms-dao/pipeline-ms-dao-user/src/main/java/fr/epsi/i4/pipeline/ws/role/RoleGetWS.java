package fr.epsi.i4.pipeline.ws.role;

import com.thomaskint.minidao.MiniDAO;
import fr.epsi.i4.pipeline.model.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class RoleGetWS {

	@GetMapping("/role")
	public List<Role> getRoles() {
		List<Role> roles = null;
		try {
			roles = MiniDAO.getEntities(Role.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}

	@GetMapping("/role/{name}")
	public Role getRole(@PathVariable("name") String name) {
		Role role = null;
		try {
			role = MiniDAO.getEntityById(Role.class, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}
}

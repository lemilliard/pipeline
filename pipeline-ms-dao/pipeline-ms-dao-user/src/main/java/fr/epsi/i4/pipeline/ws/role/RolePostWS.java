package fr.epsi.i4.pipeline.ws.role;

import com.thomaskint.minidao.MiniDAO;
import fr.epsi.i4.pipeline.model.Role;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class RolePostWS {

	@PostMapping("/role")
	public boolean createRole(@RequestBody Role role) {
		boolean created = false;
		try {
			created = MiniDAO.createEntity(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return created;
	}
}

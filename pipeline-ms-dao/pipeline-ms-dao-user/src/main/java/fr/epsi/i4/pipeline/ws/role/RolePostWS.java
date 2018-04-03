package fr.epsi.i4.pipeline.ws.role;

import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.bdd.user.Role;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class RolePostWS extends WebService {

	@PostMapping("/role")
	public boolean createRole(@RequestBody Role role) {
		boolean created = false;
		try {
			created = getMiniDAO().create().createEntity(role);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return created;
	}
}

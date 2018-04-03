package fr.epsi.i4.pipeline.ws.role;

import com.thomaskint.minidao.exception.MDException;
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
		boolean deleted = false;
		try {
			deleted = getMiniDAO().delete().deleteEntityById(Role.class, name);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return deleted;
	}
}

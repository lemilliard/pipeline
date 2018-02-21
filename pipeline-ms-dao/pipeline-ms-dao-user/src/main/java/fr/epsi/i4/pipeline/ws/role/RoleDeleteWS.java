package fr.epsi.i4.pipeline.ws.role;

import com.thomaskint.minidao.MiniDAO;
import fr.epsi.i4.pipeline.model.Role;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * Created by tkint on 25/01/2018.
 */
@RestController
public class RoleDeleteWS {

	@DeleteMapping("/role/{id_role}")
	public boolean deleteRole(@PathParam("id_role") int idRole) {
		boolean deleted = false;
		try {
			deleted = MiniDAO.deleteEntity(Role.class, idRole);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleted;
	}
}

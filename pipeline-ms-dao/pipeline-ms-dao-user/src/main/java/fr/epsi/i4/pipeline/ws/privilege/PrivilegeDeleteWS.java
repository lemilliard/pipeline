package fr.epsi.i4.pipeline.ws.privilege;

import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.bdd.user.Privilege;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * Created by tkint on 25/01/2018.
 */
@RestController
public class PrivilegeDeleteWS extends WebService {

	@DeleteMapping("/privilege/{name}")
	public boolean deletePrivilege(@PathParam("name") String name) {
		boolean deleted = false;
		try {
			deleted = getMiniDAO().delete().deleteEntityById(Privilege.class, name);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return deleted;
	}
}

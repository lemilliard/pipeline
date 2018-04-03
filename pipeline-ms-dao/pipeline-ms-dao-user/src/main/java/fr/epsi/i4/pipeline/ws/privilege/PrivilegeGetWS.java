package fr.epsi.i4.pipeline.ws.privilege;

import com.thomaskint.minidao.exception.MDException;
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
		List<Privilege> privileges = null;
		try {
			privileges = getMiniDAO().read().getEntities(Privilege.class);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return privileges;
	}

	@GetMapping("/privilege/{name}")
	public Privilege getPrivilege(@PathVariable("name") String name) {
		Privilege privilege = null;
		try {
			privilege = getMiniDAO().read().getEntityById(Privilege.class, name.toUpperCase());
		} catch (MDException e) {
			e.printStackTrace();
		}
		return privilege;
	}
}

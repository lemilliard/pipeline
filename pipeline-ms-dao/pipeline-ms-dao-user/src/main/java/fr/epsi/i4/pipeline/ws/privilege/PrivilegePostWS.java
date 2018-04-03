package fr.epsi.i4.pipeline.ws.privilege;

import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.bdd.user.Privilege;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class PrivilegePostWS extends WebService {

	@PostMapping("/privilege")
	public boolean createPrivilege(@RequestBody Privilege privilege) {
		boolean created = false;
		try {
			created = getMiniDAO().create().createEntity(privilege);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return created;
	}
}

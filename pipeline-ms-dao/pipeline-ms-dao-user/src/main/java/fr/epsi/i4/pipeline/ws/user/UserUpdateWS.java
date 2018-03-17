package fr.epsi.i4.pipeline.ws.user;

import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.User;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 25/01/2018.
 */
@RestController
public class UserUpdateWS extends WebService {

	@PutMapping("/user")
	public boolean updateUser(@RequestBody User user) {
		boolean updated = false;
		try {
			updated = getMiniDAO().update().updateEntity(user);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return updated;
	}
}

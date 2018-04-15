package fr.epsi.i4.pipeline.ws.user;

import fr.epsi.i4.pipeline.model.bdd.user.User;
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
	public User updateUser(@RequestBody User user) {
		User updatedUser = null;
		if (updateEntity(user)) {
			updatedUser = getEntityById(User.class, user.id_user);
		}
		return updatedUser;
	}
}

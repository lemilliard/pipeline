package fr.epsi.i4.pipeline.ws.user;

import fr.epsi.i4.pipeline.model.bdd.user.User;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 25/01/2018.
 */
@RestController
public class UserDeleteWS extends WebService {

	@DeleteMapping("/user/{id}")
	public boolean deleteUser(@PathVariable("id") int id) {
		return deleteEntityById(User.class, id);
	}
}

package fr.epsi.i4.pipeline.ws.user;

import fr.epsi.i4.pipeline.model.bdd.user.User;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class UserGetWS extends WebService {

	@GetMapping("/user")
	public List<User> getUsers() {
		return getEntities(User.class);
	}

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") int id) {
		return getEntityById(User.class, id);
	}
}

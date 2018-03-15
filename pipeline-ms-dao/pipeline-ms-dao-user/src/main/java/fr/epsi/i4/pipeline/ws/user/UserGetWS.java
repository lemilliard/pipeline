package fr.epsi.i4.pipeline.ws.user;

import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.User;
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
		List<User> users = null;
		try {
			users = getMiniDAO().read().getEntities(User.class);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return users;
	}

	@GetMapping("/user/{id_user}")
	public User getUser(@PathVariable("id_user") int idUser) {
		User user = null;
		try {
			user = getMiniDAO().read().getEntityById(User.class, idUser);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return user;
	}
}

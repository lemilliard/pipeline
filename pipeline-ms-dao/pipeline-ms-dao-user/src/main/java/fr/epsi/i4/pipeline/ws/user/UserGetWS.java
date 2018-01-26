package fr.epsi.i4.pipeline.ws.user;

import com.thomaskint.minidao.MiniDAO;
import fr.epsi.i4.pipeline.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class UserGetWS {

	@GetMapping("/user")
	public List<User> getUsers() {
		List<User> users = null;
		try {
			users = MiniDAO.getEntities(User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@GetMapping("/user/{id_user}")
	public User getUser(@PathVariable("id_user") int idUser) {
		User user = null;
		try {
			user = MiniDAO.getEntityById(User.class, idUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}

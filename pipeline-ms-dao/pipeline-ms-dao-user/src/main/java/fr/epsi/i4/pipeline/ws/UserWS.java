package fr.epsi.i4.pipeline.ws;

import com.thomaskint.minidao.exception.MDException;
import com.thomaskint.minidao.querybuilder.MDCondition;
import fr.epsi.i4.pipeline.model.Connector;
import fr.epsi.i4.pipeline.model.Registrator;
import fr.epsi.i4.pipeline.model.bdd.user.User;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.thomaskint.minidao.enumeration.MDConditionLink.AND;
import static com.thomaskint.minidao.enumeration.MDConditionOperator.EQUAL;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class UserWS extends WebService {

	@GetMapping("/user")
	public List<User> getUsers() {
		return getEntities(User.class);
	}

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") int id) {
		return getEntityById(User.class, id);
	}

	@PostMapping("/user/connect")
	public User connectUser(@RequestBody Connector connector) {
		User user = null;
		try {
			MDCondition subCondition = new MDCondition(User.passwordFieldName, EQUAL, connector.password);
			MDCondition condition = new MDCondition(User.emailFieldName, EQUAL, connector.email, AND, subCondition);

			List<User> users = getMiniDAO().read().getEntities(User.class, condition);

			if (users.size() > 0) {
				user = users.get(0);
			}
		} catch (MDException e) {
			e.printStackTrace();
		}
		return user;
	}

	@PostMapping("/user/register")
	public User registerUser(@RequestBody Registrator registrator) {
		Connector connector;
		User user = null;
		try {
			connector = registrator.toConnector();
			if (registrator.isValid()
					&& connectUser(connector) == null
					&& getMiniDAO().create().createEntity(registrator.toUser())) {
				user = connectUser(connector);
			}
		} catch (MDException e) {
			e.printStackTrace();
		}
		return user;
	}

	@PostMapping("/user")
	public boolean createUser(@RequestBody User user) {
		return createEntity(user);
	}

	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		User updatedUser = null;
		if (updateEntity(user)) {
			updatedUser = getEntityById(User.class, user.idUser);
		}
		return updatedUser;
	}

	@DeleteMapping("/user/{id}")
	public boolean deleteUser(@PathVariable("id") int id) {
		return deleteEntityById(User.class, id);
	}
}

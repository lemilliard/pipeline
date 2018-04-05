package fr.epsi.i4.pipeline.ws.user;

import com.thomaskint.minidao.exception.MDException;
import com.thomaskint.minidao.querybuilder.MDCondition;
import fr.epsi.i4.pipeline.model.Connector;
import fr.epsi.i4.pipeline.model.Registrator;
import fr.epsi.i4.pipeline.model.bdd.user.User;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.thomaskint.minidao.enumeration.MDConditionLink.AND;
import static com.thomaskint.minidao.enumeration.MDConditionOperator.EQUAL;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class UserPostWS extends WebService {

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
		boolean created = false;
		try {
			created = getMiniDAO().create().createEntity(user);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return created;
	}
}

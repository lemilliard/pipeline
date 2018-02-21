package fr.epsi.i4.pipeline.ws.user;

import com.thomaskint.minidao.MiniDAO;
import com.thomaskint.minidao.enumeration.MDConditionLink;
import com.thomaskint.minidao.query.MDCondition;
import fr.epsi.i4.pipeline.model.Connector;
import fr.epsi.i4.pipeline.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class UserPostWS {

	@PostMapping("/user/connect")
	public User getUserByConnector(@RequestBody Connector connector) {
		User user = null;
		try {
			MDCondition mdCondition1 = new MDCondition(User.emailField, connector.email);
			MDCondition mdCondition2 = new MDCondition(User.passwordField, connector.password);
			MDCondition mdCondition = new MDCondition(mdCondition1, MDConditionLink.AND, mdCondition2);

			List<User> users = MiniDAO.getEntities(User.class, mdCondition);

			if (users.size() > 0) {
				user = users.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@PostMapping("/user")
	public boolean createUser(@RequestBody User user) {
		boolean created = false;
		try {
			created = MiniDAO.createEntity(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return created;
	}
}

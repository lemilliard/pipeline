package fr.epsi.i4.pipeline.ws.user;

import com.thomaskint.minidao.MiniDAO;
import fr.epsi.i4.pipeline.model.User;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 25/01/2018.
 */
@RestController
public class UserUpdateWS {

	@PutMapping("/user")
	public boolean deleteUser(@RequestBody User user) {
		boolean deleted = false;
		try {
			deleted = MiniDAO.updateEntity(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleted;
	}
}

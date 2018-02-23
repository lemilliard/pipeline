package fr.epsi.i4.pipeline.ws.user;

import com.thomaskint.minidao.MiniDAO;
import fr.epsi.i4.pipeline.model.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * Created by tkint on 25/01/2018.
 */
@RestController
public class UserDeleteWS {

	@DeleteMapping("/user/{id_user}")
	public boolean deleteUser(@PathVariable("id_user") int idUser) {
		boolean deleted = false;
		try {
			deleted = MiniDAO.deleteEntity(User.class, idUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleted;
	}
}

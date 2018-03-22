package fr.epsi.i4.pipeline.ws.user;

import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.bdd.User;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 25/01/2018.
 */
@RestController
public class UserDeleteWS extends WebService {

	@DeleteMapping("/user/{id_user}")
	public boolean deleteUser(@PathVariable("id_user") int idUser) {
		boolean deleted = false;
		try {
			deleted = getMiniDAO().delete().deleteEntityById(User.class, idUser);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return deleted;
	}
}

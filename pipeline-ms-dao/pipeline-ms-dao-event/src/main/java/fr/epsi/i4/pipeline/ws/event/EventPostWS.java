package fr.epsi.i4.pipeline.ws.event;

import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.Event;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class EventPostWS extends WebService {

	@PostMapping("/event")
	public boolean createEvent(@RequestBody Event event) {
		boolean created = false;
		try {
			created = getMiniDAO().create().createEntity(event);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return created;
	}
}

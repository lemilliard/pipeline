package fr.epsi.i4.pipeline.ws.event;

import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.bdd.Event;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 25/01/2018.
 */
@RestController
public class EventUpdateWS extends WebService {

	@PutMapping("/event")
	public boolean deleteEvent(@RequestBody Event event) {
		boolean deleted = false;
		try {
			deleted = getMiniDAO().update().updateEntity(event);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return deleted;
	}
}

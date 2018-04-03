package fr.epsi.i4.pipeline.ws.event;

import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.bdd.event.Event;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 25/01/2018.
 */
@RestController
public class EventDeleteWS extends WebService {

	@DeleteMapping("/event/{id_event}")
	public boolean deleteEvent(@PathVariable("id_event") int idEvent) {
		boolean deleted = false;
		try {
			deleted = getMiniDAO().delete().deleteEntityById(Event.class, idEvent);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return deleted;
	}
}

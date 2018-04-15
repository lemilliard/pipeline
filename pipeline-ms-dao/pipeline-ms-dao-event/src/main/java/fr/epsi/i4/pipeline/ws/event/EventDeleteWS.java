package fr.epsi.i4.pipeline.ws.event;

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

	@DeleteMapping("/event/{id}")
	public boolean deleteEvent(@PathVariable("id") int id) {
		return deleteEntityById(Event.class, id);
	}
}

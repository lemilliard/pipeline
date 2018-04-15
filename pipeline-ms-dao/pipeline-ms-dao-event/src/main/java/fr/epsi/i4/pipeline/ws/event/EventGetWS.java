package fr.epsi.i4.pipeline.ws.event;

import fr.epsi.i4.pipeline.model.bdd.event.Event;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class EventGetWS extends WebService {

	@GetMapping("/event")
	public List<Event> getEvents() {
		return getEntities(Event.class);
	}

	@GetMapping("/event/{id}")
	public Event getEvent(@PathVariable("id") int id) {
		return getEntityById(Event.class, id);
	}
}

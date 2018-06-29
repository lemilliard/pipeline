package fr.epsi.i4.pipeline.ws;

import fr.epsi.i4.pipeline.model.bdd.event.Event;
import fr.epsi.i4.pipeline.ws.WebService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class EventWS extends WebService {

	@GetMapping("/event")
	public List<Event> getEvents() {
		return getEntities(Event.class);
	}

	@GetMapping("/event/{id}")
	public Event getEvent(@PathVariable("id") int id) {
		return getEntityById(Event.class, id);
	}

	@PostMapping("/event")
	public boolean createEvent(@RequestBody Event event) {
		return createEntity(event);
	}

	@PutMapping("/event")
	public boolean deleteEvent(@RequestBody Event event) {
		return updateEntity(event);
	}

	@DeleteMapping("/event/{id}")
	public boolean deleteEvent(@PathVariable("id") int id) {
		return deleteEntityById(Event.class, id);
	}
}

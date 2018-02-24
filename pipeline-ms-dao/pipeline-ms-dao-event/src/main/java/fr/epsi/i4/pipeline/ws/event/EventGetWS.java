package fr.epsi.i4.pipeline.ws.event;

import com.thomaskint.minidao.MiniDAO;
import fr.epsi.i4.pipeline.model.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class EventGetWS {

	@GetMapping("/event")
	public List<Event> getEvents() {
		List<Event> events = null;
		try {
			events = MiniDAO.getEntities(Event.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return events;
	}

	@GetMapping("/event/{id_event}")
	public Event getEvent(@PathVariable("id_event") int idEvent) {
		Event event = null;
		try {
			event = MiniDAO.getEntityById(Event.class, idEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;
	}
}

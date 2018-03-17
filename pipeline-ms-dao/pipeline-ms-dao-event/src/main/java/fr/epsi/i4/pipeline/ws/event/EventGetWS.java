package fr.epsi.i4.pipeline.ws.event;

import com.thomaskint.minidao.MiniDAO;
import com.thomaskint.minidao.exception.MDException;
import fr.epsi.i4.pipeline.model.Event;
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
		List<Event> events = null;
		try {
			events = getMiniDAO().read().getEntities(Event.class);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return events;
	}

	@GetMapping("/event/{id_event}")
	public Event getEvent(@PathVariable("id_event") int idEvent) {
		Event event = null;
		try {
			event = getMiniDAO().read().getEntityById(Event.class, idEvent);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return event;
	}
}

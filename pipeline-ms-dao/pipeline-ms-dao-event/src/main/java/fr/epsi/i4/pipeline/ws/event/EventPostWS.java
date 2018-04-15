package fr.epsi.i4.pipeline.ws.event;

import fr.epsi.i4.pipeline.model.bdd.event.Event;
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
		return createEntity(event);
	}
}

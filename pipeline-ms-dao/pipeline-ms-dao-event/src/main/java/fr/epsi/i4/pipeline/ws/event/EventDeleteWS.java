package fr.epsi.i4.pipeline.ws.event;

import com.thomaskint.minidao.MiniDAO;
import fr.epsi.i4.pipeline.model.Event;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 25/01/2018.
 */
@RestController
public class EventDeleteWS {

	@DeleteMapping("/event/{id_event}")
	public boolean deleteEvent(@PathVariable("id_event") int idEvent) {
		boolean deleted = false;
		try {
			deleted = MiniDAO.deleteEntity(Event.class, idEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleted;
	}
}

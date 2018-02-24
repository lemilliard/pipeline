package fr.epsi.i4.pipeline.ws.event;

import com.thomaskint.minidao.MiniDAO;
import fr.epsi.i4.pipeline.model.Event;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tkint on 25/01/2018.
 */
@RestController
public class EventUpdateWS {

	@PutMapping("/event")
	public boolean deleteEvent(@RequestBody Event event) {
		boolean deleted = false;
		try {
			deleted = MiniDAO.updateEntity(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleted;
	}
}

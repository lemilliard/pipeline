package fr.epsi.i4.pipeline.ws.event;

import com.thomaskint.minidao.MiniDAO;
import com.thomaskint.minidao.enumeration.MDConditionLink;
import com.thomaskint.minidao.query.MDCondition;
import fr.epsi.i4.pipeline.model.Event;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tkint on 26/01/2018.
 */
@RestController
public class EventPostWS {

	@PostMapping("/event")
	public boolean createEvent(@RequestBody Event event) {
		boolean created = false;
		try {
			created = MiniDAO.createEntity(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return created;
	}
}

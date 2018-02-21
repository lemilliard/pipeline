package fr.epsi.i4.pipeline.endpoint;

import fr.epsi.i4.pipeline.model.Demand;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by tkint on 21/02/2018.
 */
@ServerEndpoint(value = "/pipeline")
public class UserEndpoint {

	@OnMessage
	public String onMessage(String message, Session session) {
		Demand demand = Demand.fromMessage(message);
		return demand.toString();
	}
}

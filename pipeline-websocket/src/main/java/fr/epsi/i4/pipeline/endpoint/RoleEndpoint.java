package fr.epsi.i4.pipeline.endpoint;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by tkint on 21/02/2018.
 */
@ServerEndpoint(value = "/role")
public class RoleEndpoint {

	@OnMessage
	public String onMessage(String message, Session session) {
		return "Role";
	}
}

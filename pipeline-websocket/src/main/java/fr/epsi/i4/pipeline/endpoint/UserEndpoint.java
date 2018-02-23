package fr.epsi.i4.pipeline.endpoint;

import fr.epsi.i4.pipeline.ms.MicroServiceClient;
import fr.epsi.i4.pipeline.ms.client.UserMicroService;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by tkint on 21/02/2018.
 */
@ServerEndpoint(value = "/user")
public class UserEndpoint {

	@OnMessage
	public String onMessage(String message, Session session) throws Exception {
		MicroServiceClient client = new MicroServiceClient();
		return client.processMessage(UserMicroService.name, message);
	}
}

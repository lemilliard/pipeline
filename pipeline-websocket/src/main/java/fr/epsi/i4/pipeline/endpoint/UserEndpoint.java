package fr.epsi.i4.pipeline.endpoint;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import fr.epsi.i4.pipeline.decoder.RequestDecoder;
import fr.epsi.i4.pipeline.microservice.MicroServiceClient;
import fr.epsi.i4.pipeline.microservice.UserMicroService;
import fr.epsi.i4.pipeline.model.Request;

/**
 * Created by tkint on 21/02/2018.
 */
@ServerEndpoint(value = "/user", decoders = RequestDecoder.class)
public class UserEndpoint {

	@OnMessage public String onMessage(Request request, Session session) throws Exception {
		MicroServiceClient client = new MicroServiceClient();
		return client.processRequest(UserMicroService.class, request);
	}
}

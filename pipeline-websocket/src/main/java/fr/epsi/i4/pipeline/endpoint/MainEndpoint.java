package fr.epsi.i4.pipeline.endpoint;

import fr.epsi.i4.pipeline.common.Data;
import fr.epsi.i4.pipeline.decoder.RequestDecoder;
import fr.epsi.i4.pipeline.encoder.ResponseEncoder;
import fr.epsi.i4.pipeline.microservice.MicroService;
import fr.epsi.i4.pipeline.model.Request;
import fr.epsi.i4.pipeline.model.Response;
import fr.epsi.i4.pipeline.model.registry.Registry;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by tkint on 21/02/2018.
 */
@ServerEndpoint(value = "/", decoders = RequestDecoder.class, encoders = ResponseEncoder.class)
public class MainEndpoint {

	@OnOpen
	public void onOpen(Session session) throws IOException {
		System.out.println("Opening " + session.getId());
		Data.sessions.add(session);
	}

	@OnMessage
	public Response onMessage(Request request, Session session) throws Exception {
		MicroService client = new MicroService();
		return client.processRequest(request, session);
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("Closing " + session.getId());
		Data.sessions.remove(session);
		for (Registry registry : MicroService.registries) {
			registry.removeSession(session);
		}
	}
}

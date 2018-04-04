package fr.epsi.i4.pipeline.endpoint;

import java.io.IOException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import fr.epsi.i4.pipeline.common.Data;
import fr.epsi.i4.pipeline.decoder.RequestDecoder;
import fr.epsi.i4.pipeline.encoder.ResponseEncoder;
import fr.epsi.i4.pipeline.microservice.MicroService;
import fr.epsi.i4.pipeline.model.Request;
import fr.epsi.i4.pipeline.model.Response;

/**
 * Created by tkint on 21/02/2018.
 */
@ServerEndpoint(value = "/", decoders = RequestDecoder.class, encoders = ResponseEncoder.class)
public class MainEndpoint {

	@OnOpen
	public void onOpen(Session session) throws IOException {
		Data.sessions.add(session);
	}

	@OnMessage
	public Response onMessage(Request request, Session session) throws Exception {
		MicroService client = new MicroService();
		return client.processRequest(request, session);
	}
}

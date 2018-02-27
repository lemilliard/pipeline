package fr.epsi.i4.pipeline.endpoint;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import fr.epsi.i4.pipeline.common.Data;

/**
 * Created by tkint on 21/02/2018.
 */
@ServerEndpoint(value = "/chat") public class ChatEndpoint {

	@OnOpen public void onOpen(Session session) throws IOException {
		for (Session s : Data.sessions) {
			if (s.isOpen()) {
				s.getBasicRemote().sendText("New Session: " + session.getId());
			}
		}
		Data.sessions.add(session);
	}

	@OnMessage public String onMessage(String message, Session session) throws Exception {
		String msg = session.getId() + ": " + message;
		for (Session s : Data.sessions) {
			if (!s.getId().equals(session.getId())) {
				s.getBasicRemote().sendText(msg);
			}
		}
		return msg;
	}

	@OnClose public void onClose(Session session) throws IOException {
		for (Session s : Data.sessions) {
			if (s.isOpen()) {
				s.getBasicRemote().sendText("Closed Session" + session.getId());
			}
		}
		Data.sessions.remove(session);
	}
}

package fr.epsi.i4.pipeline.encoder;

import com.google.gson.Gson;
import fr.epsi.i4.pipeline.model.Notification;
import fr.epsi.i4.pipeline.model.Response;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class NotificationEncoder implements Encoder.Text<Notification> {

	@Override
	public String encode(Notification notification) {
		Gson gson = new Gson();
		return gson.toJson(notification);
	}

	@Override
	public void init(EndpointConfig endpointConfig) {

	}

	@Override
	public void destroy() {

	}
}

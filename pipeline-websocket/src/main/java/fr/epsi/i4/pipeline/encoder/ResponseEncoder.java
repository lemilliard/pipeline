package fr.epsi.i4.pipeline.encoder;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;
import fr.epsi.i4.pipeline.model.Response;

public class ResponseEncoder implements Encoder.Text<Response> {

	@Override
	public String encode(Response response) {
		Gson gson = new Gson();
		return gson.toJson(response);
	}

	@Override
	public void init(EndpointConfig endpointConfig) {

	}

	@Override
	public void destroy() {

	}
}

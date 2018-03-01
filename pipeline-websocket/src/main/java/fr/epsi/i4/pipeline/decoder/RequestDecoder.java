package fr.epsi.i4.pipeline.decoder;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;
import fr.epsi.i4.pipeline.model.Request;

public class RequestDecoder implements Decoder.Text<Request> {

	@Override public Request decode(String s) throws DecodeException {
		Gson gson = new Gson();
		return gson.fromJson(s, Request.class);
	}

	@Override public boolean willDecode(String s) {
		return true;
	}

	@Override public void init(EndpointConfig endpointConfig) {

	}

	@Override public void destroy() {

	}
}

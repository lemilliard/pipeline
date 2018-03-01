package fr.epsi.i4.pipeline.decoder;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;
import fr.epsi.i4.pipeline.model.Message;

public class MessageDecoder implements Decoder.Text<Message> {

	@Override public Message decode(String s) throws DecodeException {
		Gson gson = new Gson();
		return gson.fromJson(s, Message.class);
	}

	@Override public boolean willDecode(String s) {
		return true;
	}

	@Override public void init(EndpointConfig endpointConfig) {

	}

	@Override public void destroy() {

	}
}

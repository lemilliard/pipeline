package fr.epsi.i4.pipeline.ms;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by tkint on 23/02/2018.
 */
public class Request {

	private String method;

	private String resource;

	private Map<String, Object> params;

	private Object body;

	public Request(String method, String resource, Map<String, Object> params, Object body) {
		this.method = method;
		this.resource = resource;
		this.params = params;
		this.body = body;
	}

	public String getMethod() {
		return method;
	}

	public String getResource() {
		return resource;
	}

	public Map getParams() {
		return params;
	}

	public Object getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "Request{" +
				"method='" + method + '\'' +
				", resource='" + resource + '\'' +
				", params=" + params +
				", body=" + body +
				'}';
	}
}

package fr.epsi.i4.pipeline.model;

import java.util.Map;

import fr.epsi.i4.pipeline.microservice.Method;

public class Request {

	private String method;

	private String resource;

	private Map<String, String> params;

	private Object body;

	public Request(String method, String resource) {
		this.method = method;
		this.resource = resource;
	}

	public Request(String method, String resource, Map<String, String> params) {
		this.method = method;
		this.resource = resource;
		this.params = params;
	}

	public Request(String method, String resource, Map<String, String> params, Object body) {
		this.method = method;
		this.resource = resource;
		this.params = params;
		this.body = body;
	}

	public Method getMethod() {
		Method method = Method.INVALID;
		if (Method.isValid(this.method)) {
			method = Method.valueOf(this.method);
		}
		return method;
	}

	public String getResource() {
		return resource;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public Object getBody() {
		return body;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}

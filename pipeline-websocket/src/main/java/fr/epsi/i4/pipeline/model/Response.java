package fr.epsi.i4.pipeline.model;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class Response {

	private Request request;

	private int httpCode;

	private ArrayList contents;

	private LinkedTreeMap content;

	private String error;

	public Response(Request request) {
		this.request = request;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object object) {
		if (object instanceof ArrayList) {
			this.contents = (ArrayList) object;
		} else if (object instanceof LinkedTreeMap) {
			this.content = (LinkedTreeMap) object;
		}
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}

package fr.epsi.i4.pipeline.model;

public class Response {

	private Request request;

	private int httpCode;

	private String content;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}

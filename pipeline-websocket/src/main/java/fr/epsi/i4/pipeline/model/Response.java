package fr.epsi.i4.pipeline.model;

public class Response<T> {

	private int httpCode;

	private T content;

	public Response(int httpCode, T content) {
		this.httpCode = httpCode;
		this.content = content;
	}
}

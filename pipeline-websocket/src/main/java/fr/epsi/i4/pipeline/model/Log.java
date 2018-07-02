package fr.epsi.i4.pipeline.model;

/**
 * @author Thomas Kint
 */
public class Log {

	public Request request;

	public int httpCode;

	public String error;

	public static Log fromResponse(Response response) {
		Log log = new Log();
		log.request = response.getRequest();
		log.httpCode = response.getHttpCode();
		log.error = response.getError();
		return log;
	}
}

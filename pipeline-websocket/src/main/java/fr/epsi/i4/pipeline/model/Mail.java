package fr.epsi.i4.pipeline.model;

/**
 * @author Thomas Kint
 */
public class Mail {

	public String to;

	public String subject;

    public String body;
    
    public Mail(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

	// public static Log fromResponse(Response response) {
	// 	Log log = new Log();
	// 	log.request = response.getRequest();
	// 	log.httpCode = response.getHttpCode();
	// 	log.error = response.getError();
	// 	return log;
	// }
}

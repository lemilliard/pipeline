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

}

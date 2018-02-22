package fr.epsi.i4.pipeline.ms.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by tkint on 22/02/2018.
 */
public class UserClient {

	private Client client;

	private WebResource webResource;

	public UserClient() {
		client = Client.create();
		webResource = client.resource("http://home.thomaskint.com:8085/ms-dao-user");
	}
}

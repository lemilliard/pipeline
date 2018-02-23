package fr.epsi.i4.pipeline.ms;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.epsi.i4.pipeline.ms.client.UserMicroService;

import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by tkint on 22/02/2018.
 */
public class MicroServiceClient {

	private Client client;

	private WebResource webResource;

	private static final String baseUrl = "http://home.thomaskint.com:8085";

	private static final MicroService[] microServices = new MicroService[] {
			new UserMicroService()
	};

	public MicroServiceClient() {
		client = Client.create();
	}

	public String processMessage(String microServiceName, String message) throws Exception {
		Gson gson = new Gson();
		Request request = gson.fromJson(message, Request.class);
		String method = request.getMethod();
		String resource = request.getResource();
		Map params = request.getParams();
		Object body = request.getBody();

		MicroService microService = getMicroServiceByName(microServiceName);
		String resourcePath = microService.getResourcePath(resource, params);
		webResource = client.resource(baseUrl + resourcePath);
		webResource.accept(MediaType.APPLICATION_JSON_TYPE);

		ClientResponse clientResponse = getClientResponse(method, body);
		if (clientResponse == null) {
			throw new Exception("Can't access resource");
		}
		return clientResponse.getEntity(String.class);
	}

	public static MicroService getMicroServiceByName(String microServiceName) {
		MicroService microService = null;
		int i = 0;
		while (i < microServices.length && microService == null) {
			if (microServices[i].getBasePath().equals(microServiceName)) {
				microService = microServices[i];
			}
			i++;
		}
		return microService;
	}

	private ClientResponse getClientResponse(String method, Object body) {
		Gson gson = new Gson();
		String bodyJson = gson.toJson(body);
		ClientResponse clientResponse = null;
		switch (method) {
			case "GET":
				clientResponse = webResource.get(ClientResponse.class);
				break;
			case "POST":
				clientResponse = webResource.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, bodyJson);
				break;
			case "PUT":
				clientResponse = webResource.type(MediaType.APPLICATION_JSON_TYPE).put(ClientResponse.class, bodyJson);
				break;
			case "DELETE":
				clientResponse = webResource.delete(ClientResponse.class);
				break;
		}
		return clientResponse;
	}
}

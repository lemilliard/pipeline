package fr.epsi.i4.pipeline.microservice;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.epsi.i4.pipeline.model.Request;

/**
 * Created by tkint on 22/02/2018.
 */
public class MicroServiceClient {

	private static final String baseUrl = "http://home.thomaskint.com:8085";

	private Client client;

	private WebResource webResource;

	public MicroServiceClient() {
		client = Client.create();
	}

	public <T extends MicroService> String processRequest(Class<T> microServiceClass, Request request)
			throws Exception {
		String clientResponseString;

		T microService = microServiceClass.newInstance();
		MicroServiceResource resource = microService.getResourceByName(request.getResource());
		if (request.getMethod() == Method.INVALID) {
			clientResponseString = "Bad method";
		} else if (resource == null) {
			clientResponseString = "Bad resource";
		} else if (!resource.containsMethod(request.getMethod())) {
			clientResponseString = "Method not allowed for this resource";
		} else {
			String resourcePath = microService.getResourcePath(resource, request.getParams());
			webResource = client.resource(baseUrl + resourcePath);
			webResource.accept(MediaType.APPLICATION_JSON);

			ClientResponse clientResponse = getClientResponse(request.getMethod(), request.getBody());
			if (clientResponse.getStatus() == -1) {
				clientResponseString = "No response from given resource";
			} else {
				clientResponseString = clientResponse.getEntity(String.class);
			}
		}
		return clientResponseString;
	}

	private ClientResponse getClientResponse(Method method, Object body) {
		Gson gson = new Gson();
		String bodyJson = gson.toJson(body);
		ClientResponse clientResponse = new ClientResponse(-1, null, null, null);
		switch (method) {
			case GET:
				clientResponse = webResource.get(ClientResponse.class);
				break;
			case POST:
				clientResponse = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, bodyJson);
				break;
			case PUT:
				clientResponse = webResource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, bodyJson);
				break;
			case DELETE:
				clientResponse = webResource.delete(ClientResponse.class);
				break;
		}
		return clientResponse;
	}
}

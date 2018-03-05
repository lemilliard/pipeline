package fr.epsi.i4.pipeline.microservice;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.epsi.i4.pipeline.encoder.NotificationEncoder;
import fr.epsi.i4.pipeline.model.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tkint on 22/02/2018.
 */
public class MicroServiceClient {

	private static final String baseUrl = "http://home.thomaskint.com:8085";

	private Client client;

	private WebResource webResource;

	private static final List<Registry> registries = new ArrayList<>();

	public MicroServiceClient() {
		client = Client.create();
	}

	public <T extends MicroService> String processRequestAsMessage(Class<T> microServiceClass, Request request)
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

			ClientResponse clientResponse = getClientResponse(request);
			if (clientResponse.getStatus() == -1) {
				clientResponseString = "No response from given resource";
			} else {
				clientResponseString = clientResponse.getEntity(String.class);
			}
		}
		return clientResponseString;
	}

	public <T extends MicroService> Response processRequest(Class<T> microServiceClass, Request request, Session session)
			throws Exception {
		Response response = new Response(request);

		T microService = microServiceClass.newInstance();
		MicroServiceResource resource = microService.getResourceByName(request.getResource());
		if (request.getMethod() == Method.INVALID) {
			response.setError("Bad method");
		} else if (resource == null) {
			response.setError("Bad resource");
		} else if (!resource.containsMethod(request.getMethod())) {
			response.setError("Method not allowed for this resource");
		} else {
			String resourcePath = microService.getResourcePath(resource, request.getParams());
			webResource = client.resource(baseUrl + resourcePath);
			webResource.accept(MediaType.APPLICATION_JSON);

			ClientResponse clientResponse = getClientResponse(request);
			if (clientResponse.getStatus() == -1) {
				response.setError("No response from given resource");
			} else {
				response.setHttpCode(clientResponse.getStatus());
				response.setContent(clientResponse.getEntity(String.class));
				synchronizeRequest(request, session, resource);
				notif(request, response, session, resource);
			}
		}
		return response;
	}

	private ClientResponse getClientResponse(Request request) {
		Gson gson = new Gson();
		String bodyJson = gson.toJson(request.getBody());
		ClientResponse clientResponse = new ClientResponse(-1, null, null, null);
		switch (request.getMethod()) {
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

	private <T extends MicroService> void notif(Request request, Response response, Session session, MicroServiceResource resource) throws Exception {
		if (request.getMethod().equals(Method.PUT) || request.getMethod().equals(Method.DELETE)) {
			Registry registry = getRegistryByEntity(resource.getEntity());
			List<Entry> entries;
			if (request.getParams() == null || request.getParams().isEmpty()) {
				entries = registry.getEntries();
			} else {
				String pkValue = request.getParams().get(registry.getEntityPK());
				entries = registry.getEntriesByPKalue(pkValue);
			}
			Notification notification = new Notification(registry.getEntity(), registry.getEntityPK(), response.getContent());

			for (Entry entry : entries) {
				if (!entry.getSession().getId().equals(session.getId())) {
					try {
						entry.getSession().getBasicRemote().sendObject(new NotificationEncoder().encode(notification));
					} catch (EncodeException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void synchronizeRequest(Request request, Session session, MicroServiceResource resource) {
		Registry registry = getRegistryByEntity(resource.getEntity());
		if (registry == null) {
			if (request.getParams() == null || request.getParams().isEmpty()) {
				registry = new Registry(resource.getEntity());
			} else {
				Map.Entry<String, String> param = request.getParams().entrySet().iterator().next();
				String pk = param.getKey();
				registry = new Registry(resource.getEntity(), pk);
			}
			registries.add(registry);
		}
		Entry entry;
		String pkValue = null;
		if (request.getParams() != null && !request.getParams().isEmpty()) {
			pkValue = request.getParams().get(registry.getEntityPK());
		} else if (request.getBody() != null) {
			pkValue = (String) ((LinkedTreeMap) request.getBody()).get(registry.getEntityPK());
		}
		if (pkValue == null) {
			entry = new Entry(session);
		} else {
			entry = new Entry(pkValue, session);
		}
		registry.addEntry(entry);
	}

	private Registry getRegistryByEntity(String entity) {
		Registry registry = null;
		int i = 0;
		while (i < registries.size() && registry == null) {
			if (registries.get(i).getEntity().equals(entity)) {
				registry = registries.get(i);
			}
			i++;
		}
		return registry;
	}
}

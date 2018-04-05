package fr.epsi.i4.pipeline.microservice;

import com.google.gson.internal.LinkedTreeMap;
import fr.epsi.i4.pipeline.encoder.NotificationEncoder;
import fr.epsi.i4.pipeline.microservice.microserviceclient.Method;
import fr.epsi.i4.pipeline.microservice.microserviceclient.MicroServiceClient;
import fr.epsi.i4.pipeline.microservice.microserviceclient.MicroServiceResource;
import fr.epsi.i4.pipeline.microservice.microserviceclient.UserMicroServiceClient;
import fr.epsi.i4.pipeline.model.Notification;
import fr.epsi.i4.pipeline.model.Request;
import fr.epsi.i4.pipeline.model.Response;
import fr.epsi.i4.pipeline.model.registry.RegistryEntry;
import fr.epsi.i4.pipeline.model.registry.Registry;
import fr.epsi.i4.pipeline.model.registry.RegistryType;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tkint on 22/02/2018.
 */
public class MicroService {

	private static final List<Registry> registries = new ArrayList<>();

	private final List<MicroServiceClient> microServiceClients;

	private Client client;

	private WebTarget webTarget;

	public MicroService() {
		client = ClientBuilder.newClient();
		microServiceClients = new ArrayList<MicroServiceClient>() {{
			add(new UserMicroServiceClient());
		}};
	}

	public Response processRequest(Request request, Session session) throws Exception {
		Response response = new Response(request);

		MicroServiceClient microServiceClient = getMicroServiceByResourceName(request.getResource());
		if (microServiceClient == null) {
			response.setError("Bad resource");
		} else {
			MicroServiceResource resource = microServiceClient.getResourceByName(request.getResource());
			if (request.getMethod() == Method.INVALID) {
				response.setError("Bad method");
			} else if (resource == null) {
				response.setError("Bad resource");
			} else if (!resource.containsMethod(request.getMethod())) {
				response.setError("Method not allowed for this resource");
			} else {
				String resourcePath = microServiceClient.getResourcePath(resource, request.getParams());
				webTarget = client.target(resourcePath);

				Object clientResponse = getClientResponse(request);
				if (clientResponse == null) {
					response.setError("No response from given resource");
				} else {
					response.setHttpCode(200);
					response.setContent(clientResponse);
					synchronizeRequest(request, session, resource);
					notif(request, response, session, resource);
				}
			}
		}
		return response;
	}

	/**
	 * Interroge une service et en récupère la réponse
	 *
	 * @param request
	 * @return
	 */
	private Object getClientResponse(Request request) {
		Entity object = null;
		if (request.getBody() != null) {
			object = Entity.entity(request.getBody(), MediaType.APPLICATION_JSON);
		}
		if (request.getParams() != null) {
			for (Map.Entry<String, Object> entry : request.getParams().entrySet()) {
				webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
			}
		}
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Object clientResponse = null;
		switch (request.getMethod()) {
			case GET:
				clientResponse = invocationBuilder.get(Object.class);
				break;
			case POST:
				if (object != null) {
					clientResponse = invocationBuilder.post(object, Object.class);
				}
				break;
			case PUT:
				if (object != null) {
					clientResponse = invocationBuilder.put(object, Object.class);
				}
				break;
			case DELETE:
				clientResponse = invocationBuilder.delete(Object.class);
				break;
		}
		return clientResponse;
	}

	/**
	 * Notifie tous les abonnés à une ressource lorsque celle-ci est modifiée
	 *
	 * @param request
	 * @param response
	 * @param session
	 * @param resource
	 * @throws Exception
	 */
	private void notif(Request request, Response response, Session session, MicroServiceResource resource)
			throws Exception {
		// SI on fait un PUT ou un DELETE
		if (request.getMethod().equals(Method.PUT) || request.getMethod().equals(Method.DELETE)) {
			// On récupère le registre
			Registry registry = getRegistryByEntity(resource.getRegistryType());
			// On génère la notification
			Notification notification =
					new Notification(registry.getRegistryType(), registry.getEntityPK(), response.getContent());
			List<RegistryEntry> entries;
			Object pkValue = null;
			// Si la clef primaire est dans les paramètres
			if (request.getParams() != null && !request.getParams().isEmpty()) {
				pkValue = request.getParams().get(registry.getEntityPK());
			}
			// Si la clef primaire est dans le corps, on la récupère
			else if (request.getBody() != null && ((LinkedTreeMap) request.getBody())
					.containsKey(registry.getEntityPK())) {
				pkValue = ((LinkedTreeMap) request.getBody()).get(registry.getEntityPK());
			}
			// Si on a une clef primaire, on récupère toutes les entrées liées à celle-ci
			if (pkValue != null) {
				entries = registry.getEntriesByPKalue(pkValue);
			}
			// Sinon, on prend toutes les entrées du registre
			else {
				entries = registry.getEntries();
			}
			// On notifie toutes les entrées du registre
			for (RegistryEntry registryEntry : entries) {
				if (!registryEntry.getSession().getId().equals(session.getId())) {
					try {
						registryEntry.getSession().getBasicRemote().sendObject(new NotificationEncoder().encode(notification));
					} catch (EncodeException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Abonne une session à une ressource
	 *
	 * @param request
	 * @param session
	 * @param resource
	 */
	private void synchronizeRequest(Request request, Session session, MicroServiceResource resource) {
		// On récupère le registre
		Registry registry = getRegistryByEntity(resource.getRegistryType());
		// Si le registre n'existe pas
		if (registry == null) {
			// S'il n'y a pas de clef primaire, on créé un registre sur l'ensemble de l'entité
			if (request.getParams() == null || request.getParams().isEmpty()) {
				registry = new Registry(resource.getRegistryType());
			}
			// Sinon, on défini le premier paramètre comme clef primaire et on créé le registre
			else {
				Map.Entry<String, Object> param = request.getParams().entrySet().iterator().next();
				String pk = param.getKey();
				registry = new Registry(resource.getRegistryType(), pk);
			}
			// On enregistre le registre
			registries.add(registry);
		}
		RegistryEntry registryEntry;
		Object pkValue = null;
		// Si la clef primaire est dans les paramètres
		if (request.getParams() != null && !request.getParams().isEmpty()) {
			pkValue = request.getParams().get(registry.getEntityPK());
		}
		// Si la clef primaire est dans le corps, on la récupère
		else if (request.getBody() != null && ((LinkedTreeMap) request.getBody()).containsKey(registry.getEntityPK())) {
			pkValue = ((LinkedTreeMap) request.getBody()).get(registry.getEntityPK());
		}
		// S'il n'y a pas de valeur à la clef primaire, on génère l'registryEntry basée uniquement sur la session
		if (pkValue == null) {
			registryEntry = new RegistryEntry(session);
		}
		// Sinon, on génère l'registryEntry basée sur la session et la clef primaire
		else {
			registryEntry = new RegistryEntry(pkValue, session);
		}
		registry.addEntry(registryEntry);
	}

	/**
	 * Récupère un registre basé sur son registryType
	 *
	 * @param registryType
	 * @return
	 */
	private Registry getRegistryByEntity(RegistryType registryType) {
		Registry registry = null;
		int i = 0;
		while (i < registries.size() && registry == null) {
			if (registries.get(i).getRegistryType().equals(registryType)) {
				registry = registries.get(i);
			}
			i++;
		}
		return registry;
	}

	/**
	 * Récupère un microservice basé sur une de ses ressources
	 *
	 * @param resourceName
	 * @return
	 */
	private MicroServiceClient getMicroServiceByResourceName(String resourceName) {
		MicroServiceClient microServiceClient = null;
		int i = 0;
		int j;
		while (i < microServiceClients.size() && microServiceClient == null) {
			j = 0;
			while (j < microServiceClients.get(i).getResources().length && microServiceClient == null) {
				if (microServiceClients.get(i).getResourceByName(resourceName) != null) {
					microServiceClient = microServiceClients.get(i);
				}
				j++;
			}
			i++;
		}
		return microServiceClient;
	}
}

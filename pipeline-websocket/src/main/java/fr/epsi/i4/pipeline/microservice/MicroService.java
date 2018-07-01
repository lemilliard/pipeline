package fr.epsi.i4.pipeline.microservice;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import fr.epsi.i4.pipeline.encoder.NotificationEncoder;
import fr.epsi.i4.pipeline.microservice.microserviceclient.*;
import fr.epsi.i4.pipeline.model.Notification;
import fr.epsi.i4.pipeline.model.Request;
import fr.epsi.i4.pipeline.model.Response;
import fr.epsi.i4.pipeline.model.registry.RegistryEntry;
import fr.epsi.i4.pipeline.model.registry.Registry;
import fr.epsi.i4.pipeline.model.registry.RegistryType;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tkint on 22/02/2018.
 */
public class MicroService {

	public static final List<Registry> registries = new ArrayList<>();

	private final List<MicroServiceClient> microServiceClients;

	private HttpClient client;

	public MicroService() {
		client = HttpClientBuilder.create().build();
		microServiceClients = new ArrayList<MicroServiceClient>() {{
			add(new UserMicroServiceClient());
			add(new TournamentMicroServiceClient());
			add(new MatchMicroServiceClient());
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
				System.out.println(resourcePath);

				Object clientResponse = getClientResponse(request, resourcePath);
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
	 * @param resourcePath
	 * @return
	 */
	private Object getClientResponse(Request request, String resourcePath) {
		Object clientResponse = null;
		try {
			Gson gson = new Gson();
			Object object = null;
			// Ajout du body
			if (request.getBody() != null) {
				object = request.getBody();
			}
			HttpResponse httpResponse = null;
			switch (request.getMethod()) {
				case GET:
					HttpGet httpGet = new HttpGet(resourcePath);
					httpResponse = client.execute(httpGet);
					break;
				case POST:
					if (object != null) {
						StringEntity stringEntity = new StringEntity(gson.toJson(object));
						HttpPost httpPost = new HttpPost(resourcePath);
						httpPost.setEntity(stringEntity);
						httpPost.setHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
						httpResponse = client.execute(httpPost);
					}
					break;
				case PUT:
					if (object != null) {
						StringEntity stringEntity = new StringEntity(gson.toJson(object));
						HttpPut httpPut = new HttpPut(resourcePath);
						httpPut.setEntity(stringEntity);
						httpPut.setHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
						httpResponse = client.execute(httpPut);
					}
					break;
				case DELETE:
					HttpDelete httpDelete = new HttpDelete(resourcePath);
					httpResponse = client.execute(httpDelete);
					break;
			}

			if (httpResponse != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(httpResponse.getEntity().getContent());
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				clientResponse = gson.fromJson(bufferedReader, Object.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		// Si on fait un PUT ou un DELETE
		if (request.getMethod().equals(Method.POST) || request.getMethod().equals(Method.PUT) || request.getMethod().equals(Method.DELETE)) {
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
			if (request.getParams() != null && !request.getParams().isEmpty()) {
				Map.Entry<String, Object> entry = request.getParams().entrySet().iterator().next();
				if (request.getMethod().equals(Method.DELETE)) {
					notification.setPkValue(entry.getValue());
				}
			}
			// Si on a une clef primaire, on récupère toutes les entrées liées à celle-ci
//			if (pkValue != null) {
//				entries = registry.getEntriesByPKalue(pkValue);
//			}
//			// Sinon, on prend toutes les entrées du registre
//			else {
			entries = registry.getEntries();
//			}
			System.out.println("Notification du registre " + registry.getRegistryType().name());
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

package fr.epsi.i4.pipeline.microservice.microserviceclient;

import fr.epsi.i4.pipeline.Main;
import fr.epsi.i4.pipeline.model.registry.RegistryType;

/**
 * Created by tkint on 23/02/2018.
 */
public class UserMicroServiceClient extends MicroServiceClient {

	public static final String baseUrl = Main.getConfig().getProperty("ms-dao-user.baseUrl");

	public static final String port = Main.getConfig().getProperty("ms-dao-user.port");

	public static final String basePath = Main.getConfig().getProperty("ms-dao-user.basePath");

	private static final MicroServiceResource[] MICRO_SERVICE_RESOURCES = new MicroServiceResource[]{
			new MicroServiceResource(Resource.USERS, RegistryType.USER, Method.GET, Method.POST, Method.PUT, Method.DELETE),
			new MicroServiceResource(Resource.USER, RegistryType.USER, Method.GET, Method.DELETE),
			new MicroServiceResource(Resource.UPDATE_USER, RegistryType.USER, Method.PUT),
			new MicroServiceResource(Resource.ROLES, RegistryType.ROLE, Method.GET, Method.POST, Method.PUT),
			new MicroServiceResource(Resource.ROLE, RegistryType.ROLE, Method.GET, Method.DELETE),
			new MicroServiceResource(Resource.CONNECT, RegistryType.USER, Method.POST),
			new MicroServiceResource(Resource.CONNECT_BY_ID, RegistryType.USER, Method.GET),
			new MicroServiceResource(Resource.REGISTER, RegistryType.USER, Method.POST),
			new MicroServiceResource(Resource.ABONNEMENTS, RegistryType.ABONNEMENT, Method.GET),
			new MicroServiceResource(Resource.ABONNEMENT, RegistryType.ABONNEMENT, Method.POST),
			new MicroServiceResource(Resource.ABONNEMENTS_RENCONTRE, RegistryType.ABONNEMENT, Method.GET),
			new MicroServiceResource(Resource.DELETE_ABONNEMENT, RegistryType.ABONNEMENT, Method.DELETE),
	};

	public UserMicroServiceClient() {
		super(baseUrl, port, basePath, MICRO_SERVICE_RESOURCES);
	}
}

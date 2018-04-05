package fr.epsi.i4.pipeline.microservice.microserviceclient;

import fr.epsi.i4.pipeline.model.registry.RegistryType;

/**
 * Created by tkint on 23/02/2018.
 */
public class UserMicroServiceClient extends MicroServiceClient {

	private static final MicroServiceResource[] MICRO_SERVICE_RESOURCES = new MicroServiceResource[]{
			new MicroServiceResource(Resource.USERS, RegistryType.USER, Method.GET, Method.POST, Method.PUT),
			new MicroServiceResource(Resource.USER, RegistryType.USER, Method.GET, Method.DELETE),
			new MicroServiceResource(Resource.CONNECT, RegistryType.USER, Method.POST),
			new MicroServiceResource(Resource.CONNECT_BY_ID, RegistryType.USER, Method.GET),
			new MicroServiceResource(Resource.REGISTER, RegistryType.USER, Method.POST),
	};

	public UserMicroServiceClient() {
		super("http://localhost", "8082", "", MICRO_SERVICE_RESOURCES);
	}
}

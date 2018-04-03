package fr.epsi.i4.pipeline.microservice.microserviceclient;

import static fr.epsi.i4.pipeline.model.registry.RegistryType.PRIVILEGE;
import static fr.epsi.i4.pipeline.model.registry.RegistryType.ROLE;
import static fr.epsi.i4.pipeline.model.registry.RegistryType.USER;

/**
 * Created by tkint on 23/02/2018.
 */
public class UserMicroServiceClient extends MicroServiceClient {

	private static final MicroServiceResource[] MICRO_SERVICE_RESOURCES = new MicroServiceResource[]{
			new MicroServiceResource("user", USER, Method.GET, Method.POST, Method.PUT),
			new MicroServiceResource("user/{id_user}", USER, Method.GET, Method.DELETE),
			new MicroServiceResource("connect", USER, Method.POST),
			new MicroServiceResource("role", ROLE, Method.GET, Method.POST, Method.PUT),
			new MicroServiceResource("role/{value}", ROLE, Method.GET, Method.DELETE),
			new MicroServiceResource("privilege", PRIVILEGE, Method.GET, Method.POST, Method.PUT),
			new MicroServiceResource("privilege/{value}", PRIVILEGE, Method.GET, Method.DELETE)
	};

	public UserMicroServiceClient() {
		super("ms-dao-user", MICRO_SERVICE_RESOURCES);
	}
}

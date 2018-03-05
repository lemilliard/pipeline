package fr.epsi.i4.pipeline.microservice;

/**
 * Created by tkint on 23/02/2018.
 */
public class UserMicroService extends MicroService {

	private static final MicroServiceResource[] MICRO_SERVICE_RESOURCES =
			new MicroServiceResource[]{
					new MicroServiceResource("user", "user", Method.GET, Method.POST, Method.PUT),
					new MicroServiceResource("user/{id_user}", "user", Method.GET, Method.DELETE),
					new MicroServiceResource("connect", "user", Method.POST),
					new MicroServiceResource("role", "role", Method.GET, Method.POST, Method.PUT),
					new MicroServiceResource("role/{value}", "role", Method.GET, Method.DELETE)};

	public UserMicroService() {
		super("ms-dao-user", MICRO_SERVICE_RESOURCES);
	}
}

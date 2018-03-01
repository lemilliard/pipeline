package fr.epsi.i4.pipeline.microservice;

/**
 * Created by tkint on 23/02/2018.
 */
public class UserMicroService extends MicroService {

	private static final MicroServiceResource[] MICRO_SERVICE_RESOURCES =
			new MicroServiceResource[] { new MicroServiceResource("user", Method.GET, Method.POST, Method.PUT),
					new MicroServiceResource("user/{id_user}", Method.GET, Method.DELETE),
					new MicroServiceResource("connect", Method.POST),
					new MicroServiceResource("role", Method.GET, Method.POST, Method.PUT),
					new MicroServiceResource("role/{value}", Method.GET, Method.DELETE) };

	public UserMicroService() {
		super("ms-dao-user", MICRO_SERVICE_RESOURCES);
	}
}

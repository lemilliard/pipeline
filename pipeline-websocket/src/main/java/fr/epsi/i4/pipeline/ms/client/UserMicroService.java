package fr.epsi.i4.pipeline.ms.client;

import fr.epsi.i4.pipeline.ms.MicroService;
import fr.epsi.i4.pipeline.ms.Resource;
import fr.epsi.i4.pipeline.ms.ResourceType;

/**
 * Created by tkint on 23/02/2018.
 */
public class UserMicroService extends MicroService {

	public static final String name = "ms-dao-user";

	private static final Resource[] resources = new Resource[]{
			new Resource("user", ResourceType.GET, ResourceType.POST, ResourceType.PUT),
			new Resource("user/{id_user}", ResourceType.GET, ResourceType.DELETE),
			new Resource("connect", ResourceType.POST),
			new Resource("role", ResourceType.GET, ResourceType.POST, ResourceType.PUT),
			new Resource("role/{id_role}", ResourceType.GET, ResourceType.DELETE)
	};

	public UserMicroService() {
		super(name, resources);
	}
}

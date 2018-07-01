package fr.epsi.i4.pipeline.microservice.microserviceclient;

import fr.epsi.i4.pipeline.Main;
import fr.epsi.i4.pipeline.model.registry.RegistryType;

/**
 * Created by tkint on 23/02/2018.
 */
public class MatchMicroServiceClient extends MicroServiceClient {

	private static final String baseUrl = Main.getConfig().getProperty("ms-dao-match.baseUrl");

	private static final String port = Main.getConfig().getProperty("ms-dao-match.port");

	private static final String basePath = Main.getConfig().getProperty("ms-dao-match.basePath");

	private static final MicroServiceResource[] MICRO_SERVICE_RESOURCES = new MicroServiceResource[]{
			new MicroServiceResource(Resource.MATCHS, RegistryType.TOURNAMENT, Method.GET, Method.POST),
			new MicroServiceResource(Resource.MATCH, RegistryType.TOURNAMENT, Method.GET),
			new MicroServiceResource(Resource.SCORE, RegistryType.SCORE, Method.GET),
			new MicroServiceResource(Resource.SCORE_ADD, RegistryType.SCORE, Method.POST),
	};

	public MatchMicroServiceClient() {
		super(baseUrl, port, basePath, MICRO_SERVICE_RESOURCES);
	}
}

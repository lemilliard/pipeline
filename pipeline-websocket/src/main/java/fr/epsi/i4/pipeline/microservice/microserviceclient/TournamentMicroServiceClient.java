package fr.epsi.i4.pipeline.microservice.microserviceclient;

import fr.epsi.i4.pipeline.Main;
import fr.epsi.i4.pipeline.model.registry.RegistryType;

/**
 * Created by tkint on 23/02/2018.
 */
public class TournamentMicroServiceClient extends MicroServiceClient {

	private static final String baseUrl = Main.getConfig().getProperty("ms-dao-tournament.baseUrl");

	private static final String port = Main.getConfig().getProperty("ms-dao-tournament.port");

	private static final String basePath = Main.getConfig().getProperty("ms-dao-tournament.basePath");

	private static final MicroServiceResource[] MICRO_SERVICE_RESOURCES = new MicroServiceResource[]{
			new MicroServiceResource(Resource.TOURNAMENTS, RegistryType.TOURNAMENT, Method.GET, Method.POST),
			new MicroServiceResource(Resource.TOURNAMENT, RegistryType.TOURNAMENT, Method.GET),
	};

	public TournamentMicroServiceClient() {
		super(baseUrl, port, basePath, MICRO_SERVICE_RESOURCES);
	}
}

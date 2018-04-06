package fr.epsi.i4.pipeline;

import fr.epsi.i4.pipeline.endpoint.ChatEndpoint;
import fr.epsi.i4.pipeline.endpoint.MainEndpoint;
import org.glassfish.tyrus.server.Server;

/**
 * Created by tkint on 21/02/2018.
 */
public class Main {

	private static Config config = new Config("application.properties");

	public static void main(String[] args) {
		if (args.length > 0) {
			config = new Config("application-dev.properties");
		}

		Class[] endpoints = new Class[] { ChatEndpoint.class, MainEndpoint.class };

		Server server = new Server("localhost", 8080, "/", null, endpoints);

		try {
			server.start();
			while (true) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Config getConfig() {
		return config;
	}
}

package fr.epsi.i4.pipeline;

import fr.epsi.i4.pipeline.endpoint.UserEndpoint;
import org.glassfish.tyrus.server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by tkint on 21/02/2018.
 */
public class Main {

	public static void main(String... args) {
		Server server = new Server("localhost", 8025, "/websockets", null, UserEndpoint.class);
		try {
			server.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Please press a key to stop the server.");
			reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.stop();
		}
	}
}

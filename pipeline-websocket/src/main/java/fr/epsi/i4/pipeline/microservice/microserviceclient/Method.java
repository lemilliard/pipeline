package fr.epsi.i4.pipeline.microservice.microserviceclient;

/**
 * Created by tkint on 23/02/2018.
 */
public enum Method {
	GET, POST, PUT, DELETE, INVALID;

	public static boolean isValid(String name) {
		boolean valid = false;
		int i = 0;
		while (i < values().length && !valid) {
			if (values()[i].name().equals(name)) {
				valid = true;
			}
			i++;
		}
		return valid;
	}
}

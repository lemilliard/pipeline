package fr.epsi.i4.pipeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		System.getProperties().setProperty("oracle.jdbc.J2EE13Compliant", "true");

		SpringApplication.run(Main.class, args);
	}
}

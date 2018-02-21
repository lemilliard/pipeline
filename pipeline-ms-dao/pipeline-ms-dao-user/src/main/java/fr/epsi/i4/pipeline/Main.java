package fr.epsi.i4.pipeline;

import com.thomaskint.minidao.MiniDAO;
import com.thomaskint.minidao.connection.MDConnectionConfig;
import com.thomaskint.minidao.connection.MDDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.thomaskint.minidao.connection.MDDriver.ORACLE;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		MDConnectionConfig mdConnectionConfig = new MDConnectionConfig(ORACLE, "home.thomaskint.com", "1521", "PIPELINE", "password", "orcl");
		MiniDAO.config(mdConnectionConfig);

		SpringApplication.run(Main.class, args);
	}
}

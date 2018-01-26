package fr.epsi.i4.pipeline;

import com.thomaskint.minidao.MiniDAO;
import com.thomaskint.minidao.connection.MDConnectionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		MDConnectionConfig mdConnectionConfig = new MDConnectionConfig("home.thomaskint.com", "3306", "minidao", "minidao", "minidao");
		MiniDAO.config(mdConnectionConfig);

		SpringApplication.run(Main.class, args);
	}
}

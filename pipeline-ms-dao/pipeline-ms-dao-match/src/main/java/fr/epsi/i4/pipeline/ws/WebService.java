package fr.epsi.i4.pipeline.ws;

import com.thomaskint.minidao.MiniDAO;
import com.thomaskint.minidao.connection.MDConnectionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.thomaskint.minidao.connection.MDDriver.ORACLE;

public abstract class WebService {

	private MiniDAO miniDAO;

	@Autowired
	public void init(@Value("${database.url}") String url,
	                 @Value("${database.port}") String port,
	                 @Value("${database.login}") String login,
	                 @Value("${database.password}") String password,
	                 @Value("${database.instance}") String instance) {
		MDConnectionConfig connectionConfig = new MDConnectionConfig(ORACLE, url, port, login, password, instance);
		miniDAO = new MiniDAO(connectionConfig);
	}

	public MiniDAO getMiniDAO() {
		return miniDAO;
	}
}

package fr.epsi.i4.pipeline.ws;

import com.thomaskint.minidao.MiniDAO;
import com.thomaskint.minidao.connection.MDConnectionConfig;

import static com.thomaskint.minidao.connection.MDDriver.ORACLE;

/**
 * @author Thomas Kint
 */
public abstract class WebService {

	private final MDConnectionConfig connectionConfig;

	private final MiniDAO miniDAO;

	public WebService() {
		connectionConfig = new MDConnectionConfig(ORACLE, "home.thomaskint.com", "1521", "PIPELINE", "password", "orcl");
		miniDAO = new MiniDAO(connectionConfig);
	}

	public MDConnectionConfig getConnectionConfig() {
		return connectionConfig;
	}

	public MiniDAO getMiniDAO() {
		return miniDAO;
	}
}

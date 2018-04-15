package fr.epsi.i4.pipeline.ws;

import com.thomaskint.minidao.MiniDAO;
import com.thomaskint.minidao.connection.MDConnectionConfig;
import com.thomaskint.minidao.exception.MDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

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

	public <T> List<T> getEntities(Class<T> entityClass) {
		List<T> entities = null;
		try {
			entities = miniDAO.read().getEntities(entityClass);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return entities;
	}

	public <T> T getEntityById(Class<T> entityClass, Object id) {
		T entity = null;
		try {
			entity = getMiniDAO().read().getEntityById(entityClass, id);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return entity;
	}

	public <T> boolean createEntity(T entity) {
		boolean created = false;
		try {
			created = miniDAO.create().createEntity(entity);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return created;
	}

	public <T> boolean updateEntity(T entity) {
		boolean updated = false;
		try {
			updated = miniDAO.update().updateEntity(entity);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return updated;
	}

	public <T> boolean deleteEntity(T entity) {
		boolean deleted = false;
		try {
			deleted = miniDAO.delete().deleteEntity(entity);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return deleted;
	}

	public <T> boolean deleteEntityById(Class<T> entityClass, Object id) {
		boolean deleted = false;
		try {
			deleted = miniDAO.delete().deleteEntityById(entityClass, id);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return deleted;
	}
}

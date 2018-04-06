package fr.epsi.i4.pipeline;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

	private static final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

	private final String propertiesFileName;

	private final Properties properties;

	public Config(String propertiesFileName) {
		this.propertiesFileName = propertiesFileName;
		String propertiesFilePath = rootPath + propertiesFileName;
		properties = new Properties();
		try {
			properties.load(new FileInputStream(propertiesFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPropertiesFileName() {
		return propertiesFileName;
	}

	public Properties getProperties() {
		return properties;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public Integer getIntegerProperty(String key) {
		return Integer.valueOf(getProperty(key));
	}
}

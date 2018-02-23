package fr.epsi.i4.pipeline.ms;

import java.util.Map;
import java.util.MissingResourceException;

/**
 * Created by tkint on 23/02/2018.
 */
public abstract class MicroService {

	private String basePath;

	private Resource[] resources;

	public MicroService(String basePath, Resource... resources) {
		this.basePath = basePath;
		this.resources = resources;
	}

	public String getBasePath() {
		return basePath;
	}

	public String getResourcePath(String resource, Map params) {
		if (!doesResourceExist(resource)) {
			throw new MissingResourceException("The resource " + resource + " does not exist in " + basePath, basePath, resource);
		}
		return "/" + basePath + "/" + mapResourceWithParams(resource, params);
	}

	private boolean doesResourceExist(String resource) {
		boolean exists = false;
		int i = 0;
		while (i < resources.length && !exists) {
			if (resources[i].getName().equals(resource)) {
				exists = true;
			}
			i++;
		}
		return exists;
	}

	private String mapResourceWithParams(String resource, Map<String, Object> params) {
		String[] resourceArray = resource.split("/");
		StringBuilder resourcePath = new StringBuilder(resourceArray[0]);
		String param;
		for (int i = 1; i < resourceArray.length; i++) {
			param = resourceArray[i].replaceAll("[{}]", "");
			if (params.containsKey(param)) {
				resourcePath.append("/").append(params.get(param));
			}
		}
		return resourcePath.toString();
	}
}

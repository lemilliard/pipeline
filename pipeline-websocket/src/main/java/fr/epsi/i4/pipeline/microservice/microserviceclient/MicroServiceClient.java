package fr.epsi.i4.pipeline.microservice.microserviceclient;

import java.util.Map;

/**
 * Created by tkint on 23/02/2018.
 */
public abstract class MicroServiceClient {

	private String basePath;

	private MicroServiceResource[] microServiceResources;

	public MicroServiceClient(String basePath, MicroServiceResource... microServiceResources) {
		this.basePath = basePath;
		this.microServiceResources = microServiceResources;
	}

	public String getBasePath() {
		return basePath;
	}

	public String getResourcePath(MicroServiceResource resource, Map<String, Object> params) {
		return "/" + basePath + "/" + mapResourceWithParams(resource, params);
	}

	public MicroServiceResource getResourceByName(String resourceName) {
		MicroServiceResource resource = null;
		int i = 0;
		while (i < microServiceResources.length && resource == null) {
			if (microServiceResources[i].getName().equals(resourceName)) {
				resource = microServiceResources[i];
			}
			i++;
		}
		return resource;
	}

	public String mapResourceWithParams(MicroServiceResource resource, Map<String, Object> params) {
		String[] resourceArray = resource.getName().split("/");
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

	public MicroServiceResource[] getResources() {
		return microServiceResources;
	}
}

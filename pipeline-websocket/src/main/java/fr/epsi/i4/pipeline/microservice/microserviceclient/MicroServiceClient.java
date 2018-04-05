package fr.epsi.i4.pipeline.microservice.microserviceclient;

import java.util.Map;

/**
 * Created by tkint on 23/02/2018.
 */
public abstract class MicroServiceClient {

	private final String baseUrl;

	private final String port;

	private final String basePath;

	private MicroServiceResource[] microServiceResources;

	public MicroServiceClient(String baseUrl, String port, String basePath, MicroServiceResource... microServiceResources) {
		this.baseUrl = baseUrl;
		this.port = port;
		this.basePath = basePath;
		this.microServiceResources = microServiceResources;
	}

	public String getBasePath() {
		return basePath;
	}

	public String getResourcePath(MicroServiceResource resource, Map<String, Object> params) {
		StringBuilder resourceBuilder = new StringBuilder(baseUrl);
		resourceBuilder.append(":");
		resourceBuilder.append(port);
		resourceBuilder.append("/");
		if (basePath != null && !basePath.isEmpty()) {
			resourceBuilder.append(basePath);
			resourceBuilder.append("/");
		}
		resourceBuilder.append(mapResourceWithParams(resource, params));
		return resourceBuilder.toString();
	}

	public MicroServiceResource getResourceByName(String resourceName) {
		MicroServiceResource resource = null;
		int i = 0;
		while (i < microServiceResources.length && resource == null) {
			if (microServiceResources[i].getResource().name().equals(resourceName)) {
				resource = microServiceResources[i];
			}
			i++;
		}
		return resource;
	}

	public String mapResourceWithParams(MicroServiceResource resource, Map<String, Object> params) {
		StringBuilder resourcePath = new StringBuilder();
		if (params == null) {
			resourcePath.append(resource.getResource().getValue());
		} else {
			String[] resourceArray = resource.getResource().getValue().split("/");
			resourcePath.append(resourceArray[0]);
			String param;
			for (int i = 1; i < resourceArray.length; i++) {
				param = resourceArray[i].replaceAll("[{}]", "");
				if (params.containsKey(param)) {
					resourcePath.append("/").append(params.get(param));
				}
			}
		}
		return resourcePath.toString();
	}

	public MicroServiceResource[] getResources() {
		return microServiceResources;
	}
}

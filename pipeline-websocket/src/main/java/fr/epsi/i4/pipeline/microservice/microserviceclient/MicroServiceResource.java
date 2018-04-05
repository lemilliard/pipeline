package fr.epsi.i4.pipeline.microservice.microserviceclient;

import fr.epsi.i4.pipeline.model.registry.RegistryType;

/**
 * Created by tkint on 23/02/2018.
 */
public class MicroServiceResource {

	private Resource resource;

	private RegistryType registryType;

	private Method[] methods;

	public MicroServiceResource(Resource resource, RegistryType registryType, Method... methods) {
		this.resource = resource;
		this.registryType = registryType;
		this.methods = methods;
	}

	public Resource getResource() {
		return resource;
	}

	public RegistryType getRegistryType() {
		return registryType;
	}

	public Method[] getMethods() {
		return methods;
	}

	public boolean containsMethod(Method method) {
		boolean contains = false;
		if (method != Method.INVALID) {
			int i = 0;
			while (i < methods.length && !contains) {
				if (methods[i].equals(method)) {
					contains = true;
				}
				i++;
			}
		}
		return contains;
	}
}

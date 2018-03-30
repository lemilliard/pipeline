package fr.epsi.i4.pipeline.microservice.microserviceclient;

import fr.epsi.i4.pipeline.model.registry.RegistryEntity;

/**
 * Created by tkint on 23/02/2018.
 */
public class MicroServiceResource {

	private String name;

	private RegistryEntity registryEntity;

	private Method[] methods;

	public MicroServiceResource(String name, RegistryEntity registryEntity, Method... methods) {
		this.name = name;
		this.registryEntity = registryEntity;
		this.methods = methods;
	}

	public String getName() {
		return name;
	}

	public RegistryEntity getRegistryEntity() {
		return registryEntity;
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

package fr.epsi.i4.pipeline.microservice;

/**
 * Created by tkint on 23/02/2018.
 */
public class MicroServiceResource {

	private String name;

	private String entity;

	private Method[] methods;

	public MicroServiceResource(String name, String entity, Method... methods) {
		this.name = name;
		this.entity = entity;
		this.methods = methods;
	}

	public String getName() {
		return name;
	}

	public String getEntity() {
		return entity;
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
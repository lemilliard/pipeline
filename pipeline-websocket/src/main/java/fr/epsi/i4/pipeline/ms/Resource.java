package fr.epsi.i4.pipeline.ms;

/**
 * Created by tkint on 23/02/2018.
 */
public class Resource {

	private String name;

	private ResourceType[] resourceTypes;

	public Resource(String name, ResourceType... resourceTypes) {
		this.name = name;
		this.resourceTypes = resourceTypes;
	}

	public String getName() {
		return name;
	}

	public ResourceType[] getResourceTypes() {
		return resourceTypes;
	}
}

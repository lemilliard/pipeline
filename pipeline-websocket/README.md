# Pipeline Socket

## Build

Attention, pour build ce projet, utiliser la commande ci-dessous:
```
mvn clean compile assembly:single
```

## Endpoint

Un endpoint correspond à une logique métier. Celui-ci peut faire appel à différents micro-services.

## Micro-service

Pour créer un point d'accès à un micro-service, il faut hériter de la classe MicroService.

Exemple:
```java
public class UserMicroService extends MicroService {

	public static final String name = "ms-dao-user";

	private static final Resource[] resources = new Resource[]{
			new Resource("user", ResourceType.GET, ResourceType.POST, ResourceType.PUT),
			new Resource("user/{id_user}", ResourceType.GET, ResourceType.DELETE),
			new Resource("connect", ResourceType.POST),
			new Resource("role", ResourceType.GET, ResourceType.POST, ResourceType.PUT),
			new Resource("role/{id_role}", ResourceType.GET, ResourceType.DELETE)
	};

	public UserMicroService() {
		super(name, resources);
	}
}
```


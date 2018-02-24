# PIPELINE PROJECT

## Virtual Machines

L'architecture de Pipeline est partagée sur deux machines virtuelles

### BDD

Cette machine est dédiée à l'hébergement de la base de données Oracle. Elle est accessible en SSH à l'adresse suivante:
http://home.thomaskint.com:2223 (22)

#### Mise en place

##### Oracle

Voir tuto suivant:
https://www.howtoforge.com/tutorial/how-to-install-oracle-database-12c-on-centos-7/

### Server

Cette machine est dédiée à l'hébergement des services Docker. Elle est accessible en SSH à l'adresse suivante:
http://home.thomaskint.com:2222 (22)

#### Mise en place

##### Installation Docker

```
yum -y update

yum -y install yum-utils device-mapper-persistent-data lvm2

yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

yum -y install docker-ce

service docker start

chkconfig docker on
```

##### Installation Rancher

```
docker run -d --restart=unless-stopped -p 8080:8080 rancher/server:stable
```

* Aller sur le port 8080 de l'hôte
* Aller dans Infrastructure -> Hosts
* Cliquer sur "Add Host"
* Copier la commande donnée à l'étape 5
* Exécuter la commande sur l'hôte hébergeant Rancher (hôte distant possible)
* Retourner dans Infrastructure -> Hosts
* Vérifier que les services de l'hôte sont bien lancés

##### Installation Traefik & Pipeline

Avant cette étape, voir:
[Pipeline Docker](https://github.com/tkint/Pipeline/tree/master/pipeline-docker#pipeline-docker)

* Aller sur Rancher
* Dans Stacks -> All
* Cliquer sur "Add Stack"
* Donner un nom au Stack (ici Pipeline)
* Importer un docker-compose.yml (ici sous /pipeline-docker/)
* Cliquer sur "Create"
* Vérifier que les services du Stack sont bien lancés

## Adresses

Les différents parties de l'application sont accessibles aux adresses suivantes:

* Socket: http://home.thomaskint.com:8085/websocket (80)
* Micro Services: http://home.thomaskint.com:8085 (80)
  * MS DAO:
    * EVENT: /ms-dao-event
    * MATCH: /ms-dao-match
    * TOURNAMENT: /ms-dao-tournament
    * USER: /ms-dao-user
  * MS LOG: /ms-log
  * MS NOTIFICATION: /ms-notification
  * MS RESOURCE: /ms-resource
* Rancher: http://home.thomaskint.com:8086 (8080)
* Traefik: http://home.thomaskint.com:8087 (8081)
* BDD: http://home.thomaskint.com:1521 (1521)

### Mise à jour des images

Pour mettre à jour les images docker sur le serveur sans casser traefik, il faut suivre les étapes suivantes.

* Aller dans Rancher
* Arrêter le service ciblé
* Se connecter en SSH sur le serveur Rancher
* Supprimer le/les conteneur(s)
```
docker rm {container_id}
```
* Supprimer l'image
```
docker rmi {image_id}
```
* Se placer dans le répertoire du dockerfile
* Build la nouvelle image
```
docker build -t {image_name} .
```
* Aller dans Rancher
* Redémarrer le service

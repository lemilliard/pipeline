# Pipeline Docker

La dockerisation des services passe simplement par docker-compose.

Il suffit d'utiliser le docker-compose.yml avec la commande "docker-compose up -d" afin de créer le conteneur Traefik et les conteneures des micro services dans le même temps.

Une fois cela fait, il faut vérifier que les conteneurs sont bien lancés avec "docker ps", puis visiter l'interface graphique de Traefik sur le port 8080.

Le point d'entrée des micro services se fait sur le port 80.

Nous utilisons le reverse proxy avec des paths et non des noms de domaine
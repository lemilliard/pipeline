# Pipeline Docker

Ce dossier sert à accueillir la génération du script export.sh.

Pour importer les micro services sur la machine hôte de rancher, il faut d'abord importer ce dossier dessus, puis lancer le script dockerize.sh (supprimer avant tous les conteneurs et images pipeline).

Ensuite, dans Rancher, procéder à la création d'un stack pipeline:
[Installation Traefik & Pipeline](https://github.com/tkint/Pipeline#installation-traefik--pipeline)

# Pipeline Micro Service Resource

## Prérequis

Installer les prérequis:
```
pip install -r requirements.txt
```

Pour mettre à jour le fichier des dépendances:
```
pip freeze > requirements.txt
```

## cx_Oracle

cx_Oracle a besoin de l'Oracle Instant Client pour pouvoir fonctionner:
http://www.oracle.com/technetwork/database/database-technologies/instant-client/downloads/index.html

Après avoir choisis la version qui convient à l'OS, suivre les instructions d'installations.
Pour Windows, il faut impérativement installer le Microsoft Visual C++ Redistributable.

Pour initialiser une connexion à une base de données Oracle:

```python
import cx_Oracle

address = "[address]"
port = 1521
sid = "orcl"
dsn_tns = cx_Oracle.makedsn(address, port, sid)
con = cx_Oracle.connect("[username]", "[password]", dsn_tns)
```
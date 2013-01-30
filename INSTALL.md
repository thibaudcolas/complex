## Triple Bench : installation ##

L'application Triple Bench nécessite Tomcat 7 ainsi que la présence de plusieurs bases de données PostgreSQL 9 et d'un système de fichiers, et enfin d'une connexion à internet pour profiter de l'API Google Charts — et d'un navigateur récent.

Pour déployer l'application, il faut tout d'abord modifier le chemin absolu de l'application dans `WebContent/WEB-INF/web.xml` (nécessaire pour TDB), puis compiler l'application sous forme d'archive `.war` et placer cette archive dans `Tomcat/webapps`.

Avant de lancer Tomcat, il reste à effectuer certaines configurations des bases de données : il faut démarrer PostgreSQL et exécuter le script `conf/sdb-d2rq-setup.sql` qui se chargera de créer les bases et utilisateurs nécessaires au fonctionnement de Triple Bench.

Enfin, les coordonnées de la base de donnée utilisée sont à renseigner si elles divergent des valeurs par défaut (localhost:5432), et des scripts d'initialisation de SDB et TDB sont disponibles dans `src/com/github/masalthunlass/complexgen`.


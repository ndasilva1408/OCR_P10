# projetOcBibliotheque

` Projet n°7 du parcours DA JAVA`

Description du projet
Application Web APIREST pour les utilisateurs des bibliothèques d'une grande ville.

#Release du projet :

Application Web avec la possibilité de rechercher un ouvrage, voir le nombre d'exemplaire disponible et leurs disponibilités, consulter ses emprunts ses emprunts en cours, et pouvoir les prolonger de 4 semaines si cela n'a déjà été fait.

Réaliser un batch pour envoyer un email aux utilisateurs qui n'ont pas rendu les ouvrages dans le temps imparti à la fréquence d'une fois par jour.

#Pré-requis
Vous devez posséder Java JRE version 8 ou supérieur sur votre machine pour pouvoir correctement utiliser l'application.

#Technologies employées

`Language :`

Java 8 
TypeScript

`Front :`
Angular

`Back :`

Spring Data
Spring Web
Spring Mail
Spring Security
Zuul
Eureka
Lombok
MapStruct

`Base de données :`

PostgreSQL

`Serveur d'application :` Apache Tomcat v.9

#Restaurer la base de données
Pour restaurer la base de données de l'application, vous devez créer un serveur, puis une base de données pour chaque fichier dump. Les fichiers avec la terminologie « dump » contiennent les fichiers nécessaires à la création des tables de l'application. Les fichiers avec la terminologie « data » contiennent un jeu de donnée de l'application. Utilisez la fonction « Restore » puis sélectionnez le fichier de votre choix. Il est noté que pour toute création d’une nouvelle base de données, vous devrez obligatoirement débuté par le fichier « dump » qui contient la création des tables. Le fichier « data » s’utilise sur la base de données du même nom que vous venez de restaurer.

Les terminologies et les adresses employées pour chaque unes des bases de données peuvent être retrouver dans les fichiers applications.properties des différents micro-services.

#Installation et utilisation de l’application
`Installation`

Ouvrez votre IDE, et importez le projet extrait depuis le fichier .zip ou clonez le fichier depuis le repository : https://github.com/ndasilva1408/projetOcBibliotheque

Importez les fichiers avec Maven.

Lancez "npm install" dans le terminal du micro-service "libraryfront"

Rendez-vous dans << Project Structure >>

Allez dans l'onglet module

Sélectionnez l'un des micro-services

Allez dans l'onglet source

Cliquez sur "Add Content Root"

Dans le dossier library, sélectionnez le fichier correspondant au nom du micro-service sélectionné et cliquez sur "ok"
Rendez-vous ensuite dans dépendencies puis cliquez sur module sdk, et sélectionnez la version 8 de java ou une supérieur.

Cliquez sur "Apply"

Répétez l'opération pour tous les services.

Lancez les tests

Pour lancer les tests de l'application, sélectionnez un micro-service, cliquez droit dessus puis rendez-vous sur le menu roulant "Run Maven" puis allez sur "test" et validez.

`Démarrez l'application votre IDE (dev mod)`

Lancez « **eureka-server** »

Lancez « **ZuulGateway** »

Lancez « **book-ms** »

Lancez « **client-ms** »

Lancez « **biblio-ms** »

Lancez « **billet-ms** »

Lancez « **AngularCLI** »

Rendez-vous sur http://localhost:4200

`Déployer l'application (prod mod)`

Sélectionnez un micro-service puis cliquez droit dessus, puis rendez-vous sur le menu roulant "Run Maven" et sélectionnez "package"

Réalisez la même manipulation sur tous les micro-services sauf libraryfront SpringBoot embarquant un serveur d'application TomCat, vous n'avez plus qu'à déployer les micro-services grâce à l'aide d'un éditeur de commande et la commande : java -jar nom_du_microservice.jar.

/!\ Il est important de lancer le microservice " **eureka-server** " et " **ZuulGateway** " en premier, les autres micro-services ne requiert pas d'ordres précis. /!\



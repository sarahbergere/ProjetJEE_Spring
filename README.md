# Projet J2EE Spring Boot Marketplace

Ce projet est une application de commerce en ligne développée en utilisant Java, J2EE, Spring Boot, Thymeleaf, et d'autres technologies.

## Prérequis

Ce projet fonctionne et a été tester avec les outils suivant : 

- Java JDK 17 ou version ultérieure
- Spring Boot
- TomCat version 10.1.15
- Intellij IDEA 2023.2.5

## Configuration
Vous pouvez configurer l'application en modifiant le fichier src/main/resources/application.properties pour y modifier votre nom d'utilisateur et votre mot de passe MySql.

Attention : Veuillez à bien importer la base de donnée dans Workbench MySQL -> entrez dans votre connexion Mysql -> Server -> Data Import -> Cochez "Import from Self-Contained File" et mettez le .sql qui se trouve dans src/main/resources/ecommerce.sql puis importez.

## Lancement de l'Application

1. Assurez-vous d'avoir satisfait aux prérequis mentionnés ci-dessus.

2. Ouvrez le projet dans votre environnement de développement Java.

3. Naviguez jusqu'au fichier `ProjetJ2EeSpringBootApplication.java`.

4. Exécutez le fichier `ProjetJ2EeSpringBootApplication.java` pour démarrer l'application Spring Boot.

5. Une fois l'application démarrée avec succès, ouvrez un navigateur web.

6. Dans la barre d'adresse, saisissez l'URL suivante : [http://localhost:8080](http://localhost:8080)

7. Explorez l'application en utilisant l'interface utilisateur.

8. Profitez de l'expérience d'achat en ligne !

N'oubliez pas de vérifier la console de l'application pour les éventuels messages de journalisation ou d'erreur lors du démarrage. En cas de problème, assurez-vous que les configurations de base de données sont correctes et que le serveur Tomcat est en cours d'exécution.

**Note :** Pour arrêter l'application, retournez à votre environnement de développement Java et arrêtez l'exécution du fichier `ProjetJ2EeSpringBootApplication.java` ou fermez la fenêtre du terminal où l'application est en cours d'exécution.


## Fonctionnalités

- [X] Ajout de produits
- [X] Édition de produits
- [X] Suppression de produits
- [X] Visualisation du panier
- [X] Commande et paiement en ligne
- [X] Vérification du solde au paiement
- [X] Ajout et modification de droit de client
- [X] Page Admin
- [X] Ajout et Suppression de compte bancaire pour les clients
- [X] Envoie de mail à l'inscription et à la validation de commande 

## Auteurs 

Fait par Sarah BERGERE, Florian GAULLIER, Matéo GENTEL-DEHENNE et Marcus NDEUGOUE.

ING 2 GSI Groupe 3.

CY TECH 

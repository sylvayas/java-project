# Application de Gestion des Notes

## Description

Cette application JavaFX permet aux utilisateurs de gérer des notes avec des fonctionnalités de création, lecture, mise à jour et suppression (CRUD) de notes.
Les notes sont stockées dans une base de données et l'application fournit une interface conviviale pour l'interaction.

## Fonctionnalités

- Ajouter de nouvelles notes
- Modifier des notes existantes
- Supprimer des notes
- Vider les champs
- Voir la liste de toutes les notes
- Confirmer la suppression avec une boîte de dialogue
- Support de l'internationalisation pour le français

## Technologies Utilisées

- Java
- JavaFX
- Mysql de docker desktop


## Prérequis

- Java 8 ou supérieur


## Installation

1. Clonez le dépôt :
    ```bash
    git clone https://github.com/sylvayas/note-taking-app.git
    cd note-taking-app
    ```

2. Ouvrez le projet dans votre IDE  Eclipse.



3. Configurez la base de données :
    - Assurez-vous d'avoir une base de données mysql nommée `notetakingappdb` dans le répertoire racine du projet.
    - Créez la table `Notes` en utilisant la commande SQL suivante :
        ```sql
        CREATE TABLE Notes (
            idNote INT  AUTO_INCREMENT PRIMARY KEY,
            title VARCHAR (200),
            description VARCHAR(200)
        );
        ```

## Utilisation

1. Avant de lancez l'application, s'il vous plaît veillez d'abord enregistrer la classe DatabaseConnection, le controller BaseController et ListNotesUIController au cas ou vous rencontrez une erreur lors du lancement de l'application
2. Utilisez l'interface pour ajouter, vider les champs,modifier  ou supprimer des notes.
3. Confirmez les suppressions via les invites de boîte de dialogue.


## Contribution

1. Forkez le dépôt.
2. Créez une nouvelle branche pour votre fonctionnalité ou correction de bug.
3. Validez vos modifications et poussez la branche vers votre fork.
4. Créez une pull request avec une description détaillée de vos modifications.


## Contact

Si vous avez des questions ou des suggestions, n'hésitez pas à me contacter .

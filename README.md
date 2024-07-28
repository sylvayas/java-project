# Application de Gestion des Notes

## Description

Cette application JavaFX permet aux utilisateurs de gérer des notes avec des fonctionnalités de création, lecture, mise à jour et suppression (CRUD) de notes.
Les notes sont stockées dans une base de données et l'application fournit une interface conviviale pour l'interaction.

## Fonctionnalités

- Ajouter de nouvelles notes
- Modifier des notes existantes
- Supprimer des notes
- Vider les champs
- Rechercher une note dans la barre de recherche
- Voir la liste de toutes les notes
- Confirmer la suppression avec une boîte de dialogue
- Support de l'internationalisation pour le français

## Technologies Utilisées

- Java 22
- JavaFX
- Mysql de docker desktop
- JavaSE-17


## Prérequis

- Java 3.8
- docker desktop


## Installation

1. Clonez le dépôt :
    ```bash
    git clone https://github.com/sylvayas/note-taking-app.git
    cd note-taking-app
    docker-compose up --build 

2. Ouvrez le projet dans votre IDE  Eclipse.



## Utilisation

1. Avant de lancez l'application, s'il vous plaît veuillez d'abord enregistrer la classe DatabaseConnection, le controller BaseController et ListNotesUIController au cas ou vous rencontriez des erreurs lors du lancement de l'application
2. Utilisez l'interface pour ajouter, vider les champs,rechercher une note dans la barre de recherche,modifier ou supprimer des notes.
3. lorsque vous voulez supprimer une note veuillez d'abord selectionner la ligne avant de la supprimer et cela va de même pour la modification  
4. Confirmez les suppressions via les invites de boîte de dialogue.


## Contact

Si vous avez des questions ou des suggestions, n'hésitez pas à me contacter .

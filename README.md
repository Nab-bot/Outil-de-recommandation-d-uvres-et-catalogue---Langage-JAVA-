# Projet Java – Application de recommandation d'oeuvres et catalogue (PISE)

Application Java Swing permettant à un utilisateur de créer un compte, d’obtenir des recommandations de mangas via un questionnaire, de gérer ses favoris, et à un administrateur de gérer les utilisateurs et le catalogue de mangas.

Projet réalisé dans le cadre du Master 2 PISE à l'Université Paris-Cité.

---

## 1. Présentation générale

Cette application est une interface graphique développée en Java (Swing) permettant :

- la gestion de comptes utilisateurs (visiteur / administrateur)  
- un questionnaire de recommandation multi-critères  
- la consultation de fiches mangas détaillées (titre, auteur, description, image)  
- la gestion d’une liste de favoris  
- un espace administrateur pour gérer le catalogue global et les utilisateurs  
- une persistance des données via des fichiers texte (`data/`)  

Les données sont stockées dans des fichiers `.txt` dans le dossier `data/`, et les images dans `images/`.  
Une image par défaut `placeholder.jpg` est utilisée lorsqu’un manga n’a pas d’image dédiée.

---

## 2. Fonctionnalités

### Côté visiteur

- Création de compte  
- Connexion / déconnexion  
- Questionnaire multi-critères (genre, ambiance, univers, longueur, période, etc.)  
- Affichage d’une liste de recommandations filtrées  
- Consultation des fiches mangas (titre, auteur, description, image)  
- Gestion des favoris :  
  - ajout depuis les résultats de recommandation  
  - ajout manuel par titre  
  - ajout d’un manga hors-catalogue (utilisation du placeholder pour l’image)  
  - suppression d’un favori  

### Côté administrateur

- Gestion des utilisateurs :  
  - affichage de la liste des utilisateurs  
  - ajout d’un utilisateur  
  - promotion / rétrogradation (visiteur <-> administrateur)  
  - suppression d’un utilisateur (garde-fou empêchant la suppression de son propre compte)  

- Gestion du catalogue de mangas :  
  - ajout de nouveaux mangas  
  - modification des informations des mangas  
  - suppression de mangas  

### Interface graphique

- Fenêtre de connexion  
- Fenêtre de création de compte  
- Accueil visiteur  
- Questionnaire de recommandation  
- Résultats de recommandation  
- Fiche manga  
- Fenêtre des favoris  
- Espace administrateur (gestion des utilisateurs et du catalogue)

---

## 3. Structure du projet

```text
Projet_JAVA_PISE/
│
├── src/
│   └── projet_java/
│       ├── app/
│       │   └── Projet_JAVA.java
│       │
│       ├── model/
│       │   ├── Catalogue.java
│       │   ├── Manga.java
│       │   ├── RecoCritere.java
│       │   └── Utilisateur.java
│       │
│       ├── service/
│       │   ├── GestionFavoris.java
│       │   ├── GestionUtilisateurs.java
│       │   └── RecoSystem.java
│       │
│       └── UI/
│           ├── FenetreAccueil.java
│           ├── FenetreAdminCatalogue.java
│           ├── FenetreAdminMenu.java
│           ├── FenetreAdminUtilisateurs.java
│           ├── FenetreConnexion.java
│           ├── FenetreFavoris.java
│           ├── FenetreFicheManga.java
│           ├── FenetreQuestionnaire.java
│           └── FenetreResultat.java
│
├── data/
│   ├── catalogue.txt
│   ├── favoris.txt
│   └── utilisateurs.txt
│
├── images/
│   ├── (images des mangas : akira.jpg, berserk.jpg, etc.)
│   └── placeholder.jpg
│
└── projet.jar   (optionnel, exécutable si fourni)
4. Installation et lancement
Prérequis
Java 8 ou version supérieure

Un IDE Java (NetBeans, IntelliJ, Eclipse) ou la ligne de commande

Installation
Cloner le dépôt sur votre machine.

Vérifier que les dossiers data/ et images/ sont présents à la racine du projet.

Ouvrir le projet dans votre IDE et configurer le dossier src/ comme source.

Compiler le projet.

Lancement
Depuis l’IDE : exécuter la classe Projet_JAVA (dans app/).

Depuis le .jar (si fourni) :

bash
Copier le code
java -jar projet.jar


5. Comptes de test
Compte visiteur
login : teach

mot de passe : zeha

Compte administrateur
login : admin

mot de passe : admin123

6. Persistance des données
Les données ne sont pas stockées en base de données mais dans des fichiers texte situés dans le dossier data/ :

utilisateurs.txt : comptes et rôles (visiteur / admin)

catalogue.txt : catalogue global de mangas

favoris.txt : favoris par utilisateur

Chaque opération de création, modification ou suppression met à jour ces fichiers.

7. Résumé du fonctionnement
Au lancement, l’utilisateur arrive sur la fenêtre de connexion.

Il peut se connecter avec un compte existant ou créer un nouveau compte (visiteur).

Un visiteur peut :

lancer une recommandation,

consulter les résultats,

ajouter des mangas à ses favoris,

gérer sa liste de favoris (ajout, suppression, ajout hors-catalogue).

Un administrateur a en plus accès à :

la gestion des utilisateurs,

la gestion du catalogue de mangas.

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//ci-dessous le package ou sera rangé ce fichier
package projet_java.model;

//importer ce qu'il faut pour gérer des listes
import java.util.ArrayList;

/**
 *
 * @author nabil
 */
public class Utilisateur {

    //Un utilisateur est caractérisé par un login et mot de passe, les deux lui permettront de se connecter
    private String login;
    private String motDePasse;

    //un utilisateur sera aussi caractérisé par un rôle, il pourra etre admin 
    //ou visiteur, selon le role, il pourra utiliser certaines fonctionnalités
    private String role;  // le role est géré avec un string, 
    //Les fonctions dispo changeront selon le role de l'utilisateur connecté

    //Liste des mangas que l'utilisateur a mis dans ses favoris, enfin son catalogue perso
    private ArrayList<Manga> favoris = new ArrayList<>();

    //on construit alors le constructuer qu'on va utiliser pour les utilisateurs
    public Utilisateur(String login, String motDePasse) {
        this.login = login;
        this.motDePasse = motDePasse;
        this.role = "VISITEUR"; // par défaut le role sera visiteur
    }

    //2ème constucteur
    public Utilisateur(String login, String motDePasse, String role) {
        this.login = login;
        this.motDePasse = motDePasse;
        this.role = "VISITEUR"; // par défaut le role sera visiteur
        setRole(role);
        // si dans le fichier txt on n'a pas de role ou visiteur, alors on reste sur visiteur par défaut
        // sinon si autre chose que visiteur ou vide, on appelle la méthode setrole pour vérifier si c'est 
        //un truc valide, donc admin, on change alors l'attribut à admin, sinon par défaut ce sera visiteur
    }

    //on va ajouter les méthodes de la classe utilisateur ci-dessous
    //méthode pour obtenir le login d'un utilisateur
    public String getLogin() {
        return login;
    }

    // pas de getter public du mot de passe par prudence
    //méthode pour obtenir le rol d'un utilisateur
    public String getRole() {
        return role;
    }

    //méthode pour obtenir le catalogue perso de favoris d'un utilisateur
    public ArrayList<Manga> getFavoris() {
        return favoris;
    }

    //méthode qui fabrique la ligne à sauvegarder dans le fichier txt des utilisateur
    //format: login;motDePasse;role
    public String sauvegardeDansFichier() {
        return login + ";" + motDePasse + ";" + role;
    }

    //méthode permettant d'assigner un role à un utilisateur
    public void setRole(String nouveauRole) {
        // contrôle minimum : on n’autorise que VISITEUR ou ADMIN, il n'existe pas d'autre role
        //si c'est admin, le role passe à admin
        //dans tous les autres cas, donc une valeur invalide, vide ou ou visiteur, on garde le role visiteur par défaut
        if ("VISITEUR".equalsIgnoreCase(nouveauRole) || "ADMIN".equalsIgnoreCase(nouveauRole)) {
            //si le test est passé, alors le role de l'utilisateur est changé au nouveau role qu'on veut lui attribuer
            this.role = nouveauRole.toUpperCase();
        }
    }

    //méthode de vérification du mot de passe
    public boolean verifierMotDePasse(String mdp) {
        //si le mot de passe saisi et passé en argument n'est pas une chaine vide ET si le mot de passe 
        //saisi correspond bien à celui de cette utilisateur, on renvoie true, sinon false
        if (this.motDePasse != null && this.motDePasse.equals(mdp)) {
            return true;
        } else {
            return false;
        }
    }

    //procédure permettant d'ajouterun manga au catalogue perso des favoris d'un utilisateur
    public void ajouterFavori(Manga m) {
        //si le manga passé en paramètre n'est pas null, donc si l'objet manga existe
        if (m != null) {
            //si la liste des favoris ne contient pas déjà le manga en question
            if (favoris.contains(m) == false) {
                favoris.add(m); // Alors on l'ajoute
            }
        }
    }

    //procédure permettant de retirer un manga au catalogue perso des favoris d'un utilisateur
    public void retirerFavori(Manga m) {
        //si le manga passé en paramètre n'est pas null, donc si l'objet manga existe
        if (m != null) {
            //si la liste des favoris contient déjà le manga en question
            if (favoris.contains(m) == true) {
                favoris.remove(m); // Alors on le retire
            }
        }
    }

//méthode pour faire une recherche par titre d'un manga dans la liste des 
//favoris d'un utilisateur
    public Manga chercherFavoriParTitre(String titre) {
        //boucle for each permettant de parcourir chaque manga de la liste des favoris
        if (titre != null) {
            for (Manga m : favoris) {

                //création d'un string t qui va recevoir le titre du manga
                String t = m.getTitre();
//pour chaque objet manga on appelle la méthode permettant d'obtenir le titre
                //si ce titre est identique à celui passé en argument de la fonction, on renvoie le manga
                if (t != null && t.equalsIgnoreCase(titre)) {
                    return m;//on renvoie le manga, et on arrete alors la fonction

                }
            }
        }

        return null;//si aucun manga n'a été renvoyé, alors on atteint cette 
        //instruction, on renvoie donc null car aucun manga ne correspond au titre

    }

//méthode permettant de savoir si l'utilisateur connecté est admin
    public boolean estAdmin() {
        //si le role est différent de null
        if (role != null) {
            //si role vaut admin, alors on renvoie true car la fonction 
            //renvoie un booléen indiquant si admin ou pas
            if (role.equalsIgnoreCase("ADMIN")) {
                return true;
            }
        }
        return false;
    }
}

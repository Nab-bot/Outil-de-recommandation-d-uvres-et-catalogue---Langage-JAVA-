/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_java.model;

/**
 *
 * @author nabil
 */
public class Manga {

    // Attributs représentant les caractéristiques d'un manga
    private String titre;
    private String auteur;

    //l'année de sortie sera un int, ne va pas gérer de date précise je pense, juste l'année de sortie
    private int anneeSortie;

    //le genre ça oeut désigner soit de l'horreur, soit du fantastique, etc...
    private String genre;

    // Type de manga (ex : Shonen, Seinen,...)
    private String type;

    // ambiance générale du manga (ex : Sombre, Humoristique, Mature, etc.)
    private String ambiance;

    // nombre total de chapitres (0 si inconnu)
    private int longueurChapitres;

    // univers fantastique ou réaliste, ça servira de filtre assez efficace pour les reco
    private String univers;

    //chemin de l'image qu'on voudra afficher (les images seront surementd dans un dossier et on indiquera l'url de l'image)
    private String image;

    //on ajoute une description au manga, là aussi c'est facultatif
    private String description;

    //Constructeur pour initialiser les infos d'un manga
    public Manga(String titre, String auteur, int anneeSortie, String genre) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneeSortie = anneeSortie;
        this.genre = genre;
        //type, ambiance, longueur de chapitre, l'univers sont null par défaut (enfin 0 pour le longueur de chapitre)        
        //l'image est null par défaut
        //le synopsis aussi est null par défaut
    }

    //je raoute un autre construcuteur à 8 paramètres cette fois car ça faisiait 
    //planter quand j'ai essayé de faire l e.jaar aavec le 10 paramètres juste ne dessous
    public Manga(String titre, String auteur, int anneeSortie, String genre,
            String type, String ambiance, int longueurChapitres, String univers) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneeSortie = anneeSortie;
        this.genre = genre;
        this.type = type;
        this.ambiance = ambiance;
        this.longueurChapitres = longueurChapitres;
        this.univers = univers;
        this.description = null;
        this.image = null;
    }

    //on ajoute un autre constructuer plus complet, j'avais un bug dans la classe catalogue et ça venait de là
    public Manga(String titre, String auteur, int anneeSortie, String genre,
            String type, String ambiance, int longueurChapitres, String univers, String description, String image) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneeSortie = anneeSortie;
        this.genre = genre;
        this.type = type;
        this.ambiance = ambiance;
        this.longueurChapitres = longueurChapitres; 
        this.univers = univers;
        // image et description resteront null par défaut et pourront être fixées via setImage/setDescription
        this.description = description;
        this.image = image;

    }

    /*
    //constructeur plus complet comprenant image et synopsis
    public Manga(String titre, String auteur, int anneeSortie, String genre, String synopsis) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneeSortie = anneeSortie;
        this.genre = genre;
        this.synopsis = synopsis; 
        // image reste null par défaut
    }
     */
    //Getters pour le titre, l'auteur, etc...
    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getAnneeSortie() {
        return anneeSortie;
    }

    public String getGenre() {
        return genre;
    }

    public String getType() {
        return type;
    }

    public String getAmbiance() {
        return ambiance;
    }

    public int getLongueurChapitres() {
        return longueurChapitres;
    }

    public String getUnivers() {
        return univers;
    }

    // Getter pour l'image (chemin, peut être null)
    public String getImage() {
        return image;
    }

    //Getter permettant d'obtenir la description du manga
    public String getDescription() {
        return description;
    }

//Setters pour les infos du manga aussi, servira à modifier lesdites infos
// Retourne true si la valeur est acceptée, false sinon
    //prend une chaine de caractères t en paramètre, c'est le nouveau titre qu'on voudra mettre
    public boolean setTitre(String t) {
        //si la chaine passée en paramètre, donc t, n'est pas null et 
        //a une longueur supérieure à 0 (on fait ça pour gérer le cas d'une chaine vide)
        if (t != null && t.length() > 0) {
            //alors on remplace le titre d'avant par celui passé en paramètre
            titre = t;
            //on renvoie true pour dire que le changement a bien eu lieu
            return true;
        } else {
            //on renvoie false si le changement n'a pas pu avoir lieu
            return false;
        }
    }

    //setter pour l'auteur, c'est la même logique que pour les titres
    public boolean setAuteur(String a) {
        //pareil que le setter du titre, il faut que la chaine de caractère soit
        //non null et pas vide, donc une taille supérieure strictement à 0
        if (a != null && a.length() > 0) {
            auteur = a;
            return true;
        } else {
            return false;
        }
    }

    //setter pour l'année de sortie
    public boolean setAnneeSortie(int annee) {
        // pour l'année on ne prend pas les valeurs négatives
        if (annee >= 0) {
            anneeSortie = annee;
            return true;
        } else {
            return false;
        }
    }

    //setter pour le genre, idem quye les setters précédents qui ont utilisé des string
    public boolean setGenre(String g) {
        if (g != null && g.length() > 0) {
            genre = g;
            return true;
        } else {
            return false;
        }
    }

    //setter pour le type de manga
    public boolean setType(String t) {
        if (t != null && t.length() > 0) {
            type = t;
            return true;
        } else {
            return false;
        }
    }

    //idem pour l'ambiance, et les autres setters marchent pareil donc je vais arreter de commenter ça c'est redondant
    public boolean setAmbiance(String a) {
        if (a != null && a.length() > 0) {
            ambiance = a;
            return true;
        } else {
            return false;
        }
    }

    //idem pour le nombre de chapitres, ici comme c'est un int
    public boolean setLongueurChapitres(int nb) {
        // on controle qu'il vaut au moins 1 car un manga avec 0 chapitres...bah c'est pas possible
        if (nb > 0) {
            longueurChapitres = nb;
            return true;
        } else {
            return false;
        }
    }

    public boolean setUnivers(String u) {
        if (u != null && u.length() > 0) {
            univers = u;
            return true;
        } else {
            return false;
        }
    }

    //setter pour l'image (c'est bien un string czr on indique le chemin vers l'imazge)
    public boolean setImage(String img) {
        if (img != null && img.length() > 0) {
            image = img;
            return true;
        } else {
            return false;
        }
    }

    //setter pour modifier le synopsis d'un manga donné 
    //le fonctionnement est similaire aux setter ci-dessus, 
    //je ne vasi pas rajouter de commentaires ici ce serait redondant
    public boolean setDescription(String d) {
        if (d != null && d.length() > 0) {
            description = d;
            return true;
        } else {
            return false;
        }
    }

    //affichage des infos pour un manga
    public void afficherInfos() {
        System.out.println("Titre : " + titre);
        System.out.println("Auteur : " + auteur);
        System.out.println("Année de sortie : " + anneeSortie);
        System.out.println("Genre : " + genre);
//afficher le type 
        if (type != null && type.length() > 0) {
            System.out.println("Type : " + type);
        }
//afficher l'ambiance 
        if (ambiance != null && ambiance.length() > 0) {
            System.out.println("Ambiance : " + ambiance);
        }
//afficher le nombre de chapitres
        if (longueurChapitres > 0) {
            System.out.println("Longueur (chapitres) : " + longueurChapitres);
        }
//afficher l'univers 
        if (univers != null && univers.length() > 0) {
            System.out.println("Univers : " + univers);
        }
// afficher le chemin de l'image si présent
        if (image != null && image.length() > 0) {
            System.out.println("Image : " + image);
        }
        //s'il y a un synopsis, on l'affiche aussi !
        if (description != null && description.length() > 0) {
            System.out.println("Description : " + description);
        }

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//on précise dans quel dossier se situe ce fichier
package projet_java.model;

//on va devoir gérer des lsites, donc on fait les imports ,nécéssaires
import java.util.ArrayList;

//d'autres imports doivent être faits pour le fichier txt qui aura les infos des manga dedans
import java.io.File;//car on va gérer un fichier txt avec les infos sur les mangas
import java.io.FileNotFoundException;
import java.io.FileWriter; //on va écrire dans le fichier donc on a besoin d'importer cette classe !
import java.util.Scanner; //on veut récupérer le contenu du fichier, d'où cet import

/**
 *
 * @author nabil
 */
//ça va tre le catalogue global de l'application, ce sera une liste de manga
public class Catalogue {

    // création de la liste interne des mangas du catalogue global
    private ArrayList<Manga> baseManga = new ArrayList<Manga>();

    // méthode pour récupérer la liste du catalogue 
    public ArrayList<Manga> getBase() {
        return baseManga;
    }

    // Ajouter un manga si la paramètre saisi est non nul et s'il n'existe pas déjà (même titre + même auteur)
    public boolean ajouterManga(Manga m) {
        //si le manga saisi en paramètre est non nul
        if (m != null) {
            // on vérifie qu'il n'existe pas déjà un manga avec même titre ET même auteur
            if (rechercherParTitreEtAuteur(m.getTitre(), m.getAuteur()) == null) {
                baseManga.add(m);
                return true; //si le manga n'existe pas deja dans la liste, 
                //on l'ajoute et on renvoie le booléen à true pour dire que ça a bien été fait
            } else {
                return false; // sinon on renvoie false car déjà dans le catalogue
            }
        } else {
            //ce else permet de gérer le cas où m est null, c'est pas à la saisie mais si aucun manga n'a été créé avant
            return false;
        }
    }

    // Retirer un manga s'il est présent dans la liste
    public boolean retirerManga(Manga m) {
        if (m != null) {
            // Si la base contient ce manga
            if (baseManga.contains(m)) {
                baseManga.remove(m); // on le retire
                return true;    // on confirme que ça a été fait
            } else {
                return false;   // sinon on renvoie false car rien retiré car il n'était pas présent
            }
        } else {
            return false;       // null passé en paramètre, donc on return false aussi
        }
    }

    //fonctions de recherche insensibles à la casse
    //fonction de recherche par titre, elle prend un string ne paramètre
    public Manga rechercherParTitre(String titre) {
        //si le string passé en paramètre n'est pas null
        if (titre != null) {

            //on parcourt la liste des mangas
            for (Manga m : baseManga) {
                //on on créé un string t, à chaque manga parcouru, il se voit affecté le titre du manga courant
                String t = m.getTitre();
                //on compare alors le string t avec le titre passé en paramètre en ignorant la casse
                //V1 de la condition de détection du titre : if (t != null && t.equalsIgnoreCase(titre)) 
                //V2 de la condition pour chercher par titre
                //donc si t est non null et vaut soit le titre, soit le titre avec un espace devant ou derrière au cas ou
                if (t != null && (t.equalsIgnoreCase(titre) || t.equalsIgnoreCase(" " + titre) || t.equalsIgnoreCase(titre + " "))) {

                    //si ça correspond, alors on a trouvé notre manga, on peut alors le return !
                    return m;

                }
            }
        }
        return null; // aucun manga n'a été trouvé, on renvoie null donc !
    }

    //fonction de recherche par auteur avec plusieurs résultats possibles car un meme auteur peut avoir écrit pluseurs mangas 
    //(par exemple urasawa a ecrit 20th century boys mais aussi monster, autrement, si je mettai un return pour un seul résultat, je pense que ça donnerait le 1er trouvé
    public ArrayList<Manga> rechercherParAuteur(String auteur) {

        //on créé une liste de resultats qu'on va renvoyer à la fin de la fonction
        ArrayList<Manga> auteurs = new ArrayList<Manga>();

        //si l'auteur en paramètre de la fonction n'est pas null, si le manga dans la base n'a pas d'auteur précisé ça évitera un bug je pense
        if (auteur != null) {

            //on parcourt la liste des mangas
            for (Manga m : baseManga) {

                //on créé un string a qui va prendre successivement chaque auteur de la liste des mangas, enfin pour chaque manga de la liste parcourue, a prendra le valeur de l'auteur
                String a = m.getAuteur();

                //si a, donc l'auteur du manga en cours dans la liste qsu'on parcourt, est non null et que la chaine de caractère passée en paramètre de la fonction correspond au string a
                if (a != null && a.equalsIgnoreCase(auteur)) {

                    //alors on ajoute le manga à la liste des résultats
                    auteurs.add(m);
                }
            }
        }
        return auteurs; // on return la liste des résultats trouvés, elle est possiblement vide si on a rien trouvé
    }

    //fonction de recherche par titre ET auteur, elle renvoi un seul manga, 
    //c'est pour ça quer c'est une fonction à part, celle de recherche par auteur 
    //renvoie plusieurs manga, donc je peux pas juste combiner celle-là avec 
    //celle de la recherche par titre je pense, d'ou une fonction à part
    //la fonction prend en paramètres deux string, l'auteur et le titre
    public Manga rechercherParTitreEtAuteur(String titre, String auteur) {
        //on fait le test pour vérifier que ni le titre ni l'auteur en paramètre ne sont pas nulls
        if (titre != null && auteur != null) {
            //on parcourt alors la liste des mangas
            for (Manga m : baseManga) {
                //de là, on créé deux string, t pour le titre du manga a pour l'auteur
                //ces deux variables vont prendre chaque valeur de l alsite, donc d'abord titre et auteur du 1er manga, puis du 2ème, etc...
                String t = m.getTitre();
                String a = m.getAuteur();
                //si les deux variables sont non nulls et que celles-ci 
                //correspondent aux paramètres, donc l'auteur et le titre
                if (t != null && a != null
                        && t.equalsIgnoreCase(titre)
                        && a.equalsIgnoreCase(auteur)) {
                    //alors on return le manga trouvé qui correspond donc au titre ET au manga pssés en paramètres
                    return m;
                }
            }
        }
        //on return null dans le cas où le 1er if n'a pas été passé ou si le 2nd if 
        //n'a pas été passé non plus, car s'il était passé, alors on aurait return m et on aurait arreté la fonction
        return null;
    }

    //fonction pour filtrer par genre
    public ArrayList<Manga> filtrerParGenre(String genre) {
        //créerune liste de résultats qu'on va return à la fin de la fonction
        ArrayList<Manga> genres = new ArrayList<Manga>();
        //si le genre n'est pas null
        if (genre != null) {
            //on parcourt la liste des mangas
            for (Manga m : baseManga) {
                //on créé un string qui va prendre successivevement chaque valeur du champ genre des différents mangas de la liste
                String g = m.getGenre();
                //si g ne vaut pas null et qu'il correspond à celui passé en argument de la fonction
                if (g != null && g.equalsIgnoreCase(genre)) {
                    //on ajoute le manga à la liste qui sera renvoyée quand on va vouloir filtrer
                    genres.add(m);
                }
            }
        }
        //on return la liste des mangas qui collent à un genre donné
        return genres;
    }

    //fonction pour azfficher le catalogue globnal
    public void afficherCatalogue() {
        //on parcourt la liste des mangas du catalogue global
        for (int i = 0; i < baseManga.size(); i++) {
            //l'objet m de la classe manga se voit affecté du ième éléménet de la luiste du catalogue global  
            //la méthode get ici donne l'élément de la ième psoition dans la liste, base contient des objets manga, l'objet manga est stocké dans la variable mm 
            Manga m = baseManga.get(i);
            //on appelle la méthjode permettant d'afficher les infos d'un manga donné pour celui en cours de la liste, donc le ième manga
            //la méthode afficherInfos est dans la classe Manga
            m.afficherInfos();
            //on met une séparation entre chaque ligne pour la lisibilité
            System.out.println("--------------------");
        }
    }

    //méthode qui va charger les données du fichier txt qui contient les infos du manga, il contient aussi l'url ds images stockées dans le dossier images du proojet
    //elle prend en paramètre le chemin du fichier, donc sa localisation depuis la racine il me semble
    //en paramètre on aura cheminFichier car cette variable pourra concerner ou bien le fichier du catalogue global, ou celui des favoeris d'un utilisateur, voie les utilisateurs eux-m
    //à l'appel de la fonction, on devra juste changer la valeur du paramètre cheminFichier passé en entrée
    public void chargerDepuisFichier(String cheminFichier) {
        //on utilise un try catch pour gérer les erreurs qui peuvent arriver et qui sont liées aux fichiers
        try {

            File fichier = new File(cheminFichier); // chemin vers le fichier txt qu'on va lire, je mets le chemin depuis source, à voir si c'est bon ou si je dois remonter encore 
            Scanner sc = new Scanner(fichier); // Scanner pour lire le fichier

            //tant qu'il y a des entrées, des lignes
            while (sc.hasNextLine()) {
                //alors la variable ligne se voit attribuée la ligne que le scanner sc a lu
                String ligne = sc.nextLine();

                //si la longueur de la ligne récupérée est strictement supérieure à 0,donc que ce n'est pas une ligne vide tout simplepment
                if (ligne.length() > 0) {
                    //On créé un tableau pour stocker les colonnes trouvées, il aura 10 cases car 10 colonnes
                    String[] colonnes = new String[10]; // titre; auteur; année; genre; type; ambiance; longueur; univers; description; image
                    //on créé une variable de type entier int qui va indiquer l'index de la colonne, donc quelle colonne des 10 possibles évoquées ci-dessus
                    int colIndex = 0;
                    //création d'un string valeur qui sera une chaine vide à la création, on s'en servira pour concaténer par la suite
                    String valeur = "";

                    //on va alors parcourir la ligne pour évaluer chaque caractère
                    for (int i = 0; i < ligne.length(); i++) {
                        //le caractère c va prendre succsessibvemtn comme valeur chaque caractère de la ligne
                        char c = ligne.charAt(i);
//si le caractère en cours est un point virgule, donc notre séparateur de colonnes
                        if (c == ';') {
                            // à chaque ';', on termine la valeur courante et on passe à la colonne suivante
                            //si on est pas deja a la derniere colonne, on assigne à lal colonne la valeur de la chaine de caractères valeur qui aura été concaténée
                            if (colIndex < 10) {
                                colonnes[colIndex] = valeur;
                            }
                            valeur = "";      // on réinitialise la variable valeur à une chaine vide
                            //si jamlais on est avant la 9ème colonne, alors on augmente de 1 pour arriver à l'avant denrière colonne
                            if (colIndex < 9) {
                                colIndex = colIndex + 1; // on avance d'une colonne 
                            }
                            // si on a déjà 9 colonnes, on ignore les suivantes

                        } //si le caractère en cours n'est PAS un point virgule, alors on est tjrs dans la colonne en cours et donc on concatène le caractère à la chaine de string valeur
                        else {
                            // on concatène
                            valeur = valeur + c;
                        }
                    }
                    //une fois sortie de cette boucle, on veut ranger la dernière valeur, 
                    if (colIndex < 10) //ATTENTION on commence à 0, donc la 9ème colonne est en fait la dernière, la 10ème, d'ou cette condition !
                    {
                        //on range juste la valeur qu'on a récupéré
                        colonnes[colIndex] = valeur;
                    }

                    // on contrôle qu'on a bien au moins 4 colonnes (titre, auteur, annee, genre)
                    // la colonne des images, la 5ème, est optionnelle, idem pour les autres (j'ai ajouté ambiance ou univers en plus
                    if (colonnes[0] != null && colonnes[1] != null && colonnes[2] != null && colonnes[3] != null) {
                        String titre = colonnes[0];
                        String auteur = colonnes[1];

                        //l'année est un int et on le récupère aussi dans le fichier, sous forme de string, on doit donc le convertir en int
                        int anneeSortie = Integer.parseInt(colonnes[2]);

                        //le genre, c'est la 4ème colonne (donc comme ça commence à 0, c'est 3)
                        String genre = colonnes[3];

                        //on initialise à null (ou 0 pour le int) ces variables
                        String type = null;
                        String ambiance = null;
                        //on met le nombre de chapitres à 0 de base
                        int longueur = 0;
                        String univers = null;
                        String description = null;

                        //l'image c'est optionnel, donc par défaut je mets null
                        String image = null;

                        //le synopsis c'est optionnel, donc par défaut je mets null
                        //String description = null;
//ces if vont servir à gérer le cas ou le contenu de ces 
                        //colonnes n'est pas null, donc ce sera pour le type de manga, l'ambiance, la longueur et l'univers
                        if (colonnes[4] != null && colonnes[4].length() > 0) {
                            type = colonnes[4];
                        }
                        if (colonnes[5] != null && colonnes[5].length() > 0) {
                            ambiance = colonnes[5];
                        }
                        //ici on va aussi utiliser parseint pour convertir le string en int
                        if (colonnes[6] != null && colonnes[6].length() > 0) {
                            longueur = Integer.parseInt(colonnes[6]);
                        }
                        if (colonnes[7] != null && colonnes[7].length() > 0) {
                            univers = colonnes[7];
                        }

                        //idem pour la description 
                        if (colonnes[8] != null && colonnes[8].length() > 0) {
                            description = colonnes[8];
                        }

//on va gérer le cas où justement limage ne sera pas null
                        if (colonnes[9] != null && colonnes[9].length() > 0) {
                            image = colonnes[9]; // on affecte à la variable image la 9ème colonne  (donc la colonne 4 car ça démarre à 0 et pas à 1) si elle existe
                        }
                        // On créé un objet Manga avec ces infos, attention l'image étant optionnelle, on ne la met pas ici mais juste après
                        Manga m = new Manga(titre, auteur, anneeSortie, genre, type, ambiance, longueur, univers);

                        //ajout de l'image (optionnel du coup) 
                        //si image n'est pas null et que la chaine de caractère comporte au moins un caractère (on va se servir du nom des images pour les appeler)
                        if (image != null && image.length() > 0) {
                            //alors on modifie image de l'objet manga m
                            m.setImage(image);
                        }
                        //idem pour l'ajout du synopsis, optionnel et le fonctionnement est le m^me
                        if (description != null && description.length() > 0) {
                            m.setDescription(description);
                        }

                        // et on l’ajoute dans le catalogue global du site, on a une fonction pour ça donc on l'appelle, elle évitera notamment les doublons
                        ajouterManga(m);

                    }
                }
            }
            sc.close(); //on libère la ressource car on a fini de lire le fichier
        } catch (FileNotFoundException e)//cette instruction sert à prendre en compte l'erreur si le fichier n'est pas trouvé avec le try catch
        {
            //ça c'est le message qi va s'afficher si le try catch attrappe quelque chose
            System.out.println("Je n'ai pas trouvé ce fichier !");
        }
    }

    //méthode pour sauvegarder le catalogue global dans un fichier txt
    //elle prend en paramètre le chemin du fichier, donc sa localisation depuis la racine
    //à l'appel de la fonction, il suffira de donner le fichier cible (par ex. "catalogue.txt")
    public void sauvegarderDansFichier(String cheminFichier) {
        try {
            // création de l’objet fichier + FileWriter
            File f = new File(cheminFichier);
            FileWriter fw = new FileWriter(f, false);
            // false = on écrase le fichier existant, avec true ce serait en ajout à la fin si j'ai bien compris

            // on parcourt chaque manga de la base
            for (Manga m : baseManga) {
                // on reconstitue une ligne en récupérant chaque élément, donc le titre;auteur;année;genre;image
                String ligne = m.getTitre() + ";"
                        + m.getAuteur() + ";"
                        + m.getAnneeSortie() + ";"
                        + m.getGenre() + ";";

                // type
                if (m.getType() != null && m.getType().length() > 0) {
                    ligne += m.getType();
                }
                ligne += ";";

                // ambiance
                if (m.getAmbiance() != null && m.getAmbiance().length() > 0) {
                    ligne += m.getAmbiance();
                }
                ligne += ";";

                // longueur (nb chapitres)
                if (m.getLongueurChapitres() > 0) {
                    ligne += m.getLongueurChapitres();
                }
                ligne += ";";

                // univers
                if (m.getUnivers() != null && m.getUnivers().length() > 0) {
                    ligne += m.getUnivers();
                }
                ligne += ";";

                //si une description est renseignée : 
                if (m.getDescription() != null && m.getDescription().length() > 0) {
                    ligne += m.getDescription();
                }
                ligne += ";"; // laisser la colonne vide si pas de synopsis

// si une image est renseignée, on l’ajoute en 10ème colonne
                if (m.getImage() != null && m.getImage().length() > 0) {
                    ligne += m.getImage();
                }

                // on ajoute le retour à la ligne pour séparer les lignes
                ligne += "\n";

                // on écrit la ligne dans le fichier
                fw.write(ligne);
            }

            // on ferme le fichier après avoir écrit dedans
            fw.close();

            //et un petit message de confirmation pour qu'on sache que ça s'est bien passé !
            System.out.println("Catalogue sauvegardé avec succès dans " + cheminFichier);

        } //ça, c'est le catch au cas où une erreur est arrivée quand on a traité le fichier
        catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

}

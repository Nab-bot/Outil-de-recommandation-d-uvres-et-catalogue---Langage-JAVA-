/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_java.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import projet_java.model.Utilisateur;

/**
 *
 * @author nabil
 */
public class GestionUtilisateurs {

    // on créé une liste interne des utilisateurs
    private ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

    // Getter pour accéder à la liste créée juste avant, ça servira pour l'affichage
    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    //méthode  de recherche d'un utilisateur en utilisant son login
    //la méthode reçoit en argument le login
    public Utilisateur rechercherParLogin(String login) {
        //si le login n'est pas null
        if (login != null) {
            //on parcourt la liste des utilisateurs
            for (Utilisateur u : utilisateurs) {
                //on créé une variable l qui va stocker le login de chaque utilisateur de la liste, 
                //l va prendre successivement la valeur de chaque login de la liste des utilisateurs
                String l = u.getLogin();
                //si l est non null et qu'il est identiqueau login passé en argument
                if (l != null && l.equalsIgnoreCase(login)) {
                    //alors on arrete la méthode et on renvoie le résultat, qui est donc l'utilisateur u trouvé
                    return u;
                }
            }
        }
        //si la focntion ne s'est pas arretée avant, on renvoie null
        return null;
    }

    //méthode d'inscription des utilisateurs : 
    //la méthode prend un login et un mot de passe en argument
    public Utilisateur inscrire(String login, String motDePasse) {
        //si les deux argumlents sont null, on renvoie null
        if (login == null || motDePasse == null) {
            return null;
        }
        // on vérifie aussi l'unicité du login, si la recherche de login renvoie autre chose que null, 
        //alors le login est deja pris, on renvoie alors null car on peut pas créer un nouvel utilisateur
        if (rechercherParLogin(login) != null) {
            //si on rentre dans ce if, on return null aussi
            return null;
        }

        //enbfin, si on a évité les deux if au-dessus, alors on peut créer un utilisateur
        Utilisateur u = new Utilisateur(login, motDePasse); // on ne précise pas le role car par défaut ce sera visiteur
        //on ajoute l'utilisateur créé à la liste qu'on avait créé au-dessus
        utilisateurs.add(u);
        //on return l'utilisateur
        return u;
    }

    //méthode de connexion d'un utilisateur
    //idem, ça prend un login et un mot de passe en argument
    public Utilisateur connecter(String login, String motDePasse) {
        //pareil que dans la méthode inscrire
        if (login == null || motDePasse == null) {
            return null;
        }
        //si on n'a pas déjà eu de return null, on lance la recherche via le login dans la liste des utilisateurs
        Utilisateur u = rechercherParLogin(login);
        //si aucun login ne colle, on n'entre pas dans le if
        if (u != null) {
            //si un login correspond, alors on appelle cette méthode qui va comparer le mot de passe stocké dans la lsite pour cet utilisateur (enfin ce login)
            if (u.verifierMotDePasse(motDePasse)) {
                //si pour le login en question, le mot de passe colle aussi, alors on return l'utilisateur trouvé !
                return u;
            }
        }
        //sinon, on return null
        return null;
    }

    // Méthode pour promouvoir en ADMIN (seulement si l'utilisateur est déjà dans la liste)
    //la méthode prend en argument un utilisateur u
    public boolean promouvoir(Utilisateur u) {
        //si u non null et que la liste contient bien cet utilisateur
        if (u != null && utilisateurs.contains(u)) {
            //on appelle la méthode setrole pour passer le role de cet utilisateur en admin
            u.setRole("ADMIN");
            //on renvoie true pour savoir que l'opération a marché
            return true;
        }
        //si l'utilisateur n'a pas été trouvé, on return false
        return false;
    }

    // Rétrograder en VISITEUR, c'est la meme chose quasiment
    public boolean retrograder(Utilisateur u) {
        if (u != null && utilisateurs.contains(u)) {
            u.setRole("VISITEUR");
            return true;
        }
        return false;
    }

    // Supprimer un utilisateur, ce sera via l’admin
    //la logique est similaire aux méthodes ci-dessus sauf sur le remove
    public boolean supprimer(Utilisateur u) {
        if (u != null) {
            //si l'utilisateur est bien dans la liste, on le supprime de ladite liste
            if (utilisateurs.contains(u)) {
                utilisateurs.remove(u);
                return true;
            }
        }
        return false;
    }

    //charger depuis le fichier
    public void chargerUtilisateurDepuisFichier(String cheminFichier) {
        try {

            File fichier = new File(cheminFichier);// chemin vers le fichier txt qu'on va lire, je mets le chemin depuis source, à voir si c'est bon ou si je dois remonter encore 
            Scanner sc = new Scanner(fichier);

            //tant qu'on a des lignes
            while (sc.hasNextLine()) {
                //on créé une variable ligne qui va recevoir comme contenu la ligne que le scanner a lu
                String ligne = sc.nextLine();
                //si les lignes ne sont pas vides, donc au moins un caractère de longueur
                if (ligne.length() > 0) {

                    //on créé un tableau se string à 3 colonnes
                    String[] colonnesUtilisateurs = new String[3]; // login ; mot de passe ; role
                    //on créé 2 variables qu'on initialise, l'une est un int qui indique l'indice de la colonne, donc 0, 1 ou 2 vu qu'on a 3 colonnes
                    int colIndex = 0;
                    //la 2ème variable créée est un string qui est initialisé comme une chaine vide, on va la concaténer par la suite
                    String valeur = "";

                    //boucle for qui va parcourir la ligne caractère par caractère
                    for (int i = 0; i < ligne.length(); i++) {
                        //on créé une variable c qui va prendre successivement la valeur de chaque caractère de la lgine
                        char c = ligne.charAt(i);
                        //si le caractère en question est un point virgule, c'est notre séparateur de colonnes, alors on doit passer à la colonne suivante
                        if (c == ';') {
                            //si la variable indiqant l'indice de la colonne est inférieur à 3 (comme ça démarre à 0 ça va jusqu'à 2 c'est pour ça)
                            if (colIndex < 3) {
                                //alors on assigne le contenu de la variable valeur, qu'on a concaténé, à la case du tableau correspondant, enfin la colonne  ici
                                colonnesUtilisateurs[colIndex] = valeur;
                            }
                            //comme on a déjà affecté le contenu de valeur à sa colonne, 
                            //enfin sa case du tableau, on peut passer à la colonne suivante qui 
                            //aura sa propre valeur, donc on réinitialise valeur à une chaine vide pour 
                            //refaire la meme opération sur la colonne suivante
                            valeur = "";
                            //cette condition gère le passage à la colonne suivante, si on n'a pas encore 
                            //atteint la dernire colonne, donc 2 ici car on a 3 colonnes mais on dmarre  0, donc 0, 1, 2
                            if (colIndex < 2) {
                                //on passe à la colonne suivante en fsiant +1
                                colIndex = colIndex + 1;
                            }
                        } //si le caractère en cours n'est pas un point virgule, donc on ne gère pas 
                        //de passage à la colonne suivante, on ne fait que concaténéer le caractère ne cours à la 
                        //variable valeur qui sera assignée par la suite à a la colonne
                        else {
                            valeur = valeur + c;
                        }
                    }
                    //c'est le dernier champ, on n'aura pas de nouveau point virgule 
                    //mais on arrive à la fin, donc on range dans la dernière colonne       
                    if (colIndex < 3) {
                        colonnesUtilisateurs[colIndex] = valeur;
                    }

                    //on a créé et rempli notre liste, on va créer des objets utilisateurs à partir de ladite lsite
                    // [0]=login , [1]=motDePasse , [2]=role (peut être null ou vide car par défaut ce sera visiteur)
                    //on vérifie que les colonnes 0 et 1 sont remplies, pas le 2 car le role n'est pas obligé ici
                    if (colonnesUtilisateurs[0] != null && colonnesUtilisateurs[1] != null) {
                        //on créé 2 variables, l'une stockera le login et lautre le mot de passe
                        String login = colonnesUtilisateurs[0];
                        String mdp = colonnesUtilisateurs[1];

                        // Si la 3ème colonne, donc le role, existe ET est non vide on appelle setrole
                        // Sinon on garde VISITEUR par défaut via le 1er constructeur.
                        //on créé la variable u de typer utilisateur, on va créé l'objet jsute après
                        Utilisateur u;

                        //test pour savoir si on a les infos sur les 3 colonnes, donc ne incluant celle du role
                        if (colonnesUtilisateurs[2] != null && colonnesUtilisateurs[2].length() > 0) {
                            //si on passe ce if, alors on créé un nouvel objet utilisateur qui aura les 3 infos renseignées, sinon par défaut c'est le role visiteur
                            u = new Utilisateur(login, mdp, colonnesUtilisateurs[2]); // setRole gère ADMIN/VISITEUR
                        } else {
                            //dans ce cas, le role n'est pas renseigné donc par défait c'est visiteur
                            u = new Utilisateur(login, mdp);
                        }

                        //vérifier qu'il n'y ait pas de doublon (si jamais le fichier en comporte, ça évitera les problèmes)
                        //si la recherche par login renvoie null, alors le login n'a pas été trouvé, on peut donc l'ajouter à la liste car pas deja dedans
                        if (rechercherParLogin(login) == null) {
                            utilisateurs.add(u);
                        }
                    }
                }
            }
            //on ferme la ressource, enfin le scanner ici
            sc.close();
            /*
            // On a rajouté une sécurité au cas ou, si aucun admin n'existe après chargement, on en crée un par défaut
            if (aucunAdminPresent()) {
                //si la méthode ne détecte aucun admin, on en créé un par défaut
                Utilisateur admin = new Utilisateur("admin", "motPasseAdmin", "ADMIN");
                //on rajoute un test, si aucun utilisateur avec le login admin n'est trouvé, on l'ajoute bien à la liste des utilisateurs
                if (rechercherParLogin("admin") == null) {
                    utilisateurs.add(admin);
                }
            }
             */

            //on met un message de confirmation disant que les utilisateurs du fichier txt ont bien été chargés
            System.out.println("Utilisateurs chargés depuis " + cheminFichier);

            //enfin le catch du try catch qui entourait tout le traitement du fichier qu'on vient de faire
        } catch (FileNotFoundException e) {
            System.out.println("Fichier utilisateurs introuvable : " + cheminFichier);
            /*           
// Création d'un admin par défaut si le fichier n'existe pas encore
            if (aucunAdminPresent()) {
                Utilisateur admin = new Utilisateur("admin", "admin", "ADMIN");
                utilisateurs.add(admin);
             
            System.out.println("Admin par défaut créé (admin/admin).");
            }
             */
        }
        //on ajoute une sécurité après le try catch
        // Garantie : s'il n'y a aucun ADMIN après lecture, on crée un admin par défaut
        if (aucunAdminPresent() && rechercherParLogin("admin") == null) {
            //on ajoute alors un admin par défaut
            utilisateurs.add(new Utilisateur("admin", "motPasseAdmin", "ADMIN"));
            //on ajoute un message de confirmation indiquant au passage 
            System.out.println("Admin par défaut créé avec le login : admin et le mot de passe : motPasseAdmin");
        }

    }

    //méthode pour vérifier si dans la liste il n'y a aucun admin
    private boolean aucunAdminPresent() {
        //on parcourt la liste des utilisateurs
        for (Utilisateur u : utilisateurs) {
            //on créé un strign r qui va se voir assigner le role de chaque utilisateur de la liste successivement
            String r = u.getRole();
            //si r n'est pas null et qu'il correspond à admin, alors on a trouvé un admin, on return false direct ! 
            if (r != null && r.equalsIgnoreCase("ADMIN")) {
                return false;
            }
        }
        //si jamais on a parcouru toute la liste sans avoir de return false, alors c'est qu'on a trouvé aucun admin, on return true !
        return true;
    }

    //sauvegarder dans le fichier, on l'écrase donc on le réécrit en entier
    //la méthode prend ne argument le chemin du fichier
    public void sauvegarderDansFichier(String cheminFichier) {
        try {
            File f = new File(cheminFichier);
            FileWriter fw = new FileWriter(f, false); // false c'est pour écrase le fichier ne entier, true ce serait en ajout à la fin du fichier

            //on parcourt la liste des utilisateurs
            for (Utilisateur u : utilisateurs) {
                // on utilise la méthode de sauvegarde de la classe utilisateur
                //on créé un string ligne et on lui affecte le résultat de la fonction appelée, elle renvoie un login, un mot de passe et un role
                String ligne = u.sauvegardeDansFichier();
                //on écrit dans le fichier le contenu de la ligne et on ajoute un saut de ligne
                fw.write(ligne + "\n");
            }
            //on ferme le fichier
            fw.close();
            //on ajoute aussi un message de confirmation confirmant que ça a marché
            System.out.println("Utilisateurs sauvegardés dans " + cheminFichier);
        } catch (Exception e) {
            //on met un message au cas ou le catch du try catch attrappe qq chose, pour savoir si une erreur est arrivée
            System.out.println("Erreur lors de la sauvegarde des utilisateurs : " + e.getMessage());
        }
    }

   
}

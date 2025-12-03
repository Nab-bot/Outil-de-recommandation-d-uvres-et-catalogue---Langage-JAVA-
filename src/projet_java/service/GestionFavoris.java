/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_java.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import projet_java.model.Catalogue;
import projet_java.model.Manga;
import projet_java.model.Utilisateur;

/**
 *
 * @author nabil
 */
public class GestionFavoris {

    private final String fichierFavoris = "data/favoris.txt";

    public GestionFavoris() {
        try {
            File f = new File(fichierFavoris);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la création du fichier favoris");
        }
    }

    // Ajout d'un favori dans le fichier txt
    public void ajouterFavori(Utilisateur u, Manga m) {
        //try catch pour essayer d'atteindr l fichier
        try {
            FileWriter fw = new FileWriter(fichierFavoris, true);            //écriture dnans le fichier de l'utilisatuer et du manga passé en favori associé
            fw.write(u.getLogin() + ";" + m.getTitre() + "\n");            //on referme lke fihcier
            fw.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de l'ajout favori");
        }
    }

    //méthode pour retire run manga
    public void retirerFavori(Utilisateur u, Manga m) {
        //on créé une liste pour les lignes du fichier
        ArrayList lignes = new ArrayList();
        //on fait un truy catch et on lit le fichier
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichierFavoris));
            //on lit chaque ligne du fichier
            String ligne = br.readLine();
            while (ligne != null) {
                //a chaque ligne on réinitalise au début les variables pour le titre du manga en favori et le login de l'utilisateur
                String login = "";
                String titre = "";
                //on créé un booléen qui va permettre de détecter le séparateur entre le titre et le login
                boolean apresSeparateur = false;

                //on parcourt chaque caractère d'une ligne
                for (int i = 0; i < ligne.length(); i++) {
                    //quand le char c se voit affecté un point virgule, notre séparateur, alors on sait que le booléen doit passer à true
                    char c = ligne.charAt(i);
                    if (c == ';') {
                        apresSeparateur = true;
                    } //sinon on concatène normalement
                    else {
                        if (!apresSeparateur) {
                            login = login + c;
                        } else {
                            titre = titre + c;
                        }
                    }
                }

                // si le favori en cours n'est pas celui qu'on veut retirer, on le garde
                if (!(login.equals(u.getLogin()) && titre.equals(m.getTitre()))) {
                    //comme on réécrit le fichier, si c'est pas celui qu'on veut retirer, on l'écrit
                    lignes.add(ligne);
                }
                //on passe alors à la ligne suivante
                ligne = br.readLine();
            }
            //on libère la ressource
            br.close();

            //on ouvre le fichier en écriture
            FileWriter fw = new FileWriter(fichierFavoris, false);
            for (int i = 0; i < lignes.size(); i++) {

                String l = (String) lignes.get(i);
                fw.write(l + "\n"); // on réécrit chaque ligne suivie d'un saut de ligne
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Erreur lors du retrait favori");
        }
    }

    //méthode pour charger les favoris depuis le fichier
    public void chargerFavorisPour(Utilisateur u, Catalogue cat) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichierFavoris));
            String ligne = br.readLine();
            while (ligne != null) {
                String login = "";
                String titre = "";
                boolean apresSeparateur = false;

                for (int i = 0; i < ligne.length(); i++) {
                    char c = ligne.charAt(i);
                    if (c == ';') {
                        //si on rencontre le ';', alors on passe au titre et on a fini de concaténéer le login
                        apresSeparateur = true;
                    } else {
                        //si le booléen est false, alors on est avant le séparateur donc on continue de concaténer le login
                        if (!apresSeparateur) {
                            login = login + c;
                        } 
                        //sinon, on a passé le séparateur, c'est le titre qu'on concatène
                        else {
                            titre = titre + c;
                        }
                    }
                }

                //si le login correspond au login de l'utilisateur u
                if (login.equals(u.getLogin())) {
                    //on cherche si le manga ayant un titre donné existe déjà dans la favoris de l'utilisateur
                    if (u.chercherFavoriParTitre(titre) == null) {
                        //si le manga n'est pas dans les favoris de l'utilisateur, on va le chercher dans le catalogue
                        Manga m = cat.rechercherParTitre(titre);
                        //si la manga est bien trouvé dans le catalogue
                        if (m != null) {
                            //on l'ajoute aux favoris de l'utilisateur
                            u.ajouterFavori(m);
                        }
                    }
                }

                ligne = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement favoris");
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projet_java.app;

/**
 *
 * @author nabil
 */
import javax.swing.JFrame;
import projet_java.UI.FenetreConnexion;
import projet_java.model.Catalogue;
import projet_java.service.GestionUtilisateurs;
//import projet_java.ui.FenetreConnexion; c'est le mauvais nom, c'était UI le bon, pas ui en minuscule


public class Projet_JAVA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /* V1 du main
        JFrame fen = new JFrame("Fenêtre de recommandation de mangas");
        //on fait les régalges pour place rl afenetre au bon endroit
        fen.setSize(600, 400);
        fen.setLocation(200, 200);

        //on créé un nouveau bouton
        JButton bouton = new JButton("Lancer la recommandation");
        //on ajoute ce bouton à la fenetre
        fen.add(bouton);

        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // pour fermer correctement
        fen.setVisible(true); // on affiche la fenêtre
         */
        //V2 du main 
//d'abord on appelle les services pour avoir le catalogue des mangas et les utilisateurs
        
//on créé un objet de la classe gestion utilisateur         
        GestionUtilisateurs gestionUsers = new GestionUtilisateurs();
//et on rempli les champs de cet objet gràace à sa fonction dédiée        
        gestionUsers.chargerUtilisateurDepuisFichier("data/utilisateurs.txt");

//m^me raisonnement pour le catalogue des mangas, on construit in objet catalogue et on 
//rempli ses champs avec la fonctioon ddie qui va chercher dans le fichier qui content les ifnos sur tous les mangas, donc le catalàogue         
        Catalogue catalogue = new Catalogue();
        catalogue.chargerDepuisFichier("data/catalogue.txt");

        // Ensuite on ouvrir la fenêtre de connexion en lui passant les services en paramètres
        //on créé un objet fenetre
        FenetreConnexion fen = new FenetreConnexion(gestionUsers, catalogue);
        //on fait ensuite les réglages de la fenetre
        fen.setTitle("Connexion"); //c'est la  fentre de connexion donc le titre doit l'indiquer
        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//permet de fermer l'appli quand on ferme cette fenetre
        fen.setSize(500, 300);
       
        fen.setVisible(true);

    }

}

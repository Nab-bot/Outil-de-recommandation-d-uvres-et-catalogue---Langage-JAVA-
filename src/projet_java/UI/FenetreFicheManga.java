/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projet_java.UI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import projet_java.model.Catalogue;
import projet_java.model.Manga;
import projet_java.model.Utilisateur;
import projet_java.service.GestionFavoris;
import projet_java.service.GestionUtilisateurs;

/**
 *
 * @author nabil
 */
public class FenetreFicheManga extends javax.swing.JFrame {

    //final permet d'^tre sur que quand on arrive dans cet ecran, le catalogue ou l'utilisateur ne changeront pas
    //le private permet que les infos de l'utilisateur connecté ne changent pas, donc l'utilisateur connecté ne changera pas avant déconnexion par exempke
    private final GestionUtilisateurs gestionUsers; //c'est lo'bjet qui gère les utilisateurs
    private final Catalogue catalogue; //le catalogue des mangas
    private final Utilisateur utilisateur; // c'est l'utilisateur qui est connecté
    private final Manga manga;
    private final JFrame ecranRetour;

    /**
     * Creates new form FenetreFicheManga
     */
    public FenetreFicheManga(GestionUtilisateurs gu, Catalogue cat, Utilisateur u, Manga m, JFrame retour) {
        initComponents();

        this.gestionUsers = gu;
        this.catalogue = cat;
        this.utilisateur = u;
        this.manga = m;

        //on mémorise la fenetre juste avant, celle qui a ouvert la fiche manga, pour pouvoir revenir ne arrière
        this.ecranRetour = retour;

        //on fait aussi les régalges de la  fenetre
        setTitle("Fiche manga");
        setSize(600, 420);
        setLocation(240, 160);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //on rempli le texte pour le titre, l'auteur et la description et on  gère le cas si y a pas les infos
        //si un mlanga est trouvé, on gère le texte 
        if (manga != null) {
            //si on trouve le titre du manga, on l'affiche, sinon on met un texte par defaut        
            if (manga.getTitre() != null) {
                titreFicheManga.setText(manga.getTitre());
            } else {
                titreFicheManga.setText("(Pas de titre renseigné)");
            }
            if (manga.getAuteur() != null) {
                auteurFicheManga.setText("Auteur : " + manga.getAuteur());
            } else {
                auteurFicheManga.setText("Auteur : non renseigné");
            }
            if (manga.getDescription() != null) {
                descriptionFicheManga.setText(manga.getDescription());
            } else {
                descriptionFicheManga.setText("(Pas de description)");
            }
        } //sinon on affiche ça par défaut si le manga n'est pas trouvé
        else {
            titreFicheManga.setText("(Manga non existant)");
            auteurFicheManga.setText("");
            descriptionFicheManga.setText("");
        }

        //gestion de l'image de la fiche manga
        //on créé deux variables, l'une indique le chemin vers l'image, l'autre est l'objet icone
        //on initialise les deux à null, on part du principe qu'il n'y a pas d'image de base, enfin pour l'icone ce sera le placeholder pa rdéfaut en réalité, finalement je pense c'est pas uen bonne idée, netbeans me dit que ça sert à rien
//Inutile au final : ImageIcon icone = new ImageIcon("images/placeholder.jpg"); // placeholder par défaut ;
        String chemin = null;
        ImageIcon icone;
        //si un manga est trovué, 
        if (manga != null) {
            //la variable chemin se voit affectée le contenu de l'attribut manga
            chemin = manga.getImage();
        }

        //si le chemin est trouvé et que la chaine de caractère fait au moins un caractère
        //on tente d'accéder à l'image, elle est dans un dossier, on met un try catch auy cas ou on n'arriverai pas à la trouver, on mettra un placeholder, enfin si son chemin est un string vide, donc moins de 1 caractère
        if (chemin != null && chemin.length() > 0) {

            icone = new ImageIcon("images/" + chemin);

        } else {
            icone = new ImageIcon("images/placeholder.jpg");

            /*
                try {
                    //si tout se passe bien, on créé une icone qui correspnd àl'image dont on a le chemin indiqué
                    icone = new ImageIcon(chemin);
                } catch (Exception e) {
                    //si on a pas réussi à y accéder, l'icone est null
                    icone = null;
                }
             */
        }
//on affecte ce composant avec le placeholder qu'on vient de créé juste au-dessus
        imageFicheManga.setIcon(icone);
        //on efface le texte si jamais il y en avait (je crois que j'avais initialisé le bloc avec un texte qui dit "texte", dans tous les cas ça règle le problème
        imageFicheManga.setText("");


        /*      
        //je supprime le bloc suivant car j'ai deja initialisé icone avec le placeholder donc jamais null, ce bloc n'est pas utile
        //si l'icone est null, donc on n'a pas trouvé l'image
        if (icone == null) {
            //on créé une image icone et on lui affecte le chemin vers l'image désirée, ici le pkacholder car on gère le cas ou une image n'est pas trouvée !

            icone = new ImageIcon("images/placeholder.jpg");

          //on affecte ce composant avec le placeholder qu'on vient de créé juste au-dessus
            imageFicheManga.setIcon(icone);
            //on efface le texte si jamais il y en avait (je crois que j'avais initialisé le bloc avec un texte qui dit "texte", dans tous les cas ça règle le problème
            imageFicheManga.setText(null);
        }
         */
 /*
            try {
                icone = new ImageIcon("images/placeholder.jpg");
            } catch (Exception e) {
                //sinon on repasse l'icone à null
                icone = null;
            }
         */

 /*
        //dernier cas, si on n'arrive meme pas à accéder au placeholder, on affichera un message par défaut
        if (icone != null) {
            imageFicheManga.setIcon(icone);
            imageFicheManga.setText(null);
        } else {
            //on attribut ce messagez par défaut
            imageFicheManga.setText("(image non disponible)");
        }
         */
        //gestion de l'affichage du bouton ajouter aux favoris, on le cache si le manga est deja ne favoris
        //on créé le booléen a false, on part du principe que le manga n'est pas deja dans les favoris
        boolean deja = false;

        //si un manga est trouvé
        if (manga != null) {
            //si on trouve le titre
            if (manga.getTitre() != null) {
                //si on trouve le manga deja dans les favoris
                if (utilisateur.chercherFavoriParTitre(manga.getTitre()) != null) {
                    //on passe le booléen a true pour dire que le manga est deja trouvé dans les favoris
                    deja = true;
                }
            }
        }

        //si le booléen passe à true, donc le manga est trouvé dans les favoris
        if (deja) {
            //on cache alors le bouton car il devient inutile
            btnAjoutFavorisFicheManga.setVisible(false);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        titreFicheManga = new javax.swing.JLabel();
        auteurFicheManga = new javax.swing.JLabel();
        imageFicheManga = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionFicheManga = new javax.swing.JTextArea();
        btnAjoutFavorisFicheManga = new javax.swing.JButton();
        btnRetourFicheManga = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(1200, 900));

        titreFicheManga.setText("Titre");

        auteurFicheManga.setText("Auteur");

        imageFicheManga.setText("Image");

        descriptionFicheManga.setColumns(20);
        descriptionFicheManga.setRows(5);
        descriptionFicheManga.setText("Description");
        jScrollPane1.setViewportView(descriptionFicheManga);

        btnAjoutFavorisFicheManga.setText("Ajout aux favoris");
        btnAjoutFavorisFicheManga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutFavorisFicheMangaActionPerformed(evt);
            }
        });

        btnRetourFicheManga.setText("Retour");
        btnRetourFicheManga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetourFicheMangaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAjoutFavorisFicheManga)
                    .addComponent(btnRetourFicheManga))
                .addGap(47, 47, 47))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(auteurFicheManga)
                    .addComponent(titreFicheManga))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(imageFicheManga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(titreFicheManga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(auteurFicheManga)
                .addGap(31, 31, 31)
                .addComponent(imageFicheManga, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAjoutFavorisFicheManga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRetourFicheManga)))
                .addGap(22, 22, 22))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAjoutFavorisFicheMangaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutFavorisFicheMangaActionPerformed
        // TODO add your handling code here:

        //on créé un booléen permettant de savoir si le manga à ajouter est déjà présent dans les favoris, 
        //on met le booléen à false de base pour partir du principe que je vais ajouter le manga au favoris
        boolean presentFavoris = false;

        //si un manga est trouvé et qu'n trouve son titre
        if (manga != null) {
            if (manga.getTitre() != null) {
                //alors on cherche le manga par son titre dans les favoris de l'utilisateur
                if (utilisateur.chercherFavoriParTitre(manga.getTitre()) != null) {
                    //si le manga est trouvé dans les favoris, on passe le booléen a true
                    presentFavoris = true;
                }
            }
        }

        //si le booléen est bien passé à true, il est trouvé dans les favoris de l'utilisateur
        if (presentFavoris) {
            //on affiche alors un emssage qui le prévient et on cache le bouton d'ajout
            JOptionPane.showMessageDialog(this, "Déjà dans tes favoris.");
            btnAjoutFavorisFicheManga.setVisible(false);
            //enfin, on arrete la méthode
            return;
        }

        //et donc l'autre cas si le if ne capte pas le cas ou le manga n'est pas deeja dans les favoris
        //on appelle la méthode d'ajotu aux favoris d'un manga donné pour un utilisateur conencté
        utilisateur.ajouterFavori(manga);

//on ajoute le favori dans le fichier des favoris
        GestionFavoris gf = new GestionFavoris();
        gf.ajouterFavori(utilisateur, manga);

        //l'ajout est fait, on afficxhe un message le disant
        JOptionPane.showMessageDialog(this, "Ajouté aux favoris : " + manga.getTitre());
        //on cache le bouton d'ajout aux favoris
        btnAjoutFavorisFicheManga.setVisible(false);

    }//GEN-LAST:event_btnAjoutFavorisFicheMangaActionPerformed

    private void btnRetourFicheMangaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetourFicheMangaActionPerformed
        // TODO add your handling code here:

        //on revient à la fenêtre qui a ouvert la fiche
        if (ecranRetour != null) {
            this.setVisible(false);//on cache la fenetre actuelle
            ecranRetour.setVisible(true);  //on affiche la fenetre d'avant
        } else {
            // normalement on entrera pas dans ce if mais si jamais j'ai raté un bout de code, en failsafe ça revient à l'accueil
            FenetreAccueil fa = new FenetreAccueil(gestionUsers, catalogue, utilisateur);
            this.setVisible(false);
            fa.setVisible(true);
        }
    }//GEN-LAST:event_btnRetourFicheMangaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel auteurFicheManga;
    private javax.swing.JButton btnAjoutFavorisFicheManga;
    private javax.swing.JButton btnRetourFicheManga;
    private javax.swing.JTextArea descriptionFicheManga;
    private javax.swing.JLabel imageFicheManga;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel titreFicheManga;
    // End of variables declaration//GEN-END:variables

}

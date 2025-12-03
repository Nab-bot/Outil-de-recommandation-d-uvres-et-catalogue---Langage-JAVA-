/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_java.model;

/**
 *
 * @author nabil
 */
public class RecoCritere {

    private String genre;       // action, aventure, surnaturel
    private String type;        // shonen, seinen, shojo, etc.
    private String ambiance;    // sombre, humoristique...
    private int longueurMin;    // nb de chapitres min
    private int longueurMax;    // nb de chapitres max
    private String univers;     // réaliste, fantastique, SF, etc...
    private int anneeMin;       // année minimum (ex: 2015)
    private int anneeMax;       // année maximum

    // Constructeur complet car le questionnaire remplit tout
    public RecoCritere(String genre, String type, String ambiance, int longueurMin, int longueurMax, String univers, int anneeMin, int anneeMax) {
        this.genre = genre;
        this.type = type;
        this.ambiance = ambiance;
        this.longueurMin = longueurMin;
        this.longueurMax = longueurMax;
        this.univers = univers;
        this.anneeMin = anneeMin;
        this.anneeMax = anneeMax;
    }

    //Getter et setters
    //on a besoin des getters pour lire les infos dans l'objet
    //les setters vont permettre de remplir les champs de lk'objet recommandation 
    //qu'on va créer, ses champs seront à null ou 0 de base, on le construit en répondant aux questions
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmbiance() {
        return ambiance;
    }

    public void setAmbiance(String ambiance) {
        this.ambiance = ambiance;
    }

    public int getLongueurMin() {
        return longueurMin;
    }

    public void setLongueurMin(int longueurMin) {
        this.longueurMin = longueurMin;
    }

    public int getLongueurMax() {
        return longueurMax;
    }

    public void setLongueurMax(int longueurMax) {
        this.longueurMax = longueurMax;
    }

    public String getUnivers() {
        return univers;
    }

    public void setUnivers(String univers) {
        this.univers = univers;
    }

    public int getAnneeMin() {
        return anneeMin;
    }

    public void setAnneeMin(int anneeMin) {
        this.anneeMin = anneeMin;
    }

    public int getAnneeMax() {
        return anneeMax;
    }

    public void setAnneeMax(int anneeMax) {
        this.anneeMax = anneeMax;
    }
}

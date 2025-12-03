/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_java.service;

import java.util.ArrayList;
import projet_java.model.Catalogue;
import projet_java.model.Manga;
import projet_java.model.RecoCritere;

/**
 *
 * @author nabil
 */
public class RecoSystem {

    //référence vers le catalogue complet de mangas (base de travail pour la reco)
    //on déclare une variable de type catalogue
    private Catalogue catalogueMangas;

    // constructeur : on réutilise le catalogue déjà chargé par l'appli
    public RecoSystem(Catalogue catalogueMangas) {
        //on assigne le le paramètre passé, donc le catalogue, à l'attribut interne de l'objet recosystem
        this.catalogueMangas = catalogueMangas;
    }

    //getter pour le catalogue
    public Catalogue getCatalogueMangas() {
        return catalogueMangas;
    }

    //setter pour le catalogue
    public void setCatalogueMangas(Catalogue catalogueMangas) {
        this.catalogueMangas = catalogueMangas;
    }

    //on créé une liste interne 
    //la méthode renverra une liste de mangas
    //il prend en paramètre un objet recocritere, celui-ci regroupe les réponses de l'utilisateur
    public ArrayList<Manga> recommanderManga(RecoCritere c) {

        //on vérifie que l'oibjet catalogue a bien été créé
        //on vérifie aussi que les mangas on tbien été chargés dans la liste
        if (catalogueMangas == null || catalogueMangas.getBase() == null) {
            //si on rentre dans le if, on créé une nouvelle  liste vide évitant le crash mais ne donnera aucune reco
            return new ArrayList<Manga>();
        }

//on créé la liste de résultats de la reco, elle est vide pour l'instant mais on va la remplir
        ArrayList<Manga> resultatsReco = new ArrayList<Manga>();

        // on parcourt tout le catalogue
        for (Manga m : catalogueMangas.getBase()) {
            //on créé un booléen indiquant si l'ajout peut-être fait, par defaut a true
            boolean ok = true;

            // genre 
            //on créé un string g qui va recevoir le résultat du getter du genre du manga
            String g = m.getGenre();
            //si g est null, donc le genre du manga en cours de la liste qu'on parcourt est null, ce on rentre dans le if (ça arrive si le genre du manga n'est pas renseigné)
            //si c.getgenre, donc le genre choisi par l'utilisateur quand il a repondu au questionnaire est null, on rentre dans le if aussi (ça arrive si l'utilisateur n'a pas repondu à cette question, ça ne devrait pas arriver ne principe mais je laisse au cas où)
            //si le genre du manga est différent du genre choisi par l'utilisateur quand il a répondu au questionnaire, on rentre aussi dans le if

            /* V1 du if, c'était mauvais car si l'utilisateur ne choisisait rien, aucun résultat ne sortait, or, comme tous les critères sont facultatifs, le résultat ne doit pas etre une liste videmais une liste de tout le catalogue si aucune saisie de critère n'est faite ! 
            if (g == null || c.getGenre() == null || !g.equalsIgnoreCase(c.getGenre())) {
                //si on répond à une des 3 conditions ci-dessus, alors on passe la booléen a false, ce n'est plus ok de faire l'ajout
                ok = false;
            }
             */
            //V2 du if, ici on ne filtre que si l’utilisateur a choisi un genre, 
            //s'il n'a pas choisi, le filtre par défaut est gardé, donc le booléen ne passe pas à false
            if (c.getGenre() != null) {
                if (g == null || !g.equalsIgnoreCase(c.getGenre())) {
                    ok = false;
                }
            }

            // type 
            //on teste le booléen, s'il est true, on créé la variable puis on la 
            //test, sinon, inutile de passer par là car on aurait déjà false et donc on passera au manga suivant de la liste
            /*V1 du test pour le type 
            if (ok) {
                String t = m.getType();
                if (t == null || c.getType() == null || !t.equalsIgnoreCase(c.getType())) {
                    ok = false;
                }
            }
             */
            //V2 test type
            //on mettra le if (ok) dans les suivants car si un test échoue, le booléen passe à faux et le manga ne sera pas dans la liste des recos en resultat
            if (ok) {
                String t = m.getType();
                if (c.getType() != null) {
                    if (t == null || !t.equalsIgnoreCase(c.getType())) {
                        ok = false;
                    }
                }
            }

            // ambiance 
            /*if (ok) {
                String a = m.getAmbiance();
                if (a == null || c.getAmbiance() == null || !a.equalsIgnoreCase(c.getAmbiance())) {
                    ok = false;
                }
            }*/
            //meme raisonnement ici avec le if(ok)
            if (ok) {
                String a = m.getAmbiance();
                if (c.getAmbiance() != null) {
                    if (a == null || !a.equalsIgnoreCase(c.getAmbiance())) {
                        ok = false;
                    }
                }
            }

            //  univers 
            /*if (ok) {
                String u = m.getUnivers();
                if (u == null || c.getUnivers() == null || !u.equalsIgnoreCase(c.getUnivers())) {
                    ok = false;
                }
            }*/
            if (ok) {
                String u = m.getUnivers();          //on récupère le champ univers du manga courant
                if (c.getUnivers() != null) {       //ça vient des critères saisis
                    if (u == null || !u.equalsIgnoreCase(c.getUnivers())) {
                        ok = false;
                    }
                }
            }

            //  longueur chapitres
            /*if (ok) {
                //on créé un int donnat le nombre de chapitres du manga en cours de la liste
                int nbChapitres = m.getLongueurChapitres();

                //si le nombre de chapitres est inférieur au nombre minimum de chapitres choisi par l'utilisateur
                //par exemple si le nombre de chapitres du manga est 1157, c'est le cas de one piece, 
                //et que l'utilisateur a choisi d'avoir un manga de moins de 500 
                //chapitres par exmeple, alors on skip le manga ne cours car il colle pas à ses critères de recherche
                if (nbChapitres < c.getLongueurMin()) {
                    ok = false;
                }
                //si le nombre de chapitres est supérieur au nombre maximum de chapitres choisi par l'utilisateur
                if (nbChapitres > c.getLongueurMax()) {
                    ok = false;
                }
            }
             */
            if (ok) {
                int nb = m.getLongueurChapitres();  //ça vient du manga courant

                //si l’utilisateur n’a rien choisi, on garde les bornes larges, on n'excclut donc pas
                if (nb < c.getLongueurMin() || nb > c.getLongueurMax()) {
                    ok = false;
                }
            }

            //  année de début de publication du manga
            /*
            if (ok) {
                //idem que plus haut, on créé une variable qui va stocker l'année de sortie du manga ne cours de la liste
                int annee = m.getAnneeSortie();

                //si l'année récupérée est inférieur à l'année minimum, on met à false
                if (annee < c.getAnneeMin()) {
                    ok = false;
                }
                //si l'année récupérée est supérieur à l'année maximum, on met à false
                //par exemple, si l'année de sortie du manga est 2015 et que l'utilisateur a choisi 
                //de voir des mangas datant d'avant, 2009 oar exemple, alors on passe le booléen a false et 
                //on ne met pas ce manga dans la liste des resultats, on passe au manga suivant
                if (annee > c.getAnneeMax()) {
                    ok = false;
                }
            }
             */
            //V2 du if pour l'année de sortie
            if (ok) {
                int an = m.getAnneeSortie();        //ça vient du manga courant
                //si l'utilisateur ne saisi rien, on a des valeurs par défaut, on n'excclut donc rien
                //par contre, si jamais l'utilisateur a fait un choix, alors selon nos critères des années, le manga sera exclut ou non
                if (an < c.getAnneeMin() || an > c.getAnneeMax()) {
                    ok = false;
                }
            }

            //si tous les if ci-dessus n'ont pas entrainé de passage du booléen à false, alors on fait l'ajout
            //ça revient à dire qu'il faut que l'utilisateur ai répondu à chaque question et que ses réponses collent aux infos d'un manga
            //si on a bien ces deux choses, alors l'ajout à la liste des résultats de reco qui vont s'afficher pour l'utilisateur peut 
            //donc si on rentre dans aucun des if au-dessus, on fait l'ajout !
            if (ok) {
                resultatsReco.add(m);
            }
        }

        //on return la liste des recos issues du questionnaire
        return resultatsReco;
    }
}

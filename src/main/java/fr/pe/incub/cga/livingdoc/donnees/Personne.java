package fr.pe.incub.cga.livingdoc.donnees;

import fr.pe.incub.cga.livingdoc.doc.Glossary;

/**
 * Une personne avec un nom et un prénom... Formidable!!!
 */
@Glossary
public class Personne {

    private final int id;
    private final String nom;
    private final String prenom;

    public Personne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }
}

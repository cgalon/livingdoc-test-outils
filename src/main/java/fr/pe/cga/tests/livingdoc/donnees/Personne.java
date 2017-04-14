package fr.pe.cga.tests.livingdoc.donnees;

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

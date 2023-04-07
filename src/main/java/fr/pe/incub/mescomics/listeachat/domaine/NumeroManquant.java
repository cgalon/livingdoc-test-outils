package fr.pe.incub.mescomics.listeachat.domaine;

import fr.pe.incub.livingdoc.glossaire.Glossary;

import java.time.LocalDate;

@Glossary
/**
 * Un numéro d'une revue à acheter.
 */
public class NumeroManquant {

    public final String serie;
    public final int numero;
    public final LocalDate dateDeParution;
    public final String editeur;

    public NumeroManquant(String serie, int numero, LocalDate dateDeParution, String editeur) {
        this.serie = serie;
        this.numero = numero;
        this.dateDeParution = dateDeParution;
        this.editeur = editeur;
    }

    @Override
    public String toString() {
        return "NumeroManquant{" +
                "serie='" + serie + '\'' +
                ", numero=" + numero +
                '}';
    }
}

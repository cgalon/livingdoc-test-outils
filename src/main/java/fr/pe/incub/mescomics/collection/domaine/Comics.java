package fr.pe.incub.mescomics.collection.domaine;

import fr.pe.incub.livingdoc.glossaire.Glossary;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Glossary
/**
 * Le numéro d'une revue détenu par l'utilisateur.
 */
@Entity
@Schema(description = "Un numéro de comics possédé par l'utilisateur.")
public class Comics {

    @Id
    @Schema(description = "Titre de la revue")
    public final String titreDeLaRevue;
    @Schema(description = "Numéro de parution")
    public final int numero;
    @Schema(description = "Prix du numéro d'occasion")
    public int valeurEnEuros;

    public Comics(String nomDeLaRevue, int numero) {
        this.titreDeLaRevue = nomDeLaRevue;
        this.numero = numero;
        this.valeurEnEuros = 0;
    }

    public Comics(String nomDeLaRevue, int numero, int valeurEnEuros) {
        this.titreDeLaRevue = nomDeLaRevue;
        this.numero = numero;
        this.valeurEnEuros = valeurEnEuros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comics comics = (Comics) o;
        return numero == comics.numero &&
                Objects.equals(titreDeLaRevue, comics.titreDeLaRevue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(titreDeLaRevue, numero);
    }

    public void modifieLaValeurEnEuros(int nouvelleValeur) {
        valeurEnEuros = nouvelleValeur;
    }
}

package fr.pe.incub.mescomics.referentiel.domaine;

import fr.pe.incub.livingdoc.glossaire.Glossary;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Glossary
/**
 * Morceau d'histoire d'un héros paru dans un numéro d'une revue.
 */
@Schema(description = "Les informations d'une histoire d'un héros dans un numéro d'une revue.")
public class Episode{

    @Schema(description = "Nom du héros de l'histoire.")
    private final String heros;
    @Schema(description = "Titre de l'épisode.")
    private final String titre;
    @Schema(description = "Dessinateur de l'épisode.")
    private final String dessinateur;

    public Episode(String heros, String nomDeLEpisode, String dessinateur) {
        this.heros = heros;
        this.titre = nomDeLEpisode;
        this.dessinateur = dessinateur;
    }

    public String recupereLeHeros() {
        return heros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episode episode = (Episode) o;
        return Objects.equals(heros, episode.heros) &&
                Objects.equals(titre, episode.titre) &&
                Objects.equals(dessinateur, episode.dessinateur);
    }

    @Override
    public int hashCode() {

        return Objects.hash(heros, titre, dessinateur);
    }
}

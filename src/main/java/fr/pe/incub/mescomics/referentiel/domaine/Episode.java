package fr.pe.incub.mescomics.referentiel.domaine;

import fr.pe.incub.livingdoc.glossaire.Glossary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@Glossary
/**
 * Morceau d'histoire d'un héros paru dans un numéro d'une revue.
 */
@ApiModel(description = "Les informations d'une histoire d'un héros dans un numéro d'une revue.")
public class Episode{

    @ApiModelProperty(notes = "Nom du héros de l'histoire.")
    private final String heros;
    @ApiModelProperty(notes = "Titre de l'épisode.")
    private final String titre;
    @ApiModelProperty(notes = "Dessinateur de l'épisode.")
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

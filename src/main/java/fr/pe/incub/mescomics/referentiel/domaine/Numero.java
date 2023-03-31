package fr.pe.incub.mescomics.referentiel.domaine;

import fr.pe.incub.livingdoc.glossaire.Glossary;
import fr.pe.incub.mescomics.referentiel.domaine.exception.EpisodeNonTrouveException;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Glossary
/**
 * Parution d'une instance de revue à une date précise. Un numéro peut contenir un ou plusieurs épisodes de un ou plusieurs héros.
 */
@Schema(description = "Les informations particulière à un numéro d'une revue.")
public class Numero {

    @Schema(description = "Titre de la revue")
    public final String nomDeLaRevue;
    @Schema(description = "Numéro de la revue")
    public final int numeroDansLaSerie;
    @Schema(description = "Date de parution du numéro")
    public final LocalDate dateDeParution;
    @Schema(description = "Liste des histoires présentes dans le numéro")
    private List<Episode> listeDesEpisodes = new ArrayList<>();

    public Numero(String nomDeLaRevue, int numero, LocalDate dateDeParution) {
        this.numeroDansLaSerie = numero;
        this.dateDeParution = dateDeParution;
        this.nomDeLaRevue = nomDeLaRevue;
    }

    public String recupereLaDateDeParutionEnFrancais() {
        return dateDeParution.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void ajouteUnEpisode(Episode unEpisode) {
        listeDesEpisodes.add(unEpisode);
    }

    public int recupereLeNombreDEpisodes() {
        return listeDesEpisodes.size();
    }

    public Episode recupereUnEpisodeParLeNomDuHeros(String nomDuHeros) throws EpisodeNonTrouveException {
        Episode episodeTrouve = listeDesEpisodes.stream().filter(episode -> nomDuHeros.equals(episode.recupereLeHeros())).findFirst().orElseThrow(() -> new EpisodeNonTrouveException(nomDuHeros));
        return episodeTrouve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Numero numero = (Numero) o;
        return numeroDansLaSerie == numero.numeroDansLaSerie &&
                Objects.equals(nomDeLaRevue, numero.nomDeLaRevue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nomDeLaRevue, numeroDansLaSerie);
    }
}

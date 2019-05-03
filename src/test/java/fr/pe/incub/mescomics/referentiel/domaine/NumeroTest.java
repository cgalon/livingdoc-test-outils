package fr.pe.incub.mescomics.referentiel.domaine;

import fr.pe.incub.mescomics.referentiel.domaine.exception.EpisodeNonTrouveException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumeroTest {

    @Test
    public void doitCreerUnNouveauNumeroSansContenu() {
        Numero unNumero = creeUnNumeroVide();

        assertEquals("01/01/1970", unNumero.recupereLaDateDeParutionEnFrancais());
        assertEquals(1, unNumero.numeroDansLaSerie);
        assertEquals(0, unNumero.recupereLeNombreDEpisodes());
    }

    @Test
    public void doitAjouterUnEpisodeAuNumero() {
        Numero unNumero = creeUnNumeroVide();
        Episode unEpisode = new Episode("Iron Man", "Iron Man est né", "Don Heck");

        unNumero.ajouteUnEpisode(unEpisode);

        assertEquals(1, unNumero.recupereLeNombreDEpisodes());
    }

    @Test
    public void doitRecupererUnEpisodeEnFonctionDuNomDuHeros() throws EpisodeNonTrouveException {
        Numero unNumero = creeUnNumeroVide();
        Episode unEpisode = new Episode("Iron Man", "Iron Man est né", "Don Heck");
        unNumero.ajouteUnEpisode(unEpisode);
        Episode unAutreEpisode = new Episode("Daredevil", "Les origines de Daredevil", "Jack Kirby");
        unNumero.ajouteUnEpisode(unEpisode);
        unNumero.ajouteUnEpisode(unAutreEpisode);

        Episode episodeIronMan = unNumero.recupereUnEpisodeParLeNomDuHeros("Iron Man");

        assertEquals(unEpisode, episodeIronMan);
    }

    @Test
    public void doitRemonterUneExceptionSiLaRechercheDUnEpisodeParHerosNeFonctionnePas() throws EpisodeNonTrouveException {
        Numero unNumero = creeUnNumeroVide();
        Episode unEpisode = new Episode("Iron Man", "Iron Man est né", "Don Heck");
        unNumero.ajouteUnEpisode(unEpisode);
        Episode unAutreEpisode = new Episode("Daredevil", "Les origines de Daredevil", "Jack Kirby");
        unNumero.ajouteUnEpisode(unEpisode);
        unNumero.ajouteUnEpisode(unAutreEpisode);

        assertThrows(EpisodeNonTrouveException.class, () -> {
            Episode episodeIronMan = unNumero.recupereUnEpisodeParLeNomDuHeros("Spiderman");
        });
    }

    private Numero creeUnNumeroVide() {
        LocalDate dateDeSortie = LocalDate.of(1970, 1, 1);
        return new Numero(null, 1, dateDeSortie);
    }

}
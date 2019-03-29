package fr.pe.incub.mescomics.collection.domaine;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CollectionTest {

    UtilitairesDeTest utilitairesDeTest = new UtilitairesDeTest();

    @Test
    public void doitAjouterUnComicsALaCollection() {
        Collection maCollection = utilitairesDeTest.creeUneCollectionAvecUnComics();

        assertEquals(1, maCollection.recupereLeNombreDeComics());
    }

    @Test
    public void doitSupprimerUnComicsDeLaCollection() {
        Collection maCollection = utilitairesDeTest.creeUneCollectionAvecTroisComics();

        maCollection.supprimeUnComics("Strange", 30);

        assertEquals(2, maCollection.recupereLeNombreDeComics());
    }

    @Test
    public void doitModifierLaDateDeDernierAjoutDUnComics() {
        Collection maCollection = utilitairesDeTest.creeUneCollectionAvecUnComics();

        assertEquals(LocalDate.now(), maCollection.recupereLaDateDeDernierAjout());
    }

    @Test
    public void doitRecupererUneListeDeComicsEnFonctionDuTitre() {
        Collection maCollection = utilitairesDeTest.creeUneCollectionAvecTroisComics();

        List<Comics> maCollectionDeStrange = maCollection.recupereLaListeDesComicsDeLaSerie("Strange");

        assertEquals(2, maCollectionDeStrange.size());
        Comics strangeNumero15 = new Comics("Strange", 15);
        assertTrue(maCollectionDeStrange.contains(strangeNumero15));
    }

}
package fr.pe.incub.mescomics.listeachat.infrastructure;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.collection.api.MaCollectionDeComics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecuperateurDeComicsDeMaCollectionTest {

    @Mock
    MaCollectionDeComics maCollectionDeComicsBouchon;

    UtilitairesDeTest utilitairesDeTest = new UtilitairesDeTest();

    @Test
    public void doitConstruireLaListeDesNumerosAchetesPourUneSerie() {
        when(maCollectionDeComicsBouchon.retourneMaListeDeComicsPourCeTitre("Special Strange")).thenReturn(utilitairesDeTest.creeUneListeDeComicsSpecialStrange());
        RecuperateurDeComicsDeMaCollection recuperateurDeComicsDeMaCollection = new RecuperateurDeComicsDeMaCollection(maCollectionDeComicsBouchon);

        List<NumeroPresent> mesNumerosAchetes = recuperateurDeComicsDeMaCollection.retrouveLesComicsQueJePossedePourCeTitre("Special Strange");

        assertEquals(5, mesNumerosAchetes.size());
    }

}

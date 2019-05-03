package fr.pe.incub.mescomics.listeachat.infrastructure;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.collection.api.MaCollectionDeComics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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

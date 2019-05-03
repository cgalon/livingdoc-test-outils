package fr.pe.incub.mescomics.collection.api;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.collection.domaine.Comics;
import fr.pe.incub.mescomics.collection.infrastructure.ServiceDeCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MaCollectionDeComicsTest {

    @Mock
    ServiceDeCollection serviceDeCollection;

    UtilitairesDeTest utilitairesDeTest = new UtilitairesDeTest();

    @Test
    public void doitRetournerUneListeDeComicsFiltreeSurUnTitre() {
        when(serviceDeCollection.recupererMaCollectionCompleteDeComics()).thenReturn(utilitairesDeTest.creeUneCollectionDeComics());
        MaCollectionDeComics maCollectionDeComics = new MaCollectionDeComics(serviceDeCollection);

        List<Comics> maListeDeStranges = maCollectionDeComics.retourneMaListeDeComicsPourCeTitre("Strange");

        assertEquals(5, maListeDeStranges.size());
    }
}
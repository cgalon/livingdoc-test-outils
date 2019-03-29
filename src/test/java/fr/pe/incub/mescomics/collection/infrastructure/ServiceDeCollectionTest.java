package fr.pe.incub.mescomics.collection.infrastructure;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.collection.domaine.Collection;
import fr.pe.incub.mescomics.collection.domaine.Comics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceDeCollectionTest {

    @Mock
    private EntrepotDeComics entrepotDeComicsBouchon;

    UtilitairesDeTest utilitairesDeTest = new UtilitairesDeTest();

    @Test
    public void doitCreerUneCollectionDeComicsAPartirDUnIterable() {
        Iterable<Comics> maListeIterable = utilitairesDeTest.creeUneListeDeComics();
        when(entrepotDeComicsBouchon.findAll()).thenReturn(maListeIterable);
        ServiceDeCollection serviceDeCollection = new ServiceDeCollection(entrepotDeComicsBouchon);

        Collection maCollectionComplete = serviceDeCollection.recupererMaCollectionCompleteDeComics();

        assertEquals(7, maCollectionComplete.recupereLeNombreDeComics());
    }

}
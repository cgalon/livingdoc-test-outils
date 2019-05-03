package fr.pe.incub.mescomics.collection.infrastructure;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.collection.domaine.Collection;
import fr.pe.incub.mescomics.collection.domaine.Comics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
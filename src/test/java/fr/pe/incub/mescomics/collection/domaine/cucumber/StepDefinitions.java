package fr.pe.incub.mescomics.collection.domaine.cucumber;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.collection.domaine.Collection;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Lorsque;
import io.cucumber.java.fr.Alors;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    UtilitairesDeTest utilitairesDeTest = new UtilitairesDeTest();
    Collection maCollection;

    @Etantdonné("une collection de {int} comics")
    public void une_collection_de_comics(Integer nombreDeComics) {
        maCollection = utilitairesDeTest.creeUneCollectionFinieDeComics(nombreDeComics);
        
    }

    @Lorsque("je supprime un comics")
    public void je_supprime_un_comics() {
        maCollection.supprimeUnComics("Strange", 1);
    }

    @Alors("je dois avoir une collection avec {int} comics")
    public void je_dois_avoir_une_collection_avec_comics(Integer nouveauNombreDeComics) throws Exception {
        assertEquals(nouveauNombreDeComics, maCollection.recupereLeNombreDeComics());
    }

}
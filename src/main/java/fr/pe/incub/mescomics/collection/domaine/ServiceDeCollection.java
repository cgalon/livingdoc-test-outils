package fr.pe.incub.mescomics.collection.domaine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeCollection {

    EntrepotDeComics entrepotDeComics;

    @Autowired
    public ServiceDeCollection(EntrepotDeComics entrepotDeComics) {
        this.entrepotDeComics = entrepotDeComics;
    }

    public Collection recupererMaCollectionCompleteDeComics() {
        Collection maCollection = new Collection();
        Iterable<Comics> mesComics = entrepotDeComics.retrouveTousLesComics();
        mesComics.forEach(unComics->maCollection.ajouteUnNouveauComics(unComics));
        return maCollection;
    }
}


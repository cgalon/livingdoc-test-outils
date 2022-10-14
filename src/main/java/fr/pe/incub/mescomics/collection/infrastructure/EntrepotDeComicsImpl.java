package fr.pe.incub.mescomics.collection.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pe.incub.mescomics.collection.domaine.Comics;
import fr.pe.incub.mescomics.collection.domaine.EntrepotDeComics;

@Component
public class EntrepotDeComicsImpl implements EntrepotDeComics {

    EntrepotDeComicsSpring entrepotDeComics;

    @Autowired
    public EntrepotDeComicsImpl(EntrepotDeComicsSpring entrepotDeComics) {
        this.entrepotDeComics = entrepotDeComics;
    }

    public Iterable<Comics> retrouveTousLesComics() {
        return entrepotDeComics.findAll();
    };

}

package fr.pe.incub.mescomics.listeachat.infrastructure;

import fr.pe.incub.mescomics.collection.api.MaCollectionDeComics;
import fr.pe.incub.mescomics.collection.domaine.Comics;
import fr.pe.incub.mescomics.listeachat.domaine.EntrepotDeComicsDeMaCollection;
import fr.pe.incub.mescomics.listeachat.domaine.NumeroPresent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EntrepotDeComicsDeMaCollectionImpl implements EntrepotDeComicsDeMaCollection{

    private MaCollectionDeComics maCollectionDeComics;

    @Autowired
    public EntrepotDeComicsDeMaCollectionImpl(MaCollectionDeComics maCollectionDeComics) {
        this.maCollectionDeComics = maCollectionDeComics;
    }

    public List<NumeroPresent> retrouveLesComicsQueJePossedePourCeTitre(String titre) {
        List<Comics> lesComicsDeMaCollection = maCollectionDeComics.retourneMaListeDeComicsPourCeTitre(titre);
        return lesComicsDeMaCollection.stream().map(transformeEnNumeroPresent).collect(Collectors.toList());
    }

    private Function<Comics, NumeroPresent> transformeEnNumeroPresent = comics -> {
        NumeroPresent numero = new NumeroPresent(comics.titreDeLaRevue, comics.numero);
        return numero;
    };
}

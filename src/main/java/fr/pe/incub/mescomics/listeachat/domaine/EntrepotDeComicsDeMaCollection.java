package fr.pe.incub.mescomics.listeachat.domaine;

import java.util.List;

public interface EntrepotDeComicsDeMaCollection {

    public List<NumeroPresent> retrouveLesComicsQueJePossedePourCeTitre(String titre);
}

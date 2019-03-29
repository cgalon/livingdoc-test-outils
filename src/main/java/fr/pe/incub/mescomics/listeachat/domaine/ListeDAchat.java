package fr.pe.incub.mescomics.listeachat.domaine;

import java.util.ArrayList;
import java.util.List;

public class ListeDAchat {

    private final List<NumeroManquant> listeDeNumeroManquants = new ArrayList<>();

    public int nombreDeNumerosManquants() {
        return listeDeNumeroManquants.size();
    }

    public void ajouteUnNumeroManquant(NumeroManquant numeroManquant) {
        listeDeNumeroManquants.add(numeroManquant);
    }
}

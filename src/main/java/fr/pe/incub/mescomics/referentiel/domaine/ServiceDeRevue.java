package fr.pe.incub.mescomics.referentiel.domaine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeRevue {

    EntrepotDeRevues entrepotDeRevues;

    EntrepotDeNumeros entrepotDeNumeros;

    @Autowired
    public ServiceDeRevue(EntrepotDeRevues entrepotDeRevues, EntrepotDeNumeros entrepotDeNumeros) {
        this.entrepotDeRevues = entrepotDeRevues;
        this.entrepotDeNumeros = entrepotDeNumeros;
    }

    public Revue retrouveLaRevuePourCeTitre(String titre) {
        String identifiantDeLaRevue = retrouveLIdentifiantDuneRevueParSonTitre(titre);
        return entrepotDeRevues.retrouveLaRevueParSonIdentifiant(identifiantDeLaRevue);
    }

    public Numero retrouveLePremierNumeroPourCetteRevue(Revue revue) {
        String identifiantDeLaRevue = retrouveLIdentifiantDuneRevueParSonTitre(revue.titre);
        return entrepotDeNumeros.retrouveLePremierNumeroDeLaRevue(identifiantDeLaRevue);
    }

    private String retrouveLIdentifiantDuneRevueParSonTitre(String titre) {
        return titre + "-id";
    }
}

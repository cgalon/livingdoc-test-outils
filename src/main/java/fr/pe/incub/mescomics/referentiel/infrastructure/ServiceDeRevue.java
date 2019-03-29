package fr.pe.incub.mescomics.referentiel.infrastructure;

import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeRevue {

    @Autowired
    EntrepotDeRevues entrepotDeRevues;

    @Autowired
    EntrepotDeNumeros entrepotDeNumeros;

    public ServiceDeRevue(EntrepotDeRevues entrepotDeRevues, EntrepotDeNumeros entrepotDeNumeros) {
        this.entrepotDeRevues = entrepotDeRevues;
        this.entrepotDeNumeros = entrepotDeNumeros;
    }

    public Revue retrouveLaRevuePourCeTitre(String titre) {
        return entrepotDeRevues.findOne(titre);
    }

    public Numero retrouveLePremierNumeroPourCetteRevue(Revue revue) {
        return entrepotDeNumeros.findOne(revue.titre + "-1");
    }
}

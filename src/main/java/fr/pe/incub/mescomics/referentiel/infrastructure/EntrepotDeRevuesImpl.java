package fr.pe.incub.mescomics.referentiel.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pe.incub.mescomics.referentiel.domaine.EntrepotDeRevues;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;

@Component
public class EntrepotDeRevuesImpl implements EntrepotDeRevues {

    EntrepotDeRevuesSpring entrepotDeRevuesSpring;

    @Autowired
    public EntrepotDeRevuesImpl(EntrepotDeRevuesSpring entrepotDeRevuesSpring) {
        this.entrepotDeRevuesSpring = entrepotDeRevuesSpring;
    }
    
    public Revue retrouveLaRevueParSonIdentifiant(String identifiantDeLaRevue) {
        RevueDAO revueDAO = entrepotDeRevuesSpring.findById(identifiantDeLaRevue).get();
        Revue revue = new Revue(revueDAO.titre, revueDAO.recupereLeNombreDeNumeros(), revueDAO.nomDeLEditeur, revueDAO.encoreDesNumerosAParaitre());
        return revue;
    };

}


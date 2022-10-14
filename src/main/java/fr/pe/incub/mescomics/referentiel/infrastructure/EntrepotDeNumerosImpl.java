package fr.pe.incub.mescomics.referentiel.infrastructure;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pe.incub.mescomics.referentiel.domaine.EntrepotDeNumeros;
import fr.pe.incub.mescomics.referentiel.domaine.Numero;

@Component
public class EntrepotDeNumerosImpl implements EntrepotDeNumeros {

    EntrepotDeNumerosSpring entrepotDeNumerosSpring;

    @Autowired
    public EntrepotDeNumerosImpl(EntrepotDeNumerosSpring entrepotDeNumerosSpring) {
        this.entrepotDeNumerosSpring = entrepotDeNumerosSpring;
    }
    
    public Numero retrouveLePremierNumeroDeLaRevue(String identifiantDeLaRevue) {
        NumeroDAO numeroDAO = entrepotDeNumerosSpring.findById(identifiantDeLaRevue).get();
        Numero numero = new Numero(numeroDAO.nomDeLaRevue, numeroDAO.numeroDansLaSerie, numeroDAO.dateDeParution);
        return numero;
    };

}
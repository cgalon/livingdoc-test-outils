package fr.pe.incub.mescomics.referentiel.ressource;

import fr.pe.incub.mescomics.referentiel.api.ReferentielDeRevues;
import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Ressource de gestion du référentiel de Comics. Permet d'ajouter ou de supprimer des numéros.
 */
@RestController
@RequestMapping("/revue")
public class ReferentielDeRevuesController {

//    @Autowired
//    private ReferentielDeRevues referentielDeRevues;

    @RequestMapping
    public Optional<Revue> retrouveLaRevuePourCeTitre(@RequestParam String titre) {
//        return referentielDeRevues.retrouveLaRevuePourCeTitre(titre);
        return null;
    }

    @RequestMapping(value="/{titre}/numero", method = RequestMethod.GET)
    public Optional<Numero> retrouveLePremierNumeroPourCetteRevue(@PathVariable("titre") String titre) {
//        Revue revueRecherchee = referentielDeRevues.retrouveLaRevuePourCeTitre(titre).get();
//        return referentielDeRevues.retrouveLePremierNumeroPourCetteRevue(revueRecherchee);
        return null;
    }

    /**
     * Non implémenté. Existe juste pour tester la génération de documentation.
     * @param nouveauNombreDeNumeros
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void modifieLeNombreDeNumerosDUnerevue(@RequestParam int nouveauNombreDeNumeros) {

    }

    /**
     * Non implémenté. Existe juste pour tester la génération de documentation.
     * @param titre
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void supprimeLaRevuePourCeTitre(@RequestParam String titre) {

    }


}

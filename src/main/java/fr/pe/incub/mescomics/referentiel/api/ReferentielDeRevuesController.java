package fr.pe.incub.mescomics.referentiel.api;

import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Ressource de gestion du référentiel de Comics. Permet d'ajouter ou de supprimer des numéros.
 */
@RestController
public class ReferentielDeRevuesController {

    @Autowired
    ReferentielDeRevues monReferentielDeRevues;

    @RequestMapping(value="/revue", method = RequestMethod.GET)
    public Optional<Revue> retrouveLaRevuePourCeTitre(@RequestParam String titre) {
        return monReferentielDeRevues.retrouveLaRevuePourCeTitre(titre);
    }

    @RequestMapping(value="/revue/{titre}/numero", method = RequestMethod.GET)
    public Optional<Numero> retrouveLePremierNumeroPourCetteRevue(@PathVariable("titre") String titre) {
        Revue revueRecherchee = monReferentielDeRevues.retrouveLaRevuePourCeTitre(titre).get();
        return monReferentielDeRevues.retrouveLePremierNumeroPourCetteRevue(revueRecherchee);
    }

    /**
     * Non implémenté. Existe juste pour tester la génération de documentation.
     * @param nouveauNombreDeNumeros
     */
    @RequestMapping(value="/revue", method = RequestMethod.PUT)
    public void modifieLeNombreDeNumerosDUnerevue(@RequestParam int nouveauNombreDeNumeros) {

    }

    /**
     * Non implémenté. Existe juste pour tester la génération de documentation.
     * @param titre
     */
    @RequestMapping(value="/revue", method = RequestMethod.DELETE)
    public void supprimeLaRevuePourCeTitre(@RequestParam String titre) {

    }


}

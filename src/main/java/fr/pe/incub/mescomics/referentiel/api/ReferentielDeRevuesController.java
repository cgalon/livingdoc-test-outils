package fr.pe.incub.mescomics.referentiel.api;

import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Ressource de gestion du référentiel de Comics. Permet d'ajouter ou de supprimer des numéros.
 */
@Api(value="Référentiel de revues", description="Ressource de gestion du référentiel de Comics. Permet d'ajouter ou de supprimer des numéros.")
@RestController
@RequestMapping("/revue")
public class ReferentielDeRevuesController {

    private ReferentielDeRevues referentielDeRevues;

    @Autowired
    public ReferentielDeRevuesController(ReferentielDeRevues referentielDeRevues) {
        this.referentielDeRevues = referentielDeRevues;
    }

    @ApiOperation(value ="Retourne une revue correspondante au titre passé en paramètre.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recherche effectuée avec succès."),
            @ApiResponse(code = 404, message = "Pas de revue référencée pour le titre spécifié.")
    })
    @RequestMapping(method = RequestMethod.GET)
    public Optional<Revue> retrouveLaRevuePourCeTitre(@RequestParam String titre) {
        return referentielDeRevues.retrouveLaRevuePourCeTitre(titre);
    }

    @ApiOperation(value ="Retourne le premier numero d'une revue correspondante au titre passé en paramètre.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recherche effectuée avec succès.")
    })
    @RequestMapping(value="/{titre}/numero", method = RequestMethod.GET)
    public Optional<Numero> retrouveLePremierNumeroPourCetteRevue(@PathVariable("titre") String titre) {
        Revue revueRecherchee = referentielDeRevues.retrouveLaRevuePourCeTitre(titre).get();
        return referentielDeRevues.retrouveLePremierNumeroPourCetteRevue(revueRecherchee);
    }

    /**
     * Non implémenté. Existe juste pour tester la génération de documentation.
     * @param nouveauNombreDeNumeros
     */
    @ApiOperation(value ="Modifie le nombre de numeros d'une revue.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Modification effectuée avec succès.")
    })
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void modifieLeNombreDeNumerosDUnerevue(@RequestParam int nouveauNombreDeNumeros) {

    }

    /**
     * Non implémenté. Existe juste pour tester la génération de documentation.
     * @param titre
     */
    @ApiOperation(value ="Supprime une revue correspondante au titre passé en paramètre.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suppression effectuée avec succès.")
    })
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void supprimeLaRevuePourCeTitre(@RequestParam String titre) {

    }


}

package fr.pe.incub.mescomics.collection.api;

import fr.pe.incub.mescomics.collection.domaine.Comics;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Ressource de gestion de la collection de Comics détenus par l'utilisateur.
 */
@RestController
@RequestMapping("/collection/titre")
@Tag(name="Collection de comics", description="Ressource de gestion de la collection de Comics détenus par " +
        "l'utilisateur.")
public class MaCollectionDeComicsController {

    MaCollectionDeComics maCollectionDeComics;

    @Autowired
    public MaCollectionDeComicsController(@Autowired MaCollectionDeComics maCollectionDeComics) {
        this.maCollectionDeComics = maCollectionDeComics;
    }

    @Operation(description ="Retourne une liste de comics pour la titre passé en paramètre.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste trouvée"),
            @ApiResponse(responseCode = "401", description = "Opération non autorisée"),
            @ApiResponse(responseCode = "403", description = "Opération interdite"),
            @ApiResponse(responseCode = "404", description = "Pas de comics trouvé pour ce titre")
    })
    @RequestMapping(method = RequestMethod.GET)
    public List<Comics> retourneMaListeDeComicsPourCeTitre(@RequestParam String titre) {
        return maCollectionDeComics.retourneMaListeDeComicsPourCeTitre(titre);
    }

    /**
     * Non implémenté. Existe juste pour tester la génération de documentation.
     * @param titre
     */
    @Operation(description ="Supprime la liste de comics correspondants au titre passé en paramètre.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suppression effectuée avec succès"),
            @ApiResponse(responseCode = "401", description = "Opération non autorisée"),
            @ApiResponse(responseCode = "403", description = "Opération interdite"),
            @ApiResponse(responseCode = "404", description = "Pas de comics à supprimer pour ce titre")
    })
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void supprimeUneListeDeComicsPourCeTitre(@RequestParam String titre) {

    }
}

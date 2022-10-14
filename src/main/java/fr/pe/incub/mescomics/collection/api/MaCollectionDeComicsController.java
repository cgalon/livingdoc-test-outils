package fr.pe.incub.mescomics.collection.api;

import fr.pe.incub.mescomics.collection.domaine.Comics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Ressource de gestion de la collection de Comics détenus par l'utilisateur.
 */
@RestController
@RequestMapping("/collection/titre")
@Api(value="Collection de comics", description="Ressource de gestion de la collection de Comics détenus par l'utilisateur.")
public class MaCollectionDeComicsController {

    MaCollectionDeComics maCollectionDeComics;

    @Autowired
    public MaCollectionDeComicsController(@Autowired MaCollectionDeComics maCollectionDeComics) {
        this.maCollectionDeComics = maCollectionDeComics;
    }

    @ApiOperation(value ="Retourne une liste de comics pour la titre passé en paramètre.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste trouvée"),
            @ApiResponse(code = 401, message = "Opération non autorisée"),
            @ApiResponse(code = 403, message = "Opération interdite"),
            @ApiResponse(code = 404, message = "Pas de comics trouvé pour ce titre")
    })
    @RequestMapping(method = RequestMethod.GET)
    public List<Comics> retourneMaListeDeComicsPourCeTitre(@RequestParam String titre) {
        return maCollectionDeComics.retourneMaListeDeComicsPourCeTitre(titre);
    }

    /**
     * Non implémenté. Existe juste pour tester la génération de documentation.
     * @param titre
     */
    @ApiOperation(value ="Supprime la liste de comics correspondants au titre passé en paramètre.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suppression effectuée avec succès"),
            @ApiResponse(code = 401, message = "Opération non autorisée"),
            @ApiResponse(code = 403, message = "Opération interdite"),
            @ApiResponse(code = 404, message = "Pas de comics à supprimer pour ce titre")
    })
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void supprimeUneListeDeComicsPourCeTitre(@RequestParam String titre) {

    }
}

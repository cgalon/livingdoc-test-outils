package fr.pe.incub.mescomics.collection.ressource;

import fr.pe.incub.mescomics.collection.api.MaCollectionDeComics;
import fr.pe.incub.mescomics.collection.domaine.Comics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Ressource de gestion de la collection de Comics détenus par l'utilisateur.
 */
@RestController
@RequestMapping("/collection/titre")
public class MaCollectionDeComicsController {

    MaCollectionDeComics maCollectionDeComics;

    @Autowired
    public MaCollectionDeComicsController(@Autowired MaCollectionDeComics maCollectionDeComics) {
        this.maCollectionDeComics = maCollectionDeComics;
    }

    @RequestMapping
    public List<Comics> retourneMaListeDeComicsPourCeTitre(@RequestParam String titre) {
        return maCollectionDeComics.retourneMaListeDeComicsPourCeTitre(titre);
    }

    /**
     * Non implémenté. Existe juste pour tester la génération de documentation.
     * @param titre
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void supprimeUneListeDeComicsPourCeTitre(@RequestParam String titre) {

    }
}

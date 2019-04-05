package fr.pe.incub.mescomics.collection.api;

import fr.pe.incub.mescomics.collection.domaine.Comics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Ressource de gestion de la collection de Comics détenus par l'utilisateur.
 */
@RestController
public class MaCollectionDeComicsController {

    @Autowired
    MaCollectionDeComics maCollectionDeComics;

    @RequestMapping("/collection/titre")
    public List<Comics> retourneMaListeDeComicsPourCeTitre(@RequestParam String titre) {
        return maCollectionDeComics.retourneMaListeDeComicsPourCeTitre(titre);
    }

}

package fr.pe.incub.cga.livingdoc.ressources;

import fr.pe.incub.cga.livingdoc.donnees.Personne;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personne")
public class PersonneRessource {

    @RequestMapping(method = RequestMethod.GET)
    public Personne recuperePersonneParIdentifiant(@RequestParam(value="id") int identifiant) {
        return new Personne(identifiant, "Mon nom", "Mon prénom");
    }

    @RequestMapping(path = "/random", method = RequestMethod.GET)
    public Personne recupereUnePersonneAuHasard() {
        return new Personne(4, "Mon nom random", "Mon prénom random");
    }

}

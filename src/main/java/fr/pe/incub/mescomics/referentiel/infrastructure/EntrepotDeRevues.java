package fr.pe.incub.mescomics.referentiel.infrastructure;

import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import org.springframework.data.repository.CrudRepository;

public interface EntrepotDeRevues extends CrudRepository<Revue, String> {

}
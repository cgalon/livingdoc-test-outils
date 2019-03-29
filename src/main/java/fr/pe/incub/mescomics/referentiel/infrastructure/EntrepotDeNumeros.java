package fr.pe.incub.mescomics.referentiel.infrastructure;

import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import org.springframework.data.repository.CrudRepository;

public interface EntrepotDeNumeros extends CrudRepository<Numero, String> {

}
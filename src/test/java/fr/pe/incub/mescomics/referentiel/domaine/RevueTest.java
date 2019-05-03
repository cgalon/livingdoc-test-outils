package fr.pe.incub.mescomics.referentiel.domaine;

import fr.pe.incub.mescomics.referentiel.domaine.exception.ParutionRevueTermineeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RevueTest {

    @Test
    public void doitCreerUneRevueAvecDesInfosSpécifiques() {
        Revue uneRevue = new Revue("Strange", 228, "Lug", false);

        assertEquals("Strange", uneRevue.titre);
        assertEquals(228, uneRevue.recupereLeNombreDeNumeros());
        assertEquals("Lug", uneRevue.nomDeLEditeur);
        assertEquals(false, uneRevue.encoreDesNumerosAParaitre());
    }

    @Test
    public void doitModifierLeNombreDeNumerosDUneRevue() throws ParutionRevueTermineeException {
        Revue uneRevue = new Revue("Marvel Icons", 45, "Panini", true);

        uneRevue.modifieLeNombreDeNumeros(55);

        assertEquals(55, uneRevue.recupereLeNombreDeNumeros());
    }

    @Test
    public void doitRetournerUneExceptionSiOnModifieLeNombreDeNumerosDUneRevueQuiNeParaitPlus() throws ParutionRevueTermineeException {
        Revue uneRevue = new Revue("Strange", 228, "Lug", false);

        assertThrows(ParutionRevueTermineeException.class, () -> {
            uneRevue.modifieLeNombreDeNumeros(229);
        });
    }


}
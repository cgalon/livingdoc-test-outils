package fr.pe.incub.mescomics.listeachat.infrastructure;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.referentiel.api.ReferentielDeRevues;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecuperateurDeRevueReferenceeTest {

    @Mock
    ReferentielDeRevues referentielDeRevuesBouchon;

    UtilitairesDeTest utilitairesDeTest = new UtilitairesDeTest();

    @Test
    public void doitRamenerUneRevueReferenceeExistante() {
        Revue strange = utilitairesDeTest.creeUneRevueStrange();
        when(referentielDeRevuesBouchon.retrouveLaRevuePourCeTitre("Strange")).thenReturn(Optional.of(strange));
        when(referentielDeRevuesBouchon.retrouveLePremierNumeroPourCetteRevue(strange)).thenReturn(Optional.of(utilitairesDeTest.creeUnPremierNumeroDeStrange()));
        RecuperateurDeRevueReferencee recuperateurDeRevueReferencee = new RecuperateurDeRevueReferencee(referentielDeRevuesBouchon);

        RevueReferencee revueStrange = recuperateurDeRevueReferencee.retrouveLaRevuePourCeTitre("Strange");

        RevueReferencee strangeReferencee = utilitairesDeTest.creeUneRevueReferenceePourStrange();
        assertEquals(strangeReferencee, revueStrange);
    }

    @Test
    public void doitRamenerNullPourUneRevueInexistante() {
        when(referentielDeRevuesBouchon.retrouveLaRevuePourCeTitre("Conan")).thenReturn(Optional.ofNullable(null));
        RecuperateurDeRevueReferencee recuperateurDeRevueReferencee = new RecuperateurDeRevueReferencee(referentielDeRevuesBouchon);

        RevueReferencee conan = recuperateurDeRevueReferencee.retrouveLaRevuePourCeTitre("Conan");

        assertNull(conan);
    }

}
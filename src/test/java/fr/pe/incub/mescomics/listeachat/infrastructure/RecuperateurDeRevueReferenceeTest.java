package fr.pe.incub.mescomics.listeachat.infrastructure;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.listeachat.domaine.RevueReferencee;
import fr.pe.incub.mescomics.referentiel.api.ReferentielDeRevues;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecuperateurDeRevueReferenceeTest {

    @Mock
    ReferentielDeRevues referentielDeRevuesBouchon;

    UtilitairesDeTest utilitairesDeTest = new UtilitairesDeTest();

    @Test
    public void doitRamenerUneRevueReferenceeExistante() {
        Revue strange = utilitairesDeTest.creeUneRevueStrange();
        when(referentielDeRevuesBouchon.retrouveLaRevuePourCeTitre("Strange")).thenReturn(Optional.of(strange));
        when(referentielDeRevuesBouchon.retrouveLePremierNumeroPourCetteRevue(strange)).thenReturn(Optional.of(utilitairesDeTest.creeUnPremierNumeroDeStrange()));
        EntrepotDeRevueReferenceeImpl recuperateurDeRevueReferencee = new EntrepotDeRevueReferenceeImpl(referentielDeRevuesBouchon);

        RevueReferencee revueStrange = recuperateurDeRevueReferencee.retrouveLaRevuePourCeTitre("Strange");

        RevueReferencee strangeReferencee = utilitairesDeTest.creeUneRevueReferenceePourStrange();
        assertEquals(strangeReferencee, revueStrange);
    }

    @Test
    public void doitRamenerNullPourUneRevueInexistante() {
        when(referentielDeRevuesBouchon.retrouveLaRevuePourCeTitre("Conan")).thenReturn(Optional.ofNullable(null));
        EntrepotDeRevueReferenceeImpl recuperateurDeRevueReferencee = new EntrepotDeRevueReferenceeImpl(referentielDeRevuesBouchon);

        RevueReferencee conan = recuperateurDeRevueReferencee.retrouveLaRevuePourCeTitre("Conan");

        assertNull(conan);
    }

}
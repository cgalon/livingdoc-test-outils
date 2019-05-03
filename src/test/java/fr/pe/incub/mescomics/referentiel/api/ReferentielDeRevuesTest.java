package fr.pe.incub.mescomics.referentiel.api;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import fr.pe.incub.mescomics.referentiel.infrastructure.ServiceDeRevue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReferentielDeRevuesTest {

    UtilitairesDeTest utilitairesDeTest = new UtilitairesDeTest();

    @Mock
    ServiceDeRevue serviceDeRevueBouchon;

    @Test
    public void doitRetrouverUneRevuePourUnTitre() {
        when(serviceDeRevueBouchon.retrouveLaRevuePourCeTitre("Strange")).thenReturn(utilitairesDeTest.creeUneRevueStrange());
        ReferentielDeRevues referentielDeRevues = new ReferentielDeRevues(serviceDeRevueBouchon);

        Optional<Revue> revueStrange = referentielDeRevues.retrouveLaRevuePourCeTitre("Strange");

        Revue strangeDeReference = utilitairesDeTest.creeUneRevueStrange();
        assertEquals(strangeDeReference, revueStrange.get());
    }

    @Test
    public void doitRetrouverLePremierNumeroDUneRevue() {
        Revue strange = utilitairesDeTest.creeUneRevueStrange();
        when(serviceDeRevueBouchon.retrouveLePremierNumeroPourCetteRevue(strange)).thenReturn(utilitairesDeTest.creeUnPremierNumeroDeStrange());
        ReferentielDeRevues referentielDeRevues = new ReferentielDeRevues(serviceDeRevueBouchon);

        Optional<Numero> premierStrange = referentielDeRevues.retrouveLePremierNumeroPourCetteRevue(strange);

        Numero numeroDeReference = utilitairesDeTest.creeUnPremierNumeroDeStrange();
        assertEquals(numeroDeReference, premierStrange.get());
    }
}
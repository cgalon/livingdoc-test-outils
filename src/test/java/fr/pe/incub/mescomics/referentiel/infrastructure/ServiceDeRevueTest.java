package fr.pe.incub.mescomics.referentiel.infrastructure;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.referentiel.domaine.EntrepotDeNumeros;
import fr.pe.incub.mescomics.referentiel.domaine.EntrepotDeRevues;
import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import fr.pe.incub.mescomics.referentiel.domaine.ServiceDeRevue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceDeRevueTest {

    @Mock
    EntrepotDeRevues entrepotDeRevues;

    @Mock
    EntrepotDeNumeros entrepotDeNumeros;

    UtilitairesDeTest utilitairesDeTest = new UtilitairesDeTest();

    @Test
    public void doitRecupererUneRevueParSonTitre() {
        when(entrepotDeRevues.retrouveLaRevueParSonIdentifiant("Strange-id")).thenReturn(utilitairesDeTest.creeUneRevueStrange());
        ServiceDeRevue serviceDeRevue = new ServiceDeRevue(entrepotDeRevues, entrepotDeNumeros);

        Revue strange = serviceDeRevue.retrouveLaRevuePourCeTitre("Strange");

        assertEquals(utilitairesDeTest.creeUneRevueStrange(), strange);
    }

    @Test
    public void doitRecupererLePremierNumeroDUneRevue() {
        when(entrepotDeNumeros.retrouveLePremierNumeroDeLaRevue("Strange-id")).thenReturn(utilitairesDeTest.creeUnPremierNumeroDeStrange());
        ServiceDeRevue serviceDeRevue = new ServiceDeRevue(entrepotDeRevues, entrepotDeNumeros);

        Numero premierStrange = serviceDeRevue.retrouveLePremierNumeroPourCetteRevue(utilitairesDeTest.creeUneRevueStrange());

        assertEquals(utilitairesDeTest.creeUnPremierNumeroDeStrange(), premierStrange);
    }
}
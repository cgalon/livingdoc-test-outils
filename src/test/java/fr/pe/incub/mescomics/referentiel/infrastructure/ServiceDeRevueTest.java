package fr.pe.incub.mescomics.referentiel.infrastructure;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceDeRevueTest {

    @Mock
    EntrepotDeRevues entrepotDeRevues;

    @Mock
    EntrepotDeNumeros entrepotDeNumeros;

    UtilitairesDeTest utilitairesDeTest = new UtilitairesDeTest();

    @Test
    public void doitRecupererUneRevueParSonTitre() {
        when(entrepotDeRevues.findOne("Strange")).thenReturn(utilitairesDeTest.creeUneRevueDAOStrange());
        ServiceDeRevue serviceDeRevue = new ServiceDeRevue(entrepotDeRevues, entrepotDeNumeros);

        Revue strange = serviceDeRevue.retrouveLaRevuePourCeTitre("Strange");

        assertEquals(utilitairesDeTest.creeUneRevueStrange(), strange);
    }

    @Test
    public void doitRecupererLePremierNumeroDUneRevue() {
        when(entrepotDeNumeros.findOne("Strange-1")).thenReturn(utilitairesDeTest.creeUnPremierNumeroDAODeStrange());
        ServiceDeRevue serviceDeRevue = new ServiceDeRevue(entrepotDeRevues, entrepotDeNumeros);

        Numero premierStrange = serviceDeRevue.retrouveLePremierNumeroPourCetteRevue(utilitairesDeTest.creeUneRevueStrange());

        assertEquals(utilitairesDeTest.creeUnPremierNumeroDeStrange(), premierStrange);
    }
}
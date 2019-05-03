package fr.pe.incub.mescomics.listeachat.domaine;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.listeachat.infrastructure.RecuperateurDeComicsDeMaCollection;
import fr.pe.incub.mescomics.listeachat.infrastructure.RecuperateurDeRevueReferencee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateurDeListeDAchatTest {

    @Mock
    private RecuperateurDeComicsDeMaCollection recuperateurDeMaCollectionDeComicsBouchon;

    @Mock
    private RecuperateurDeRevueReferencee recuperateurDeRevueReferenceeBouchon;

    UtilitairesDeTest utilitairesDeTest = new UtilitairesDeTest();

    @Test
    public void doitCreerUneListeDAchatVideLorsqueJAiTousLesNumeros() {
        when(recuperateurDeMaCollectionDeComicsBouchon.retrouveLesComicsQueJePossedePourCeTitre("Spidey")).thenReturn(utilitairesDeTest.creeUneListeCompleteDeNumerosPresentsDeSpidey());
        when(recuperateurDeRevueReferenceeBouchon.retrouveLaRevuePourCeTitre("Spidey")).thenReturn(utilitairesDeTest.creeUneRevueReferenceePourSpidey());
        CreateurDeListeDAchat createurDeListeDAchat = new CreateurDeListeDAchat(recuperateurDeMaCollectionDeComicsBouchon, recuperateurDeRevueReferenceeBouchon);

        ListeDAchat listeDAchatSpidey = createurDeListeDAchat.creeUneListeDAchatPourUneRevue("Spidey");

        assertEquals(0, listeDAchatSpidey.nombreDeNumerosManquants());
    }

    @Test
    public void doitCreerUneListeDAchatCompleteLorsqueJeNAiAucunNumero() {
        when(recuperateurDeMaCollectionDeComicsBouchon.retrouveLesComicsQueJePossedePourCeTitre("Titan")).thenReturn(utilitairesDeTest.creeUneListeDeNumerosPresentsVide());
        when(recuperateurDeRevueReferenceeBouchon.retrouveLaRevuePourCeTitre("Titan")).thenReturn(utilitairesDeTest.creeUneRevueReferenceePourTitan());
        CreateurDeListeDAchat createurDeListeDAchat = new CreateurDeListeDAchat(recuperateurDeMaCollectionDeComicsBouchon, recuperateurDeRevueReferenceeBouchon);

        ListeDAchat listeDAchatTitan = createurDeListeDAchat.creeUneListeDAchatPourUneRevue("Titan");

        assertEquals(10, listeDAchatTitan.nombreDeNumerosManquants());
    }

    @Test
    public void doitCreerUneListeDAchatAPartirDUneCollectionExistante() {
        when(recuperateurDeMaCollectionDeComicsBouchon.retrouveLesComicsQueJePossedePourCeTitre("Strange")).thenReturn(utilitairesDeTest.creeUneListeIncompleteDeNumerosPresentsDeStrange());
        when(recuperateurDeRevueReferenceeBouchon.retrouveLaRevuePourCeTitre("Strange")).thenReturn(utilitairesDeTest.creeUneRevueReferenceePourStrange());
        CreateurDeListeDAchat createurDeListeDAchat = new CreateurDeListeDAchat(recuperateurDeMaCollectionDeComicsBouchon, recuperateurDeRevueReferenceeBouchon);

        ListeDAchat listeDAchatStrange = createurDeListeDAchat.creeUneListeDAchatPourUneRevue("Strange");

        assertEquals(14, listeDAchatStrange.nombreDeNumerosManquants());
    }

}
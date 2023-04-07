package fr.pe.incub.mescomics.listeachat.domaine;

import fr.pe.incub.mescomics.UtilitairesDeTest;
import fr.pe.incub.mescomics.listeachat.infrastructure.EntrepotDeComicsDeMaCollectionImpl;
import fr.pe.incub.mescomics.listeachat.infrastructure.EntrepotDeRevueReferenceeImpl;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Utilisation du framework Approvals.
 * Reprise des tests de la classe CreateurDeListeDAchatTest.
 * Les "Arrange" et "Act" de chaque test sont les mêmes. Seuls les "Assert" sont modifiés pour utiliser ApprovalTests.
 */
@ExtendWith(MockitoExtension.class)
public class CreateurDeListeDAchatApprovalTest {

    @Mock
    private EntrepotDeComicsDeMaCollectionImpl recuperateurDeMaCollectionDeComicsBouchon;

    @Mock
    private EntrepotDeRevueReferenceeImpl recuperateurDeRevueReferenceeBouchon;

    UtilitairesDeTest utilitairesDeTest = new UtilitairesDeTest();

    @Test
    public void doitCreerUneListeDAchatVideLorsqueJAiTousLesNumeros() {
        when(recuperateurDeMaCollectionDeComicsBouchon.retrouveLesComicsQueJePossedePourCeTitre("Spidey")).thenReturn(utilitairesDeTest.creeUneListeCompleteDeNumerosPresentsDeSpidey());
        when(recuperateurDeRevueReferenceeBouchon.retrouveLaRevuePourCeTitre("Spidey")).thenReturn(utilitairesDeTest.creeUneRevueReferenceePourSpidey());
        CreateurDeListeDAchat createurDeListeDAchat = new CreateurDeListeDAchat(recuperateurDeMaCollectionDeComicsBouchon, recuperateurDeRevueReferenceeBouchon);

        ListeDAchat listeDAchatSpidey = createurDeListeDAchat.creeUneListeDAchatPourUneRevue("Spidey");

        Approvals.verify(listeDAchatSpidey.nombreDeNumerosManquants());
    }

    @Test
    public void doitCreerUneListeDAchatCompleteLorsqueJeNAiAucunNumero() {
        when(recuperateurDeMaCollectionDeComicsBouchon.retrouveLesComicsQueJePossedePourCeTitre("Titan")).thenReturn(utilitairesDeTest.creeUneListeDeNumerosPresentsVide());
        when(recuperateurDeRevueReferenceeBouchon.retrouveLaRevuePourCeTitre("Titan")).thenReturn(utilitairesDeTest.creeUneRevueReferenceePourTitan());
        CreateurDeListeDAchat createurDeListeDAchat = new CreateurDeListeDAchat(recuperateurDeMaCollectionDeComicsBouchon, recuperateurDeRevueReferenceeBouchon);

        ListeDAchat listeDAchatTitan = createurDeListeDAchat.creeUneListeDAchatPourUneRevue("Titan");

        Approvals.verifyAll("Numéro de Titan manquant", listeDAchatTitan.listeDesNumerosManquants());
    }

    @Test
    public void doitCreerUneListeDAchatAPartirDUneCollectionExistante() {
        when(recuperateurDeMaCollectionDeComicsBouchon.retrouveLesComicsQueJePossedePourCeTitre("Strange")).thenReturn(utilitairesDeTest.creeUneListeIncompleteDeNumerosPresentsDeStrange());
        when(recuperateurDeRevueReferenceeBouchon.retrouveLaRevuePourCeTitre("Strange")).thenReturn(utilitairesDeTest.creeUneRevueReferenceePourStrange());
        CreateurDeListeDAchat createurDeListeDAchat = new CreateurDeListeDAchat(recuperateurDeMaCollectionDeComicsBouchon, recuperateurDeRevueReferenceeBouchon);

        ListeDAchat listeDAchatStrange = createurDeListeDAchat.creeUneListeDAchatPourUneRevue("Strange");

        Approvals.verifyAll("Numéro de Strange manquant", listeDAchatStrange.listeDesNumerosManquants());
    }

}
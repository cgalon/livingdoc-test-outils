package fr.pe.incub.mescomics.listeachat.domaine;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CreateurDeListeDAchat {

    private EntrepotDeComicsDeMaCollection recuperateurDeMaCollectionDeComics;

    private EntrepotDeRevueReferencee recuperateurDeRevueReferencee;

    @Autowired
    public CreateurDeListeDAchat(EntrepotDeComicsDeMaCollection recuperateurDeMaCollectionDeComics, EntrepotDeRevueReferencee recuperateurDeRevueReferencee) {
        this.recuperateurDeMaCollectionDeComics = recuperateurDeMaCollectionDeComics;
        this.recuperateurDeRevueReferencee = recuperateurDeRevueReferencee;
    }

    public ListeDAchat creeUneListeDAchatPourUneRevue(String titre) {
        List<NumeroPresent> lesNumerosQueJePossede = recuperateurDeMaCollectionDeComics.retrouveLesComicsQueJePossedePourCeTitre(titre);

        RevueReferencee revue = recuperateurDeRevueReferencee.retrouveLaRevuePourCeTitre(titre);

        ListeDAchat listeDAchatPourCeTitre = new ListeDAchat();
        for (int i = 1; i <= revue.nombreDeNumeros; i++) {
            NumeroPresent numeroAVerifier = new NumeroPresent(titre, i);
            if (!lesNumerosQueJePossede.contains(numeroAVerifier)) {
                NumeroManquant numeroManquant = new NumeroManquant(titre, i, revue.dateDePremiereParution, revue.editeur);
                listeDAchatPourCeTitre.ajouteUnNumeroManquant(numeroManquant);
            }
        }

        return listeDAchatPourCeTitre;
    }

}

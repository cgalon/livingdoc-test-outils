package fr.pe.incub.mescomics;

import fr.pe.incub.mescomics.collection.domaine.Collection;
import fr.pe.incub.mescomics.collection.domaine.Comics;
import fr.pe.incub.mescomics.listeachat.domaine.NumeroPresent;
import fr.pe.incub.mescomics.listeachat.domaine.RevueReferencee;
import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import fr.pe.incub.mescomics.referentiel.infrastructure.NumeroDAO;
import fr.pe.incub.mescomics.referentiel.infrastructure.RevueDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilitairesDeTest {

    public List<Comics> creeUneListeDeComics() {
        Comics strangeNumero15 = new Comics("Strange", 15);
        Comics strangeNumero30 = new Comics("Strange", 30);
        Comics strangeNumero31 = new Comics("Strange", 31);
        Comics strangeNumero32 = new Comics("Strange", 32);
        Comics strangeNumero45 = new Comics("Strange", 45);
        Comics specialStrangeNumero50 = new Comics("Special Strange", 50);
        Comics specialStrangeNumero60 = new Comics("Special Strange", 60);
        List<Comics> liste = new ArrayList<>();
        liste.add(strangeNumero15);
        liste.add(strangeNumero30);
        liste.add(strangeNumero31);
        liste.add(strangeNumero32);
        liste.add(strangeNumero45);
        liste.add(specialStrangeNumero50);
        liste.add(specialStrangeNumero60);
        return liste;
    }

    public List<Comics> creeUneListeFinieDeComics(int nombreDeComics) {
        List<Comics> liste = new ArrayList<>();
        for (int i = 0; i < nombreDeComics; i++) {
            liste.add(new Comics("Strange", i));
        }
        return liste;
    }

    public Collection creeUneCollectionDeComics() {
        Collection maCollection = new Collection();
        Iterable<Comics> mesComics = creeUneListeDeComics();
        mesComics.forEach(unComics->maCollection.ajouteUnNouveauComics(unComics));
        return maCollection;
    }

    public Collection creeUneCollectionFinieDeComics(int nombreDeComics) {
        Collection maCollection = new Collection();
        Iterable<Comics> mesComics = creeUneListeFinieDeComics(nombreDeComics);
        mesComics.forEach(unComics->maCollection.ajouteUnNouveauComics(unComics));
        return maCollection;
    }

    public Collection creeUneCollectionAvecUnComics() {
        Collection maCollection = new Collection();
        Comics unNouveauComics = new Comics("Strange", 15);

        maCollection.ajouteUnNouveauComics(unNouveauComics);
        return maCollection;
    }

    public Collection creeUneCollectionAvecTroisComics() {
        Collection maCollection = creeUneCollectionAvecUnComics();
        Comics unAutreComics = new Comics("Strange", 30);
        Comics unEncoreUnAutreComics = new Comics("Special Strange", 10);
        maCollection.ajouteUnNouveauComics(unAutreComics);
        maCollection.ajouteUnNouveauComics(unEncoreUnAutreComics);
        return maCollection;
    }

    public List<Comics> creeUneListeDeComicsSpecialStrange() {
        Comics specialStrangeNumero15 = new Comics("Special Strange", 15);
        Comics specialStrangeNumero30 = new Comics("Special Strange", 30);
        Comics specialStrangeNumero45 = new Comics("Special Strange", 45);
        Comics specialStrangeNumero50 = new Comics("Special Strange", 50);
        Comics specialStrangeNumero60 = new Comics("Special Strange", 60);
        List<Comics> liste = new ArrayList<>();
        liste.add(specialStrangeNumero15);
        liste.add(specialStrangeNumero30);
        liste.add(specialStrangeNumero45);
        liste.add(specialStrangeNumero50);
        liste.add(specialStrangeNumero60);
        return liste;
    }

    public List<NumeroPresent> creeUneListeCompleteDeNumerosPresentsDeSpidey() {
        NumeroPresent numero1 = new NumeroPresent("Spidey", 1);
        NumeroPresent numero2 = new NumeroPresent("Spidey", 2);
        NumeroPresent numero3 = new NumeroPresent("Spidey", 3);
        NumeroPresent numero4 = new NumeroPresent("Spidey", 4);
        NumeroPresent numero5 = new NumeroPresent("Spidey", 5);
        NumeroPresent numero6 = new NumeroPresent("Spidey", 6);
        List<NumeroPresent> liste = new ArrayList<>();
        liste.add(numero1);
        liste.add(numero2);
        liste.add(numero3);
        liste.add(numero4);
        liste.add(numero5);
        liste.add(numero6);
        return liste;
    }

    public List<NumeroPresent> creeUneListeDeNumerosPresentsVide() {
        List<NumeroPresent> liste = new ArrayList<>();
        return liste;
    }

    public List<NumeroPresent> creeUneListeIncompleteDeNumerosPresentsDeStrange() {
        NumeroPresent numero1 = new NumeroPresent("Strange", 1);
        NumeroPresent numero5 = new NumeroPresent("Strange", 5);
        NumeroPresent numero10 = new NumeroPresent("Strange", 10);
        NumeroPresent numero11 = new NumeroPresent("Strange", 11);
        NumeroPresent numero12 = new NumeroPresent("Strange", 12);
        NumeroPresent numero15 = new NumeroPresent("Strange", 15);
        List<NumeroPresent> liste = new ArrayList<>();
        liste.add(numero1);
        liste.add(numero5);
        liste.add(numero10);
        liste.add(numero11);
        liste.add(numero12);
        liste.add(numero15);
        return liste;
    }

    public RevueReferencee creeUneRevueReferenceePourSpidey() {
        LocalDate dateDeParution = LocalDate.of(1970, 1, 1);
        return new RevueReferencee("Spidey", 6, dateDeParution, "Lug");
    }

    public RevueReferencee creeUneRevueReferenceePourTitan() {
        LocalDate dateDeParution = LocalDate.of(1970, 1, 1);
        return new RevueReferencee("Titan", 10, dateDeParution, "Lug");
    }

    public RevueReferencee creeUneRevueReferenceePourStrange() {
        LocalDate dateDeParution = LocalDate.of(1970, 1, 1);
        return new RevueReferencee("Strange", 20, dateDeParution, "Lug");
    }

    public Revue creeUneRevueStrange() {
        return new Revue("Strange", 20, "Lug", false);
    }

    public Numero creeUnPremierNumeroDeStrange() {
        return new Numero("Strange",1, LocalDate.of(1970, 1, 1));
    }
}

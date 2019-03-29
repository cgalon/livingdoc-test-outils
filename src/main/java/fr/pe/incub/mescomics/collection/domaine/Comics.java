package fr.pe.incub.mescomics.collection.domaine;

import java.util.Objects;

public class Comics {

    public final String titreDeLaRevue;
    public final int numero;
    public int valeurEnEuros;

    public Comics(String nomDeLaRevue, int numero) {
        this.titreDeLaRevue = nomDeLaRevue;
        this.numero = numero;
        this.valeurEnEuros = 0;
    }

    public Comics(String nomDeLaRevue, int numero, int valeurEnEuros) {
        this.titreDeLaRevue = nomDeLaRevue;
        this.numero = numero;
        this.valeurEnEuros = valeurEnEuros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comics comics = (Comics) o;
        return numero == comics.numero &&
                Objects.equals(titreDeLaRevue, comics.titreDeLaRevue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(titreDeLaRevue, numero);
    }

    public void modifieLaValeurEnEuros(int nouvelleValeur) {
        valeurEnEuros = nouvelleValeur;
    }
}

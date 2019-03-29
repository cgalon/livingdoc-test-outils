package fr.pe.incub.mescomics.collection.domaine;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComicsTest {

    @Test
    public void doitPouvoirModifierLaValeurDUnNumeroDeComics() {
        Comics comics = new Comics("Strange", 15, 50);

        comics.modifieLaValeurEnEuros(75);

        assertEquals(75, comics.valeurEnEuros);
    }

}
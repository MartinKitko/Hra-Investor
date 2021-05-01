package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Vazenie extends Policko {

    public Vazenie(String nazov) {
        super(nazov);
    }

    public void vykonaj(Hrac hrac) {
        System.out.println("Stojis jedno kolo");
        hrac.doVazenia(1);
    }
}

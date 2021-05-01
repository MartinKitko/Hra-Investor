package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class PolickoStart extends Policko {

    public PolickoStart(String nazov) {
        super(nazov);
    }

    public void vykonaj(Hrac hrac) {
        System.out.println("Vitaj spat na starte");
    }
}

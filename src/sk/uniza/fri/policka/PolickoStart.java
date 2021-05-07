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

    public String vykonaj(Hrac hrac) {
        return "Vitaj spat na starte";
    }
}

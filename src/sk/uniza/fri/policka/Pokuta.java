package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Pokuta extends Policko {

    public Pokuta(String nazov) {
        super(nazov);
    }

    public String vykonaj(Hrac hrac) {
        hrac.odoberPeniaze(20000);
        return "Banke si zaplatil pokutu 20 000";
    }
}

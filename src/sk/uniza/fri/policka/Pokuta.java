package sk.uniza.fri.policka;

import sk.uniza.fri.hraci.IHrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Pokuta extends Policko {

    public Pokuta(String nazov) {
        super(nazov);
    }

    public void vykonaj(IHrac hrac) {
        if (hrac == null) {
            return;
        }

        System.out.println("Zaplatil si banke pokutu 20 000");
        hrac.odoberPeniaze(20000);
    }
}

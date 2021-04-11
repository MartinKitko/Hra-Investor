package sk.uniza.fri.policka;

import sk.uniza.fri.hraci.HracClovek;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Vazenie extends Policko {

    public Vazenie(String nazov) {
        super(nazov);
    }

    @Override
    public void vykonaj(HracClovek hracClovek) {
        System.out.println("Stojis jedno kolo");
        hracClovek.doVazenia(1);
    }
}

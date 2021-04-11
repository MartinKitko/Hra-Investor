package sk.uniza.fri.policka;

import sk.uniza.fri.hraci.HracClovek;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Pokuta extends Policko {

    public Pokuta(String nazov) {
        super(nazov);
    }

    @Override
    public void vykonaj(HracClovek hracClovek) {
        if (hracClovek == null) {
            return;
        }

        System.out.println("Zaplatil si banke pokutu 20 000");
        hracClovek.odoberPeniaze(20000);
    }
}

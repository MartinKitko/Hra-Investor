package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;
import sk.uniza.fri.TypHraca;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Relax extends Policko {

    public Relax(String nazov) {
        super(nazov);
    }

    public void vykonaj(Hrac hrac) {
        if (hrac.getTypHraca() == TypHraca.POCITAC) {
            System.out.println("Stojis jedno kolo");
            hrac.doVazenia(1);
            return;
        }

        if (hrac.getPeniaze() > 20000) {
            int volba = hrac.zobrazMoznosti("Chces zaplatit 20 000 aby si nemusel stat jedno kolo?", "Relax", false);

            if (volba == 0) {
                System.out.println("Zaplatil si 20 000");
                hrac.odoberPeniaze(20000);
            } else {
                System.out.println("Stojis jedno kolo");
                hrac.doVazenia(1);
            }
        } else {
            System.out.println("Nemas 20 000 na zaplatenie, stojis jedno kolo");
            hrac.doVazenia(1);
        }
    }
}

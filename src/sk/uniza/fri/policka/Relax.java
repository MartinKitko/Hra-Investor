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

    public String vykonaj(Hrac hrac) {
        String sprava;
        if (hrac.getTypHraca() == TypHraca.POCITAC) {
            hrac.doVazenia(1);
            return "Stojis jedno kolo";
        }

        if (hrac.getPeniaze() > 20000) {
            int volba = hrac.zobrazMoznosti("Chces zaplatit 20 000 aby si nemusel stat jedno kolo?", "Relax", false);

            if (volba == 0) {
                sprava = "Zaplatil si 20 000";
                hrac.odoberPeniaze(20000);
            } else {
                sprava = "Stojis jedno kolo";
                hrac.doVazenia(1);
            }
        } else {
            sprava = "Nemas 20 000 na zaplatenie, stojis jedno kolo";
            hrac.doVazenia(1);
        }
        return sprava;
    }
}

package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;
import sk.uniza.fri.TypHraca;

/**
 * Trieda Relax ktora je potomkom triedy Policko
 *
 * @author Martin Kitko
 * @version 18.5.2021
 */
public class Relax extends Policko {

    /**
     * Konstruktor triedy Relax na vytvorenie policka relax so zadanym nazvom
     * @param nazov nazov policka typu pokuta
     */
    public Relax(String nazov) {
        super(nazov);
    }

    /**
     * Implementacia abstraktnej metody z predka Policko ktora vykona dane akcie
     * podla pravidiel hry po skoceni hracom na policko typu relax
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return vrati spravu typu String na zaklade toho co sa vykonalo
     */
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

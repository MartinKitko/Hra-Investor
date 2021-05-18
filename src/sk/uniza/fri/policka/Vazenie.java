package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * Trieda Vazenie ktora je potomkom triedy Policko
 *
 * @author Martin Kitko
 * @version 18.5.2021
 */
public class Vazenie extends Policko {

    /**
     * Konstruktor triedy Vazenie na vytvorenie policka vazenia so zadanym nazvom
     * @param nazov nazov policka typu vazenie
     */
    public Vazenie(String nazov) {
        super(nazov);
    }

    /**
     * Implementacia abstraktnej metody z predka Policko ktora vykona dane akcie podla pravidiel
     * hry po skoceni hracom na policko typu Vazenie
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return String obsahujuci spravu o uspesnom vykonani tejto metody
     */
    public String vykonaj(Hrac hrac) {
        hrac.doVazenia(1);
        return "Stojis jedno kolo";
    }
}

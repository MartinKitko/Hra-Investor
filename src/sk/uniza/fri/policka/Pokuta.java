package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * Trieda pokuta ktora je potomkom triedy Policko
 *
 * @author Martin Kitko
 * @version 23.5.2021
 */
public class Pokuta extends Policko {

    /**
     * Konstruktor triedy Pokuta na vytvorenie policka pokuty so zadanym nazvom
     * @param nazov nazov policka typu pokuta
     */
    public Pokuta(String nazov) {
        super(nazov);
    }

    /**
     * Implementacia abstraktnej metody z predka Policko ktora vykona dane akcie podla pravidiel
     * hry po skoceni hracom na policko typu pokuta
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return String obsahujuci spravu o uspesnom vykonani tejto metody
     */
    public String vykonaj(Hrac hrac) {
        hrac.odoberPeniaze(20000);
        return "Banke si zaplatil pokutu 20 000";
    }
}

package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * Trieda Policia ktora je potomkom triedy Policko
 *
 * @author Martin Kitko
 * @version 23.5.2021
 */
public class Policia extends Policko {

    /**
     * Konstruktor triedy Policica na vytvorenie policka policie so zadanym nazvom
     * @param nazov nazov policka typu policia
     */
    public Policia(String nazov) {
        super(nazov);
    }

    /**
     * Implementacia abstraktnej metody z predka Policko ktora vykona dane akcie podla pravidiel
     * hry po skoceni hracom na policko typu policia
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return String obsahujuci spravu o uspesnom vykonani tejto metody
     */
    public String vykonaj(Hrac hrac) {
        hrac.nastavPoziciu(12);
        hrac.doVazenia(3);
        return "Bol si presunuty do vazenia, stojis 3 kola";
    }
}

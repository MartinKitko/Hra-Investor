package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * Trieda PolickoStart ktora je potomkom triedy Policko
 *
 * @author Martin Kitko
 * @version 18.5.2021
 */
public class PolickoStart extends Policko {

    /**
     * Konstruktor triedy PolickoStart na vytvorenie policka start so zadanym nazvom
     * @param nazov nazov policka typu polickoStart
     */
    public PolickoStart(String nazov) {
        super(nazov);
    }

    /**
     * Implementacia abstraktnej metody z predka Policko ktora vykona dane akcie podla pravidiel
     * hry po skoceni hracom na policko typu PolickoStart
     * @param hrac konkretny hrac pre ktoreho sa ma vykonat tato metoda
     * @return String sprava ktora hovori o vrateni na start
     */
    public String vykonaj(Hrac hrac) {
        return "Vitaj spat na starte";
    }
}

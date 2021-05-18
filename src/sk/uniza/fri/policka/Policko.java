package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * Trieda Policko ktora je predkom jej potomkov
 *
 * @author Martin Kitko
 * @version 18.5.2021
 */
public abstract class Policko {
    private String nazov;

    /**
     * Konstruktor triedy Policko na vytvorenie policka so zadanym nazvom
     * @param nazov nazov policka
     */
    public Policko(String nazov) {
        this.nazov = nazov;
    }

    /**
     * Metoda ktora vrati nazov policka
     * @return String obsahujuci nazov policka
     */
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Abstraktna metoda pomocou ktorej sa vykonava polymorfizmus
     * @param hrac konkretny hrac pre ktoreho sa ma vykonat tato metoda
     * @return String obsahujuci spravu o uspesnom vykonani tejto metody
     */
    public abstract String vykonaj(Hrac hrac);

    /**
     * toString ktory vrati nazov policka
     * @return nazov policka typu String
     */
    @Override
    public String toString() {
        return this.nazov;
    }
}

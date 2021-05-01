package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public abstract class Policko {
    private String nazov;

    public Policko(String nazov) {
        if (nazov == null) {
            nazov = "";
        }

        this.nazov = nazov;
    }

    public String getNazov() {
        return this.nazov;
    }

    public abstract void vykonaj(Hrac hrac);

    @Override
    public String toString() {
        return this.nazov;
    }
}

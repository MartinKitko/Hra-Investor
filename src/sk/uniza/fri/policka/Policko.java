package sk.uniza.fri.policka;

import sk.uniza.fri.GUI;
import sk.uniza.fri.hraci.IHrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public abstract class Policko {
    private String nazov;
    //private GUI gui;

    public Policko(String nazov) {
        if (nazov == null) {
            nazov = "";
        }

        this.nazov = nazov;
        //this.gui = GUI.getInstancia();
    }

    public String getNazov() {
        return this.nazov;
    }

    public int zobrazMoznosti(String sprava, String nazovOkna, boolean zobrazInfo) {
        return GUI.getInstancia().zobrazMoznosti(sprava, nazovOkna, zobrazInfo);
    }

    public abstract void vykonaj(IHrac hrac);

    @Override
    public String toString() {
        return this.nazov;
    }
}

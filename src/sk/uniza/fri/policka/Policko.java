package sk.uniza.fri.policka;

import sk.uniza.fri.GUI;
import sk.uniza.fri.hraci.IHrac;

import java.util.Scanner;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public abstract class Policko {
    private String nazov;
    private GUI gui;

    public Policko(String nazov) {
        if (nazov == null) {
            nazov = "";
        }

        this.nazov = nazov;
        this.gui = GUI.getInstancia();
    }

    public String getNazov() {
        return this.nazov;
    }

    public void zobrazGUI(String text) {
        this.gui.zobraz(text);
    }

    public abstract void vykonaj(IHrac hrac);

    @Override
    public String toString() {
        return this.nazov;
    }
}

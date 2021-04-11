package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

import java.util.Scanner;

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

    public int zobrazMoznosti() {
        Scanner sc = new Scanner(System.in);
        int volba = 0;
        do {
            if (volba != 0) {
                System.out.println("Zle zadana volba");
            }
            System.out.println("1 - Kupit podnik");
            System.out.println("2 - Dalsie informacie o podniku");
            System.out.println("0 - Ziadna akcia");
            System.out.print("Zadaj svoju volbu: ");
            while (!sc.hasNextInt()) {
                System.out.print("Je potrebne zadat cele cislo: ");
                sc.next();
            }
            volba = sc.nextInt();
        } while (volba < 0 || volba > 2);

        return volba;
    }

    @Override
    public String toString() {
        return this.nazov;
    }
}

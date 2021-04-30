package sk.uniza.fri.hraci;

import sk.uniza.fri.policka.Policko;
import sk.uniza.fri.policka.Preprava;
import sk.uniza.fri.policka.Agentura;
import sk.uniza.fri.policka.Odvetvie;
import sk.uniza.fri.policka.Podnik;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class HracClovek implements IHrac {
    private String meno;
    private int aktPozicia;
    private int peniaze;
    private int zostavajuciPocetKolVoVazeni;
    private boolean prehral;
    private ArrayList<Policko> vlastnenePolicka;

    public HracClovek(String meno) {
        if (meno == null) {
            meno = "";
        }

        this.meno = meno;
        this.aktPozicia = 0;
        this.peniaze = 500000;
        this.zostavajuciPocetKolVoVazeni = 0;
        this.prehral = false;
        this.vlastnenePolicka = new ArrayList<>();
    }

    public void posun() {
        int staraPozicia = this.aktPozicia;
        Random generator = new Random();
        int hodKockou = generator.nextInt(6) + 1;
        if (this.aktPozicia + hodKockou >= 40) {
            this.aktPozicia = (this.aktPozicia + hodKockou) - 40;
            System.out.println("Za prejdenie polickom Start si ziskal 20 000");
            this.peniaze += 20000;
        } else {
            this.aktPozicia += hodKockou;
        }
        System.out.println("\nPosun z " + staraPozicia + " o " + hodKockou + " na " + this.aktPozicia);
    }

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

    public void pridajPolicko(Policko policko) {
        this.vlastnenePolicka.add(policko);
    }

    public int getPocetVlastnenych(Policko policko) {
        int pocet = 0;
        for (Policko p : this.vlastnenePolicka) {
            if (policko instanceof Preprava) {
                if (p instanceof Preprava) {
                    pocet++;
                }
            } else {
                if (p instanceof Agentura) {
                    pocet++;
                }
            }

        }
        return pocet;
    }

    public int getPocetVlastnenychVOdvetvi(Odvetvie odvetvie) {
        int pocet = 0;
        for (Policko p : this.vlastnenePolicka) {
            if (p instanceof Podnik &&
                    ((Podnik)p).getOdvetvie() == odvetvie) {
                pocet++;
            }
        }
        return pocet;
    }

    public String getMeno() {
        return this.meno;
    }

    public int getAktPozicia() {
        return this.aktPozicia;
    }

    public int getPeniaze() {
        return this.peniaze;
    }

    public void nastavPoziciu(int pozicia) {
        this.aktPozicia = pozicia;
    }

    public void pridajPeniaze(int hodnota) {
        this.peniaze += hodnota;
    }

    public void odoberPeniaze(int hodnota) {
        this.peniaze -= hodnota;
    }

    public boolean prehral() {
        return this.peniaze <= 0;
    }

    public boolean jeVoVazeni() {
        return this.zostavajuciPocetKolVoVazeni > 0;
    }

    public void doVazenia(int pocetKol) {
        this.zostavajuciPocetKolVoVazeni += pocetKol;
    }

    public void odsedelSiKolo() {
        this.zostavajuciPocetKolVoVazeni--;
    }

    @Override
    public String toString() {
        return this.meno;
    }

}

package sk.uniza.fri;

import sk.uniza.fri.policka.Policko;
import sk.uniza.fri.policka.Preprava;
import sk.uniza.fri.policka.Agentura;
import sk.uniza.fri.policka.Odvetvie;
import sk.uniza.fri.policka.Podnik;

import java.util.ArrayList;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Hrac {
    private String meno;
    private int aktPozicia;
    private int peniaze;
    private int zostavajuciPocetKolVoVazeni;
    private ArrayList<Policko> vlastnenePolicka;
    private TypHraca typHraca;

    public Hrac(String meno, TypHraca typHraca) {
        this.meno = meno;
        this.aktPozicia = 0;
        this.peniaze = 500000;
        this.zostavajuciPocetKolVoVazeni = 0;
        this.vlastnenePolicka = new ArrayList<>();
        this.typHraca = typHraca;
    }

    public int zobrazMoznosti(String sprava, String nazov, boolean zobrazInfo) {
        if (this.typHraca == TypHraca.CLOVEK) {
            return GUI.getInstancia().zobrazMoznosti(sprava, nazov, zobrazInfo);
        } else {
            if (this.peniaze > 100000) {
                return (Math.random() < 0.9 ? 0 : 1);
            } else {
                return (Math.random() < 0.2 ? 0 : 1);
            }
        }
    }

    public String dajVlastnenePolicka() {
        StringBuilder vlastnene = new StringBuilder();
        vlastnene.append(this.meno).append(" vlastni tieto policka:");
        for (int i = 0; i < this.vlastnenePolicka.size(); i++) {
            vlastnene.append("\n").append(i + 1).append(". ").append(this.vlastnenePolicka.get(i));
        }
        return vlastnene.toString();
    }

    public TypHraca getTypHraca() {
        return this.typHraca;
    }

    public void pridajPolicko(Policko policko) {
        this.vlastnenePolicka.add(policko);
    }

    public int getPocetVlastnenych() {
        int pocet = 0;
        for (Policko p : this.vlastnenePolicka) {
            pocet++;
        }
        return pocet;
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
            if (p instanceof Podnik && ((Podnik)p).getOdvetvie() == odvetvie) {
                pocet++;
            }
        }
        return pocet;
    }

    public String predajPolicko(int cisloPolicka) {
        IPredatelny p = (IPredatelny)this.vlastnenePolicka.get(cisloPolicka - 1);
        if (p.predaj(this)) {
            this.vlastnenePolicka.remove(cisloPolicka - 1);
        }
        return "Uspesne predane";
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

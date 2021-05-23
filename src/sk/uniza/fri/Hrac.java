package sk.uniza.fri;

import sk.uniza.fri.policka.Agentura;
import sk.uniza.fri.policka.IPredatelny;
import sk.uniza.fri.policka.Odvetvie;
import sk.uniza.fri.policka.Podnik;
import sk.uniza.fri.policka.Policko;
import sk.uniza.fri.policka.Preprava;

import java.util.ArrayList;

/**
 * Trieda Hrac ktora predstavuje hraca hry
 *
 * @author Martin Kitko
 * @version 22.5.2021
 */
public class Hrac {
    private String meno;
    private int aktPozicia;
    private int peniaze;
    private int zostavajuciPocetKolVoVazeni;
    private ArrayList<Policko> vlastnenePolicka;
    private TypHraca typHraca;

    /**
     * Konstruktor triedy Hrac na vytvorenie hraca podla hodnot zadanych ako parametre
     * a inicializuje atributy na pociatocny stav pred zacatim hry
     * @param meno meno hraca
     * @param typHraca typ hraca
     */
    public Hrac(String meno, TypHraca typHraca) {
        this.meno = meno;
        this.aktPozicia = 0;
        this.peniaze = 500000;
        this.zostavajuciPocetKolVoVazeni = 0;
        this.vlastnenePolicka = new ArrayList<>();
        this.typHraca = typHraca;
    }

    /**
     * Vrati konkretnu volbu hraca na zaklade hodnot zadanych ako parametre
     * @param sprava sprava ktora sa zobrazi hracovi
     * @param nazov nazov okna ktore sa zobrazi
     * @param zobrazInfo zobrazenie tretieho tlacitka na zobrazenie informacii o podniku
     * @return konkretna celociselna hodnota volby hraca
     */
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

    /**
     * Vrati nazvy vlastnenych polickok hraca
     * @return String obsahujuci nazov policiek ktore hrac vlastni
     */
    public String dajVlastnenePolicka() {
        if (this.vlastnenePolicka.size() == 0) {
            return this.meno + " nevlastni ziadne policka";
        } else {
            StringBuilder vlastnene = new StringBuilder();
            vlastnene.append(this.meno).append(" vlastni tieto policka:");
            for (int i = 0; i < this.vlastnenePolicka.size(); i++) {
                vlastnene.append("\n").append(i + 1).append(". ").append(this.vlastnenePolicka.get(i));
            }
            return vlastnene.toString();
        }
    }

    /**
     * Vrati typ hraca
     * @return typ hraca
     */
    public TypHraca getTypHraca() {
        return this.typHraca;
    }

    /**
     * Prida hracovi vlastnene policko zadane ako parameter
     * @param policko konkretne policko ktore chceme pridat
     */
    public void pridajPolicko(Policko policko) {
        this.vlastnenePolicka.add(policko);
    }

    /**
     * Vrati pocet vlastnenych policok
     * @return pocet vlastnenych policok
     */
    public int getPocetVlastnenych() {
        return this.vlastnenePolicka.size();
    }

    /**
     * Pretazena metoda ktora vrati pocet vlastnenych policok podla typu policka zadaneho ako parameter
     * @param policko policko ktoreho typu chceme zistit pocet
     * @return pocet vlastnenych policok
     */
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

    /**
     * Pretazena metoda ktora vrati pocet vlastnenych policok typu podnik v konkretnom odvetvi zadanom ako parameter
     * @param odvetvie odvetvie v ktorom chceme zistit pocet vlastnenych policok
     * @return pocet vlastnenych policok v danom odvetvi
     */
    public int getPocetVlastnenych(Odvetvie odvetvie) {
        int pocet = 0;
        for (Policko p : this.vlastnenePolicka) {
            if (p instanceof Podnik && ((Podnik)p).getOdvetvie() == odvetvie) {
                pocet++;
            }
        }
        return pocet;
    }

    /**
     * Metoda na predanie konkretneho vlastneneho policka
     * @param cisloPolicka cislo policka ktore chce hrac predat
     * @return String ktory informuje o uspesnom predani policka
     */
    public String predajPolicko(int cisloPolicka) {
        IPredatelny p = (IPredatelny)this.vlastnenePolicka.get(cisloPolicka - 1);
        if (p.predaj(this)) {
            this.vlastnenePolicka.remove(cisloPolicka - 1);
        }
        return "Uspesne predane";
    }

    /**
     * Vrati meno hraca
     * @return meno hraca
     */
    public String getMeno() {
        return this.meno;
    }

    /**
     * Vrati aktualnu poziciu hraca
     * @return aktualna pozicia hraca
     */
    public int getAktPozicia() {
        return this.aktPozicia;
    }

    /**
     * Vrati hodnotu penazi hraca
     * @return hodnota penazi hraca
     */
    public int getPeniaze() {
        return this.peniaze;
    }

    /**
     * Nastavi poziciu hraca podla hodnoty zadanej ako parameter
     * @param pozicia pozicia na ktoru chceme nastavit hraca
     */
    public void nastavPoziciu(int pozicia) {
        this.aktPozicia = pozicia;
    }

    /**
     * Prida hracovi peniaze
     * @param hodnota hodnota penazi ktore chceme hracovi pridat
     */
    public void pridajPeniaze(int hodnota) {
        this.peniaze += hodnota;
    }

    /**
     * Odoberie hracovi peniaze
     * @param hodnota hodnota penazi ktore chceme hracovi odobrat
     */
    public void odoberPeniaze(int hodnota) {
        if (this.peniaze - hodnota <= 0) {
            if (this.vlastnenePolicka.size() > 0) {
                if (this.typHraca == TypHraca.CLOVEK) {
                    System.out.println("\nNemas dostatok penazi, musis predaj nejaky podnik alebo jeho pobocku");
                    System.out.println("Potrebujes " + hodnota + " na zaplatenie");
                    System.out.println(this.dajVlastnenePolicka());
                    GUI.getInstancia().predajPolicka();
                    this.odoberPeniaze(hodnota);
                } else {
                    int nahodneCisloPolicka = (int)(Math.random() * this.vlastnenePolicka.size()) + 1;
                    this.predajPolicko(nahodneCisloPolicka);
                }
            } else {
                System.out.println("Nemas dostatok penazi ani ziaden podnik na predanie, prehral si!");
                this.peniaze -= hodnota;
            }
        } else {
            this.peniaze -= hodnota;
        }
    }

    /**
     * Vrati boolean hodnotu za zaklade toho ci hrac prehral
     * @return prehral - true, neprehral - false
     */
    public boolean prehral() {
        return this.peniaze <= 0;
    }

    /**
     * Vrati boolean hodnotu na zaklade toho ci je hrac vo vazeni
     * @return je vo vazeni - true, nie je - false
     */
    public boolean jeVoVazeni() {
        return this.zostavajuciPocetKolVoVazeni > 0;
    }

    /**
     * Presunie hraca do vazenie na pocet kol zadany ako parameter
     * @param pocetKol pocet kol ktore hrac stravi vo vazeni
     */
    public void doVazenia(int pocetKol) {
        this.zostavajuciPocetKolVoVazeni += pocetKol;
    }

    /**
     * Znizi zostavajuci pocet kol vo vazeni
     */
    public void odsedelSiKolo() {
        this.zostavajuciPocetKolVoVazeni--;
    }

    /**
     * toString ktory vrati meno hraca
     * @return meno hraca
     */
    @Override
    public String toString() {
        return this.meno;
    }

}

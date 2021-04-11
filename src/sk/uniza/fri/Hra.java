package sk.uniza.fri;

import sk.uniza.fri.hraci.HracClovek;
import sk.uniza.fri.hraci.HracPocitac;
import sk.uniza.fri.hraci.IHrac;
import sk.uniza.fri.policka.Policko;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Hra {
    private HraciaPlocha hraciaPlocha;
    private IHrac[] zoznamHracov;
    private int pocetHracov;
    private int pocetTahov;
    private int aktHrac;

    public Hra(int pocetHracov) {
        this.hraciaPlocha = new HraciaPlocha();
        this.zoznamHracov = new IHrac[pocetHracov];


        /*for (int i = 0; i < pocetHracov; i++) {
            this.zoznamHracov[i] = new HracClovek("Hrac " + (i + 1));
        }*/
        /*for (int i = 0; i < pocetHracov - 1; i++) {
            this.zoznamHracov[i] = new HracClovek("Hrac " + (i + 1));
        }*/
        this.zoznamHracov[0] = new HracPocitac("Pocitac 1");
        //this.zoznamHracov[0] = new HracClovek("Hrac 1");
        this.zoznamHracov[1] = new HracPocitac("Pocitac 2");


        this.pocetHracov = pocetHracov;
        this.pocetTahov = 0;
        this.aktHrac = 0;
    }

    public void tah() {
        if (!this.koniecHry()) {
            IHrac hrac = this.zoznamHracov[this.aktHrac];

            hrac.posun();
            Policko aktPolicko = this.hraciaPlocha.getPolicko(hrac.getAktPozicia());
            System.out.println(hrac.getMeno() + " skocil na policko " + aktPolicko);
            System.out.println("Peniaze: " + hrac.getPeniaze());

            this.hraciaPlocha.vykonaj(hrac);
            this.dalsiHrac();
            this.pocetTahov++;
        }
    }

    public void dalsiHrac() {
        if (this.aktHrac + 1 < this.pocetHracov) {
            this.aktHrac++;
        } else {
            this.aktHrac = 0;
        }

        IHrac dalsiHracClovek = this.zoznamHracov[this.aktHrac];
        if (dalsiHracClovek.jeVoVazeni()) {
            dalsiHracClovek.odsedelSiKolo();
            this.dalsiHrac();
        }

        if (dalsiHracClovek.prehral()) {
            this.dalsiHrac();
        }

    }

    public IHrac getAktHrac() {
        return this.zoznamHracov[this.aktHrac];
    }

    public void ulozHru() {
        System.out.println("Zatial nie je mozne ulozit hru");
    }

    public int getPocetTahov() {
        return this.pocetTahov;
    }

    public boolean koniecHry() {
        int pocetHrajucich = 0;
        for (int i = 0; i < this.pocetHracov; i++) {
            if (!this.zoznamHracov[i].prehral()) {
                pocetHrajucich++;
            }
        }

        if (pocetHrajucich <= 1) {
            for (int i = 0; i < this.pocetHracov; i++) {
                if (!this.zoznamHracov[i].prehral()) {
                    System.out.println("Koniec hry");
                    System.out.println(this.zoznamHracov[i] + " je vitaz!");
                    System.out.println("Pocet tahov: " + this.pocetTahov);
                }
            }
            return true;
        }
        return false;
    }
}

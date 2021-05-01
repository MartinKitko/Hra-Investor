package sk.uniza.fri;

import sk.uniza.fri.policka.Policko;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Hra {
    private HraciaPlocha hraciaPlocha;
    private Hrac[] zoznamHracov;
    private int pocetHracov;
    private int pocetTahov;
    private int aktHrac;

    public Hra(int pocetHracov) {
        this.hraciaPlocha = new HraciaPlocha();
        this.zoznamHracov = new Hrac[pocetHracov];

        /*for (int i = 0; i < pocetHracov; i++) {
            this.zoznamHracov[i] = new HracClovek("Hrac " + (i + 1));
            //this.zoznamHracov[i] = new HracPocitac("Pocitac " + (i + 1));
        }*/
        this.zoznamHracov[0] = new Hrac("Hrac " + (0 + 1), TypHraca.CLOVEK);
        this.zoznamHracov[1] = new Hrac("Pocitac " + (1 + 1), TypHraca.POCITAC);
        // TODO spravit aby fungovalo jeden hrac jeden pocitac

        this.pocetHracov = pocetHracov;
        this.pocetTahov = 0;
        this.aktHrac = 0;
    }

    public void tah() {
        if (!this.koniecHry()) {
            Hrac hrac = this.zoznamHracov[this.aktHrac];

            hrac.posun();
            Policko aktPolicko = this.hraciaPlocha.getPolicko(hrac.getAktPozicia());
            System.out.println(hrac.getMeno() + " skocil na policko " + aktPolicko);
            //System.out.println("Peniaze: " + hrac.getPeniaze());

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

        Hrac nasledujuciHrac = this.zoznamHracov[this.aktHrac];
        if (nasledujuciHrac.jeVoVazeni()) {
            nasledujuciHrac.odsedelSiKolo();
            this.dalsiHrac();
        }

        if (nasledujuciHrac.prehral()) {
            this.dalsiHrac();
        }

    }

    public Hrac getAktHrac() {
        return this.zoznamHracov[this.aktHrac];
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
                    System.out.println("\nKoniec hry");
                    System.out.println(this.zoznamHracov[i] + " je vitaz!");
                    System.out.println("Pocet kol: " + this.pocetTahov / this.pocetHracov);
                }
            }
            return true;
        }
        return false;
    }
}

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

    public Hra(int pocetHracov, int pocetPocitacov) {
        this.hraciaPlocha = new HraciaPlocha();
        this.zoznamHracov = new Hrac[pocetHracov];

        for (int i = 0; i < pocetHracov; i++) {
            if (i < pocetPocitacov) {
                this.zoznamHracov[i] = new Hrac("Pocitac " + (i + 1), TypHraca.POCITAC);
            } else {
                this.zoznamHracov[i] = new Hrac("Hrac " + (i + 1 - pocetPocitacov), TypHraca.CLOVEK);
            }
        }

        this.pocetHracov = pocetHracov;
        this.pocetTahov = 0;
        this.aktHrac = 0;

        if (pocetHracov == pocetPocitacov) {
            this.odohrajHru();
        }
    }

    public void odohrajHru() {
        while (!this.koniecHry()) {
            this.tah();
        }
    }

    public void tah() {
        Hrac hrac = this.zoznamHracov[this.aktHrac];

        hrac.posun();
        Policko aktPolicko = this.hraciaPlocha.getPolicko(hrac.getAktPozicia());
        System.out.println(hrac.getMeno() + " skocil na policko " + aktPolicko);
        System.out.println("Peniaze: " + hrac.getPeniaze());

        System.out.println(this.hraciaPlocha.vykonaj(hrac));
        this.dalsiHrac();
        this.pocetTahov++;
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

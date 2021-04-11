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
    private int aktHrac;

    public Hra(int pocetHracov) {
        this.hraciaPlocha = new HraciaPlocha();
        this.zoznamHracov = new Hrac[pocetHracov];
        for (int i = 0; i < pocetHracov; i++) {
            this.zoznamHracov[i] = new Hrac("Hrac " + (i + 1));
        }
        this.pocetHracov = pocetHracov;
        this.aktHrac = 0;
    }

    public void tah() {
        if (!this.koniecHry()) {
            Hrac hrac = this.zoznamHracov[this.aktHrac];

            hrac.posun();
            Policko aktPolicko = this.hraciaPlocha.getPolicko(hrac.getAktPozicia());
            System.out.println(hrac.getMeno() + " skocil na policko " + aktPolicko);
            System.out.println("Peniaze: " + hrac.getPeniaze());

            this.hraciaPlocha.vykonaj(hrac);
            this.dalsiHrac();
        }
    }

    public void dalsiHrac() {
        if (this.aktHrac + 1 < this.pocetHracov) {
            this.aktHrac++;
        } else {
            this.aktHrac = 0;
        }

        Hrac dalsiHrac = this.zoznamHracov[this.aktHrac];
        if (dalsiHrac.jeVoVazeni()) {
            dalsiHrac.odsedelSiKolo();
            this.dalsiHrac();
        }

        if (dalsiHrac.prehral()) {
            this.dalsiHrac();
        }

    }

    public Hrac getAktHrac() {
        return this.zoznamHracov[this.aktHrac];
    }

    public void ulozHru() {
        System.out.println("Zatial nie je mozne ulozit hru");
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
                }
            }
            return true;
        }
        return false;
    }
}

package sk.uniza.fri;

import sk.uniza.fri.hraci.HracClovek;
import sk.uniza.fri.policka.Policko;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Hra {
    private HraciaPlocha hraciaPlocha;
    private HracClovek[] zoznamHracov;
    private int pocetHracov;
    private int aktHrac;

    public Hra(int pocetHracov) {
        this.hraciaPlocha = new HraciaPlocha();
        this.zoznamHracov = new HracClovek[pocetHracov];
        for (int i = 0; i < pocetHracov; i++) {
            this.zoznamHracov[i] = new HracClovek("Hrac " + (i + 1));
        }
        this.pocetHracov = pocetHracov;
        this.aktHrac = 0;
    }

    public void tah() {
        if (!this.koniecHry()) {
            HracClovek hracClovek = this.zoznamHracov[this.aktHrac];

            hracClovek.posun();
            Policko aktPolicko = this.hraciaPlocha.getPolicko(hracClovek.getAktPozicia());
            System.out.println(hracClovek.getMeno() + " skocil na policko " + aktPolicko);
            System.out.println("Peniaze: " + hracClovek.getPeniaze());

            this.hraciaPlocha.vykonaj(hracClovek);
            this.dalsiHrac();
        }
    }

    public void dalsiHrac() {
        if (this.aktHrac + 1 < this.pocetHracov) {
            this.aktHrac++;
        } else {
            this.aktHrac = 0;
        }

        HracClovek dalsiHracClovek = this.zoznamHracov[this.aktHrac];
        if (dalsiHracClovek.jeVoVazeni()) {
            dalsiHracClovek.odsedelSiKolo();
            this.dalsiHrac();
        }

        if (dalsiHracClovek.prehral()) {
            this.dalsiHrac();
        }

    }

    public HracClovek getAktHrac() {
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

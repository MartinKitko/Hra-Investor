package sk.uniza.fri;

import sk.uniza.fri.policka.Policko;

import java.util.Random;

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
            System.out.println("Odohravanie hry...");
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
        this.posunHraca(hrac);

        Policko aktPolicko = this.hraciaPlocha.getPolicko(hrac.getAktPozicia());
        System.out.println(hrac.getMeno() + " skocil na policko " + aktPolicko);
        System.out.println("Peniaze: " + hrac.getPeniaze());

        String sprava = this.hraciaPlocha.vykonaj(hrac);
        if (!sprava.equals("")) {
            System.out.println(sprava);
        }
        this.dalsiHrac();
        this.pocetTahov++;
    }

    public void posunHraca(Hrac hrac) {
        int staraPozicia = hrac.getAktPozicia();
        Random generator = new Random();
        int hodKockou = generator.nextInt(6) + 1;
        int novaPozicia;
        if (staraPozicia + hodKockou >= 40) {
            novaPozicia = (staraPozicia + hodKockou) - 40;
            System.out.println("\nZa prejdenie polickom Start si ziskal 20 000");
            hrac.pridajPeniaze(20000);
        } else {
            novaPozicia = staraPozicia + hodKockou;
        }
        hrac.nastavPoziciu(novaPozicia);
        System.out.println("\nPosun z " + staraPozicia + " o " + hodKockou + " na " + novaPozicia);
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
        if (this.pocetTahov > 9999) {
            return true;
        }

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

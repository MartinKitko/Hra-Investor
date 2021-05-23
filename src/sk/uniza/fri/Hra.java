package sk.uniza.fri;

import sk.uniza.fri.policka.Policko;

import java.util.Random;

/**
 * Trieda Hra ktora riadi samotnu hru
 *
 * @author Martin Kitko
 * @version 22.5.2021
 */
public class Hra {
    private HraciaPlocha hraciaPlocha;
    private Hrac[] zoznamHracov;
    private int pocetHracov;
    private int pocetTahov;
    private int aktHrac;

    /**
     * Konstruktor triedy Hra ktory pripravi hru na spustenie
     * @param pocetHracov pocet hracov ktori budu hrat hru
     * @param pocetPocitacov pocet pocitacov z celkoveho poctu hracov
     */
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

    /**
     * Odohra celu hru
     * Vyuziva sa v pripade ze hru hraju iba pocitace
     */
    public void odohrajHru() {
        while (!this.koniecHry()) {
            this.tah();
        }
    }

    /**
     * Vykona jeden tah, v ktorom sa posunie hrac, vykona sa vsetko potrebne
     * po skoceni na policko a zmeni ktory hrac je prave na tahu
     */
    public void tah() {
        Hrac hrac = this.zoznamHracov[this.aktHrac];
        this.posunHraca(hrac);
        int poziciaHraca = hrac.getAktPozicia();

        Policko aktPolicko = this.hraciaPlocha.getPolicko(poziciaHraca);
        System.out.println(hrac.getMeno() + " skocil na policko " + aktPolicko);
        System.out.println("Peniaze: " + hrac.getPeniaze());

        GUI.getInstancia().presunFigurku(this.aktHrac, poziciaHraca);
        String sprava = this.hraciaPlocha.vykonaj(hrac);
        if (!sprava.equals("")) {
            System.out.println(sprava);
        }
        if (poziciaHraca != hrac.getAktPozicia()) {
            GUI.getInstancia().presunFigurku(this.aktHrac, hrac.getAktPozicia());
        }
        this.dalsiHrac();
        this.pocetTahov++;
    }

    /**
     * Vykona samotny posun hraca zadaneho ako parameter
     * @param hrac hrac ktoreho chceme posunut
     */
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

    /**
     * Zmeni hraca ktory je aktualne na tahu
     */
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

    /**
     * Vrati hraca ktory je aktualne na tahu
     * @return hrac ktory je na tahu
     */
    public Hrac getAktHrac() {
        return this.zoznamHracov[this.aktHrac];
    }

    /**
     * Vrati cislo hraca ktory je aktualne na tahu
     * @return cislo hraca ktory je na tahu
     */
    public int getCisloAktHraca() {
        return this.aktHrac;
    }

    /**
     * Vrati pozicie vsetkych hracov
     * @return pole pozicii vsetkych hracov
     */
    public int[] getPozicieHracov() {
        int[] pozicie = new int[this.pocetHracov];
        for (int i = 0; i < this.pocetHracov; i++) {
            pozicie[i] = this.zoznamHracov[i].getAktPozicia();
        }
        return pozicie;
    }

    /**
     * Vrati pocet hracov
     * @return pocet hracov
     */
    public int getPocetHracov() {
        return this.zoznamHracov.length;
    }

    /**
     * Vrati pocet vykonanych tahov pocas hry
     * @return pocet tahov
     */
    public int getPocetTahov() {
        return this.pocetTahov;
    }

    /**
     * Kontroluje ci nenastal koniec hry
     * @return koniec nastal - true, nenastal - false
     */
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

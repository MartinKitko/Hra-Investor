package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Riziko extends Policko {
    private int aktKarta;

    public Riziko(String nazov) {
        super(nazov);
        this.aktKarta = 1;
    }

    private void dalsiaKarta() {
        if (this.aktKarta + 1 <= 9) {
            this.aktKarta++;
        } else {
            this.aktKarta = 1;
        }
    }

    public String vykonaj(Hrac hrac) {
        String sprava = "";
        switch (this.aktKarta) {
            case 1 -> {
                sprava = "Daroval si peniaze na dobrocinne ucely. Banke si zaplatil 10 000";
                hrac.odoberPeniaze(10000);
            }
            case 2 -> {
                sprava = "Vracias sa na start";
                hrac.nastavPoziciu(0);
            }
            case 3 -> {
                sprava = "Dal si zrenovovat svoje letne sidlo. Banke si zaplatil 40 000";
                hrac.odoberPeniaze(40000);
            }
            case 4 -> {
                sprava = "Priatelovi si vratil staru dlzobu. Banke si zapltil 20 000";
                hrac.odoberPeniaze(20000);
            }
            case 5 -> {
                sprava = "Za investicne poradenstvo si banke zaplatil 10 000";
                hrac.odoberPeniaze(10000);
            }
            case 6 -> sprava = "Nabuduce to bude lepsie";
            case 7 -> {
                sprava = "Za bankove sluzby si banke zaplatil 30 000";
                hrac.odoberPeniaze(30000);
            }
            case 8 -> {
                sprava = "Zmodernizoval si svoje podniky. Banke si zaplatil 20 000";
                hrac.odoberPeniaze(20000);
            }
            case 9 -> {
                sprava = "Kupil si novy trezor. Banke si zaplatil 10 000";
                hrac.odoberPeniaze(10000);
            }
        }

        this.dalsiaKarta();
        return sprava;
    }
}

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

    @Override
    public void vykonaj(Hrac hrac) {
        switch (this.aktKarta) {
            case 1:
                System.out.println("Daroval si peniaze na dobrocinne ucely. Banke si zaplatil 10 000");
                hrac.odoberPeniaze(10000);
                break;
            case 2:
                System.out.println("Vracias sa na start");
                hrac.nastavPoziciu(0);
                break;
            case 3:
                System.out.println("Dal si zrenovovat svoje letne sidlo. Banke si zaplatil 40 000");
                hrac.odoberPeniaze(40000);
                break;
            case 4:
                System.out.println("Priatelovi si vratil staru dlzobu. Banke si zapltil 20 000");
                hrac.odoberPeniaze(20000);
                break;
            case 5:
                System.out.println("Za investicne poradenstvo si banke zaplatil 10 000");
                hrac.odoberPeniaze(10000);
                break;
            case 6:
                System.out.println("Na buduce to bude lepsie");
                break;
            case 7:
                System.out.println("Za bankove sluzby si banke zaplatil 30 000");
                hrac.odoberPeniaze(30000);
                break;
            case 8:
                System.out.println("Zmodernizoval si svoje podniky. Banke si zaplatil 20 000");
                hrac.odoberPeniaze(20000);
                break;
            case 9:
                System.out.println("Kupil si novy trezor. Banke si zaplatil 10 000");
                hrac.odoberPeniaze(10000);
                break;
        }

        this.dalsiaKarta();
    }
}

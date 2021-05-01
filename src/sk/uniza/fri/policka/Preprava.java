package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Preprava extends Policko {
    private static final int CENA = 40000;
    private static final int POPLATOK = 5000;
    private Hrac majitel;

    public Preprava(String nazov) {
        super(nazov);
    }

    public void vykonaj(Hrac hrac) {
        int volba;
        if (this.majitel == null) {
            do {
                volba = hrac.zobrazMoznosti("Chces zakupit tuto prepravu za " + CENA + "?", "Kupa prepravy", true);
                switch (volba) {
                    case 0 -> this.kupa(hrac);
                    case 2 -> this.zobrazInfo();
                }
            } while (volba == 2);
        } else if (this.majitel.equals(hrac)) {
            System.out.println("Vlastnis tuto prepravu");
        } else {
            System.out.println("Tuto prepravu vlastni " + this.majitel.getMeno());
            int poplatok = this.getPoplatok(this.majitel.getPocetVlastnenych(this));
            System.out.println("Zaplatil si mu " + poplatok);
            hrac.odoberPeniaze(poplatok);
            this.majitel.pridajPeniaze(poplatok);
        }
    }

    private void zobrazInfo() {
        System.out.println("Poplatok, pokial hrac vlastni:");
        System.out.println("1 prepravu: " + POPLATOK);
        System.out.println("2 prepravy: " + this.getPoplatok(2));
        System.out.println("3 prepravy: " + this.getPoplatok(3));
        System.out.println("4 prepravy: " + this.getPoplatok(4) + "\n");
    }

    private int getPoplatok(int pocetVlastnenych) {
        return switch (pocetVlastnenych) {
            case 2 -> POPLATOK * 2;
            case 3 -> POPLATOK * 4;
            case 4 -> POPLATOK * 8;
            default -> POPLATOK;
        };
    }

    private void kupa(Hrac hrac) {
        if (hrac.getPeniaze() >= CENA) {
            hrac.pridajPolicko(this);
            hrac.odoberPeniaze(CENA);
            this.majitel = hrac;
            System.out.println("Preprava uspesne zakupena");
        } else {
            System.out.println("Na zakupenie tejto prepravy nemas dostatok penazi");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", cena: " + CENA;
    }
}

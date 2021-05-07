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

    public String vykonaj(Hrac hrac) {
        String sprava = "";
        int volba;
        if (this.majitel == null) {
            do {
                volba = hrac.zobrazMoznosti("Chces zakupit tuto prepravu za " + CENA + "?", "Kupa prepravy", true);
                switch (volba) {
                    case 0 -> sprava = this.kupa(hrac);
                    case 2 -> sprava = this.dajInfo();
                }
            } while (volba == 2);
        } else if (this.majitel.equals(hrac)) {
            sprava = "Vlastnis tuto prepravu";
        } else {
            sprava = "Tuto prepravu vlastni " + this.majitel.getMeno();
            int poplatok = this.getPoplatok(this.majitel.getPocetVlastnenych(this));
            sprava += "\nZaplatil si mu " + poplatok;
            hrac.odoberPeniaze(poplatok);
            this.majitel.pridajPeniaze(poplatok);
        }
        return sprava;
    }

    private String dajInfo() {
        return "Poplatok, pokial hrac vlastni:" + "\n" +
            "1 prepravu: " + POPLATOK + "\n" +
            "2 prepravy: " + this.getPoplatok(2) + "\n" +
            "3 prepravy: " + this.getPoplatok(3) + "\n" +
            "4 prepravy: " + this.getPoplatok(4) + "\n";
    }

    private int getPoplatok(int pocetVlastnenych) {
        return switch (pocetVlastnenych) {
            case 2 -> POPLATOK * 2;
            case 3 -> POPLATOK * 4;
            case 4 -> POPLATOK * 8;
            default -> POPLATOK;
        };
    }

    private String kupa(Hrac hrac) {
        String sprava;
        if (hrac.getPeniaze() >= CENA) {
            hrac.pridajPolicko(this);
            hrac.odoberPeniaze(CENA);
            this.majitel = hrac;
            sprava = "Preprava uspesne zakupena";
        } else {
            sprava = "Na zakupenie tejto prepravy nemas dostatok penazi";
        }
        return sprava;
    }

    @Override
    public String toString() {
        return super.toString() + ", cena: " + CENA;
    }
}

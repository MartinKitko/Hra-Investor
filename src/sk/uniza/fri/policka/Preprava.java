package sk.uniza.fri.policka;

import sk.uniza.fri.HracClovek;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Preprava extends Policko {
    private static final int CENA = 40000;
    private static final int POPLATOK = 5000;
    private HracClovek majitel;


    public Preprava(String nazov) {
        super(nazov);
    }

    @Override
    public void vykonaj(HracClovek hracClovek) {
        int volba;
        if (this.majitel == null) {
            do {
                volba = this.zobrazMoznosti();
                switch (volba) {
                    case 1:
                        this.kupa(hracClovek);
                        break;
                    case 2:
                        System.out.println("Poplatok, pokial hrac vlastni:");
                        System.out.println("1 prepravu: " + POPLATOK);
                        System.out.println("2 prepravy: " + this.getPoplatok(2));
                        System.out.println("3 prepravy: " + this.getPoplatok(3));
                        System.out.println("4 prepravy: " + this.getPoplatok(4) + "\n");
                        break;
                    default:
                }
            } while (volba == 2);
        } else if (this.majitel.equals(hracClovek)) {
            System.out.println("Vlastnis tuto prepravu");
        } else {
            System.out.println("Tuto prepravu vlastni " + this.majitel.getMeno());
            int poplatok = this.getPoplatok(this.majitel.getPocetVlastnenych(this));
            System.out.println("Zaplatil si mu " + poplatok);
            hracClovek.odoberPeniaze(poplatok);
            this.majitel.pridajPeniaze(poplatok);
        }
    }

    private int getPoplatok(int pocetVlastnenych) {
        return switch (pocetVlastnenych) {
            case 2 -> POPLATOK * 2;
            case 3 -> POPLATOK * 4;
            case 4 -> POPLATOK * 8;
            default -> POPLATOK;
        };
    }

    private void kupa(HracClovek hracClovek) {
        if (hracClovek.getPeniaze() >= CENA) {
            hracClovek.pridajPolicko(this);
            hracClovek.odoberPeniaze(CENA);
            this.majitel = hracClovek;
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

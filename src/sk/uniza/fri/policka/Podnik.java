package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

import java.util.Scanner;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Podnik extends Policko {
    private int cena;
    private int zakladnyPoplatok;
    private int pocetPobociek;
    private boolean maKoncern;
    private Odvetvie odvetvie;
    private Hrac majitel;

    public Podnik(String nazov, int cena, int zakladnyPoplatok, Odvetvie odvetvie) {
        super(nazov);

        if (cena < 0) {
            cena = 0;
        }

        if (zakladnyPoplatok < 0) {
            zakladnyPoplatok = 0;
        }

        this.cena = cena;
        this.zakladnyPoplatok = zakladnyPoplatok;
        this.odvetvie = odvetvie;
        this.pocetPobociek = 0;
        this.maKoncern = false;
    }

    public Odvetvie getOdvetvie() {
        return this.odvetvie;
    }

    public Hrac getMajitel() {
        return this.majitel;
    }

    @Override
    public void vykonaj(Hrac hrac) {
        int volba;
        if (this.majitel == null) {
            do {
                volba = this.zobrazMoznosti();
                switch (volba) {
                    case 1:
                        this.kupa(hrac);
                        break;
                    case 2:
                        System.out.println("\t\t\tCENA:  POPLATOK:");
                        System.out.println("Bez pobocky " + this.cena + "\t" + this.zakladnyPoplatok);
                        System.out.println("1 pobocka  \t" + this.cena * 2 + "\t" + this.zakladnyPoplatok * 3);
                        System.out.println("2 pobocky  \t" + this.cena * 3 + "\t" + this.zakladnyPoplatok * 6);
                        System.out.println("3 pobocky  \t" + this.cena * 4 + "\t" + this.zakladnyPoplatok * 9);
                        System.out.println("Koncern    \t" + this.cena * 6 + "\t" + this.zakladnyPoplatok * 25 + "\n");
                        break;
                    default:
                }
            } while (volba == 2);

        } else if (this.majitel.equals(hrac)) {
            // TODO moznost predaja podniku
            this.kupaPobocky();
        } else {
            System.out.println("Tento podnik vlastni " + this.majitel.getMeno());
            int poplatok = this.getPoplatok();
            System.out.println("Zaplatil si mu " + poplatok);
            hrac.odoberPeniaze(poplatok);
            this.majitel.pridajPeniaze(poplatok);
        }

    }

    private void kupa(Hrac hrac) {
        if (hrac.getPeniaze() >= this.cena) {
            hrac.odoberPeniaze(this.cena);
            hrac.pridajPolicko(this);
            this.majitel = hrac;
            System.out.println("Podnik uspesne zakupeny");
        } else {
            System.out.println("Na zakupenie tohto podniku nemas dostatok penazi");
        }
    }

    private void kupaPobocky() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Toto je tvoj podnik");

        int maxPocetPobociek;
        if (this.odvetvie == Odvetvie.ALKOHOL || this.odvetvie == Odvetvie.BANE) {
            maxPocetPobociek = 2;
        } else {
            maxPocetPobociek = 3;
        }

        if (this.majitel.getPocetVlastnenychVOdvetvi(this.odvetvie) == maxPocetPobociek) {
            if (this.pocetPobociek < 3) {
                System.out.print("Chces si zakupit dalsiu pobocku za " + this.cena + "? Ano - 1, nie - 0: ");
                int volba = sc.nextInt();
                if (volba == 1) {
                    this.majitel.odoberPeniaze(this.cena);
                    this.pocetPobociek++;
                    System.out.println("Zakupena 1 pobocka");
                }
            } else {
                if (this.maKoncern) {
                    System.out.println("Vlastnis jeho koncern a vsetky pobocky");
                } else {
                    System.out.print("Zakupit koncern za " + this.cena * 2 + " ? Ano - 1, nie - 0: ");
                    int volba = sc.nextInt();
                    if (volba == 1) {
                        this.majitel.odoberPeniaze(this.cena * 2);
                        this.maKoncern = true;
                        System.out.println("Koncern uspesne zakupeny");
                    }
                }
            }
        } else {
            System.out.println("Pre zakupenie pobocky musis vlastnit vsetky podniky v danom odvetvi");
        }
    }

    public int getPoplatok() {
        if (this.maKoncern) {
            return this.zakladnyPoplatok * 25;
        }

        if (this.pocetPobociek > 0) {
            return this.pocetPobociek * this.zakladnyPoplatok * 3;
        }

        return this.zakladnyPoplatok;
    }

    private void predaj(Hrac hrac) {
        hrac.pridajPeniaze(this.cena);
        this.majitel = null;
    }

    @Override
    public String toString() {
        return super.toString() + ", cena: " + this.cena;
    }

}

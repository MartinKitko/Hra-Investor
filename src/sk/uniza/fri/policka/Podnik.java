package sk.uniza.fri.policka;

import sk.uniza.fri.hraci.HracPocitac;
import sk.uniza.fri.hraci.IHrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Podnik extends Policko {
    private int cena;
    private int zakladnyPoplatok;
    private int poplatokSPobockou;
    private int pocetPobociek;
    private boolean maKoncern;
    private Odvetvie odvetvie;
    private IHrac majitel;

    public Podnik(String nazov, int cena, int zakladnyPoplatok, int poplatokSPobockou, Odvetvie odvetvie) {
        super(nazov);
        this.cena = cena;
        this.zakladnyPoplatok = zakladnyPoplatok;
        this.poplatokSPobockou = poplatokSPobockou;
        this.odvetvie = odvetvie;
        this.pocetPobociek = 0;
        this.maKoncern = false;
    }

    public Odvetvie getOdvetvie() {
        return this.odvetvie;
    }

    public void vykonaj(IHrac hrac) {
        int volba;
        if (this.majitel == null) {
            do {
                //volba = hrac.zobrazMoznosti();
                volba = this.zobrazMoznosti("Chces zakupit tento podnik za " + this.cena + "?", "Kupa podniku", true);
                switch (volba) {
                    case 0:
                        this.kupa(hrac);
                        break;
                    case 2:
                        System.out.println("\tCENA:   POPLATOK:");
                        System.out.println("Bez pobocky\t" + this.cena + "\t" + this.zakladnyPoplatok);
                        System.out.println("1 pobocka  \t" + this.cena + "\t" + this.poplatokSPobockou);
                        System.out.println("2 pobocky  \t" + this.cena * 2 + "\t" + this.poplatokSPobockou * 2);
                        System.out.println("3 pobocky  \t" + this.cena * 3 + "\t" + this.poplatokSPobockou * 3);
                        System.out.println("Koncern    \t" + this.cena * 5 + "\t" + this.getPoplatokSKoncernom() + "\n");
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

    private void kupa(IHrac hrac) {
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
        System.out.println("Toto je tvoj podnik");

        // docasne pre pocitac
        if (this.majitel instanceof HracPocitac) {
            if (this.pocetPobociek < 3) {
                this.majitel.odoberPeniaze(this.cena);
                this.pocetPobociek++;
                System.out.println("Zakupena 1 pobocka");
            } else {
                if (!this.maKoncern) {
                    this.majitel.odoberPeniaze(this.cena * 2);
                    this.maKoncern = true;
                    System.out.println("Koncern zakupeny");
                }
            }
            return;
        }


        int maxPocetPobociek;
        if (this.odvetvie == Odvetvie.ALKOHOL || this.odvetvie == Odvetvie.BANE) {
            maxPocetPobociek = 2;
        } else {
            maxPocetPobociek = 3;
        }

        if (this.majitel.getPocetVlastnenychVOdvetvi(this.odvetvie) == maxPocetPobociek) {
            if (this.pocetPobociek < 3) {
                /*System.out.print("Chces si zakupit dalsiu pobocku za " + this.cena + "? Ano - 1, nie - 0: ");
                int volba = sc.nextInt();
                if (volba == 1) {
                    this.majitel.odoberPeniaze(this.cena);
                    this.pocetPobociek++;
                    System.out.println("Zakupena 1 pobocka");
                }*/
                int volba = this.zobrazMoznosti("Chces zakupit dalsiu pobocku za " + this.cena + "?", "Kupa pobocky", false);
                if (volba == 0) {
                    this.majitel.odoberPeniaze(this.cena);
                    this.pocetPobociek++;
                    System.out.println("Zakupena 1 pobocka");
                }
            } else {
                if (this.maKoncern) {
                    System.out.println("Vlastnis jeho koncern a vsetky pobocky");
                } else {
                    /*System.out.print("Zakupit koncern za " + this.cena * 2 + " ? Ano - 1, nie - 0: ");
                    int volba = sc.nextInt();
                    if (volba == 1) {
                        this.majitel.odoberPeniaze(this.cena * 2);
                        this.maKoncern = true;
                        System.out.println("Koncern uspesne zakupeny");
                    }*/
                    int volba = this.zobrazMoznosti("Chces zakupit koncern za " + this.cena * 2 + "?", "Kupa koncernu", false);
                    if (volba == 0) {
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

    private int getPoplatok() {
        if (this.maKoncern) {
            return this.getPoplatokSKoncernom();
        }

        if (this.pocetPobociek > 0) {
            return this.pocetPobociek * this.poplatokSPobockou;
        }

        return this.zakladnyPoplatok;
    }

    private int getPoplatokSKoncernom() {
        int poplatok = (int)(this.cena * 2.5);
        if (poplatok % 1000 == 500) {
            return poplatok + 500;
        }
        return poplatok;
    }

    private void predaj(IHrac hrac) {
        hrac.pridajPeniaze(this.cena);
        this.majitel = null;
    }

    @Override
    public String toString() {
        return super.toString() + ", cena: " + this.cena;
    }

}

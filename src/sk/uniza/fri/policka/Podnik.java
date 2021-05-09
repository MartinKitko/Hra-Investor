package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Podnik extends Policko implements IPredatelny {
    private int cena;
    private int zakladnyPoplatok;
    private int poplatokSPobockou;
    private int pocetPobociek;
    private boolean maKoncern;
    private Odvetvie odvetvie;
    private Hrac majitel;

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

    public String vykonaj(Hrac hrac) {
        String sprava = "";
        int volba;
        if (this.majitel == null) {
            do {
                volba = hrac.zobrazMoznosti("Chces zakupit tento podnik za " + this.cena + "?", "Kupa podniku", true);
                switch (volba) {
                    case 0:
                        sprava = this.kupa(hrac);
                        break;
                    case 2:
                        this.zobrazInfo();
                        break;
                    default:
                }
            } while (volba == 2);

        } else if (this.majitel.equals(hrac)) {
            sprava = this.kupaPobocky();
        } else {
            System.out.println("Tento podnik vlastni " + this.majitel.getMeno());
            int poplatok = this.getPoplatok();
            sprava += "\nZaplatil si mu " + poplatok;
            hrac.odoberPeniaze(poplatok);
            this.majitel.pridajPeniaze(poplatok);
        }
        return sprava;
    }

    private void zobrazInfo() {
        System.out.println("\tCENA:   POPLATOK:");
        System.out.println("Bez pobocky\t" + this.cena + "\t" + this.zakladnyPoplatok);
        System.out.println("1 pobocka  \t" + this.cena + "\t" + this.poplatokSPobockou);
        System.out.println("2 pobocky  \t" + this.cena * 2 + "\t" + this.poplatokSPobockou * 2);
        System.out.println("3 pobocky  \t" + this.cena * 3 + "\t" + this.poplatokSPobockou * 3);
        System.out.println("Koncern    \t" + this.cena * 5 + "\t" + this.getPoplatokSKoncernom() + "\n");

    }

    private String kupa(Hrac hrac) {
        String sprava;
        if (hrac.getPeniaze() >= this.cena) {
            hrac.odoberPeniaze(this.cena);
            hrac.pridajPolicko(this);
            this.majitel = hrac;
            sprava = "Podnik uspesne zakupeny";
        } else {
            sprava = "Na zakupenie tohto podniku nemas dostatok penazi";
        }
        return sprava;
    }

    private String kupaPobocky() {
        String sprava;
        sprava = "Toto je tvoj podnik";

        int maxPocetPobociek;
        if (this.odvetvie == Odvetvie.ALKOHOL || this.odvetvie == Odvetvie.BANE) {
            maxPocetPobociek = 2;
        } else {
            maxPocetPobociek = 3;
        }

        if (this.majitel.getPocetVlastnenychVOdvetvi(this.odvetvie) == maxPocetPobociek) {
            if (this.pocetPobociek < 3) {
                if (this.majitel.getPeniaze() < this.cena) {
                    sprava += "\nNemas dostatok penazi na zakupenie pobocky";
                } else {
                    int volba = this.majitel.zobrazMoznosti("Chces zakupit dalsiu pobocku za " + this.cena + "?", "Kupa pobocky", true);

                    if (volba == 0) {
                        this.majitel.odoberPeniaze(this.cena);
                        this.pocetPobociek++;
                        sprava += "\nZakupena 1 pobocka";
                    } else if (volba == 2) {
                        this.zobrazInfo();
                    }
                }
            } else {
                if (this.maKoncern) {
                    sprava += "\nVlastnis jeho koncern a vsetky pobocky";
                } else {
                    if (this.majitel.getPeniaze() < this.cena * 2) {
                        sprava += "\nNemas dostatok penazi na zakupenie koncernu";
                    } else {
                        int volba = this.majitel.zobrazMoznosti("Chces zakupit koncern za " + this.cena * 2 + "?", "Kupa koncernu", true);

                        if (volba == 0) {
                            this.majitel.odoberPeniaze(this.cena * 2);
                            this.maKoncern = true;
                            sprava += "\nKoncern uspesne zakupeny";
                        } else if (volba == 2) {
                            this.zobrazInfo();
                        }
                    }
                }
            }
        } else {
            sprava += "\nPre zakupenie pobocky musis vlastnit vsetky podniky v danom odvetvi";
        }
        return sprava;
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

    public boolean predaj(Hrac hrac) {
        if (this.pocetPobociek == 0) {
            hrac.pridajPeniaze(this.cena);
            this.majitel = null;
            return true;
        } else if (this.maKoncern) {
            int volba = hrac.zobrazMoznosti("Tento podnik ma koncern, chces ho predat za " + this.cena * 2 + "?", "Predaj koncernu", false);
            if (volba == 1) {
                this.maKoncern = false;
                hrac.pridajPeniaze(this.cena * 2);
            }
        } else {
            int volba = hrac.zobrazMoznosti("Tento podnik ma " + this.pocetPobociek + " pobocku/pobocky, chces jednu predat za " + this.cena + "?",
                    "Predaj pobocky", false);
            if (volba == 1) {
                this.pocetPobociek--;
                hrac.pridajPeniaze(this.cena);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + ", cena: " + this.cena;
    }

}

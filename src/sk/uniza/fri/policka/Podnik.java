package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * Trieda Podnik ktora je potomkom triedy Policko a implementuje interface IPredatelny
 *
 * @author Martin Kitko
 * @version 18.5.2021
 */
public class Podnik extends Policko implements IPredatelny {
    private int cena;
    private int zakladnyPoplatok;
    private int poplatokSPobockou;
    private int pocetPobociek;
    private boolean maKoncern;
    private Odvetvie odvetvie;
    private Hrac majitel;

    /**
     * Konstruktor triedy Podnik na vytvorenie policka podniku so zadanymi parametrami
     * @param nazov nazov podniku (policka)
     * @param cena zakladna kupna cena podniku
     * @param zakladnyPoplatok zakladny poplatok ktory musi zaplatit hrac ktori skoci na toto policko
     * @param poplatokSPobockou poplatok pokial je na tomto podniku zakupena pobocka
     * @param odvetvie odvetvie podniku
     */
    public Podnik(String nazov, int cena, int zakladnyPoplatok, int poplatokSPobockou, Odvetvie odvetvie) {
        super(nazov);
        this.cena = cena;
        this.zakladnyPoplatok = zakladnyPoplatok;
        this.poplatokSPobockou = poplatokSPobockou;
        this.odvetvie = odvetvie;
        this.pocetPobociek = 0;
        this.maKoncern = false;
    }

    /**
     * Vrati odvetvie podniku
     * @return odvetvie tohto podniku
     */
    public Odvetvie getOdvetvie() {
        return this.odvetvie;
    }

    /**
     * Implementacia abstraktnej metody z predka Policko ktora vykona dane akcie
     * podla pravidiel hry po skoceni hracom na policko typu podnik
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return vrati spravu typu String na zaklade toho co sa vykonalo
     */
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
            if (this.majitel.jeVoVazeni()) {
                System.out.println("Hrac je vo vazeni, neplatis mu ziaden poplatok");
            } else {
                int poplatok = this.getPoplatok();
                sprava += "\nZaplatil si mu " + poplatok;
                hrac.odoberPeniaze(poplatok);
                this.majitel.pridajPeniaze(poplatok);
            }
        }
        return sprava;
    }

    /**
     * Vypise na obrazovku poplatky pri vlastneni rozneho poctu pobociek
     */
    private void zobrazInfo() {
        System.out.println("\tCENA:   POPLATOK:");
        System.out.println("Bez pobocky\t" + this.cena + "\t" + this.zakladnyPoplatok);
        System.out.println("1 pobocka  \t" + this.cena + "\t" + this.poplatokSPobockou);
        System.out.println("2 pobocky  \t" + this.cena * 2 + "\t" + this.poplatokSPobockou * 2);
        System.out.println("3 pobocky  \t" + this.cena * 3 + "\t" + this.poplatokSPobockou * 3);
        System.out.println("Koncern    \t" + this.cena * 5 + "\t" + this.getPoplatokSKoncernom() + "\n");

    }

    /**
     * Vykonava kupu podniku
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return vrati spravu typu String na zaklade toho co sa vykonalo
     */
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

    /**
     * Vykonava kupu pobocky
     * @return vrati spravu typu String na zaklade toho co sa vykonalo
     */
    private String kupaPobocky() {
        String sprava;
        sprava = "Toto je tvoj podnik";

        int maxPocet;
        if (this.odvetvie == Odvetvie.ALKOHOL || this.odvetvie == Odvetvie.BANE) {
            maxPocet = 2;
        } else {
            maxPocet = 3;
        }

        if (this.majitel.getPocetVlastnenychVOdvetvi(this.odvetvie) == maxPocet) {
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

    /**
     * Vrati konkretnu hodnotu poplatku podla poctu vlastnenych pobociek alebo koncernu
     * @return konkretna hodnota poplatku
     */
    private int getPoplatok() {
        if (this.maKoncern) {
            return this.getPoplatokSKoncernom();
        }

        if (this.pocetPobociek > 0) {
            return this.pocetPobociek * this.poplatokSPobockou;
        }

        return this.zakladnyPoplatok;
    }

    /**
     * Vypocita a vrati hodnotu poplatku pokial ma tento podnik zakupeny koncern
     * @return konkretna hodnota poplatku
     */
    private int getPoplatokSKoncernom() {
        int poplatok = (int)(this.cena * 2.5);
        if (poplatok % 1000 == 500) {
            return poplatok + 500;
        }
        return poplatok;
    }

    /**
     * Metoda z interface IPredatelny ktora vykona predaj podniku
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return hodnota boolean na zaklade toho ci sa predal podnik (true) alebo iba pobocka (false)
     */
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

    /**
     * toString ktory vrati zakladne informacie o podniku
     * @return String v ktorom je nazov podniku a jeho cena
     */
    @Override
    public String toString() {
        return super.toString() + ", cena: " + this.cena;
    }

}

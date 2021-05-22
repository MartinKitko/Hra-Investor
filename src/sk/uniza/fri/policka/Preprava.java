package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * Trieda Preprava ktora je potomkom triedy Policko a implementuje interface IPredatelny
 *
 * @author Martin Kitko
 * @version 18.5.2021
 */
public class Preprava extends Policko implements IPredatelny {
    private static final int CENA = 40000;
    private static final int POPLATOK = 5000;
    private Hrac majitel;

    /**
     * Konstruktor triedy Preprava na vytvorenie policka prepravy so zadanym nazvom
     * @param nazov nazov prepravy (policka)
     */
    public Preprava(String nazov) {
        super(nazov);
    }

    /**
     * Implementacia abstraktnej metody z predka Policko ktora vykona dane akcie
     * podla pravidiel hry po skoceni hracom na policko typu preprava
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return vrati spravu typu String na zaklade toho co sa vykonalo
     */
    public String vykonaj(Hrac hrac) {
        String sprava = "";
        int volba;
        if (this.majitel == null) {
            do {
                volba = hrac.zobrazMoznosti("Chces zakupit tuto prepravu za " + CENA + "?", "Kupa prepravy", true);
                switch (volba) {
                    case 0 -> sprava = this.kupa(hrac);
                    case 2 -> this.zobrazInfo();
                }
            } while (volba == 2);
        } else if (this.majitel.equals(hrac)) {
            sprava = "Vlastnis tuto prepravu";
        } else {
            System.out.println("Tuto prepravu vlastni " + this.majitel.getMeno());
            if (this.majitel.jeVoVazeni()) {
                sprava = "Hrac je vo vazeni, neplatis mu ziaden poplatok";
            } else {
                int poplatok = this.getPoplatok(this.majitel.getPocetVlastnenych(this));
                sprava = "Zaplatil si mu " + poplatok;
                hrac.odoberPeniaze(poplatok);
                this.majitel.pridajPeniaze(poplatok);
            }
        }
        return sprava;
    }

    /**
     * Metoda z interface IPredatelny ktora vykona predaj prepravy
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return v tejto triede vzdy vrati true
     */
    public boolean predaj(Hrac hrac) {
        hrac.pridajPeniaze(CENA);
        this.majitel = null;
        return true;
    }

    /**
     * Vypise na obrazovku poplatky pri vlastneni rozneho poctu preprav
     */
    private void zobrazInfo() {
        System.out.println("Poplatok, pokial hrac vlastni:");
        System.out.println("1 prepravu: " + POPLATOK);
        System.out.println("2 prepravy: " + this.getPoplatok(2));
        System.out.println("3 prepravy: " + this.getPoplatok(3));
        System.out.println("4 prepravy: " + this.getPoplatok(4) + "\n");
    }

    /**
     * Vrati konkretnu hodnotu poplatku podla poctu vlastnenych preprav
     * @param pocetVlastnenych pocet vlastnenych preprav danym hracom
     * @return vrati dany poplatok
     */
    private int getPoplatok(int pocetVlastnenych) {
        return switch (pocetVlastnenych) {
            case 2 -> POPLATOK * 2;
            case 3 -> POPLATOK * 4;
            case 4 -> POPLATOK * 8;
            default -> POPLATOK;
        };
    }

    /**
     * Vykonava kupu agentury
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return vrati spravu typu String na zaklade toho co sa vykonalo
     */
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

    /**
     * toString ktory vrati zakladne infromacie o preprave
     * @return String v ktorom je nazov prepravy a jej cena
     */
    @Override
    public String toString() {
        return super.toString() + ", cena: " + CENA;
    }
}

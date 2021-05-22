package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * Trieda Agentura ktora je potomkom triedy Policko a implementuje interface IPredatelny
 * @author Martin Kitko
 * @version 18.5.2021
 */
public class Agentura extends Policko implements IPredatelny {
    private static final int CENA = 30000;
    private static final int POPLATOK = 15000;
    private Hrac majitel;

    /**
     * Konstruktor triedy Agentura na vytvorenie policka agentury so zadanym nazvom
     * @param nazov nazov agentury (policka)
     */
    public Agentura(String nazov) {
        super(nazov);
    }

    /**
     * Implementacia abstraktnej metody z predka Policko ktora vykona dane akcie
     * podla pravidiel hry po skoceni hracom na policko typu agentura
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return vrati spravu typu String na zaklade toho co sa vykonalo
     */
    public String vykonaj(Hrac hrac) {
        String sprava = "";
        int volba;
        if (this.majitel == null) {
            do {
                volba = hrac.zobrazMoznosti("Chces zakupit tuto agenturu za " + CENA + "?", "Kupa agentury", true);
                switch (volba) {
                    case 0 -> sprava = this.kupa(hrac);
                    case 2 -> this.zobrazInfo();
                }
            } while (volba == 2);
        } else if (this.majitel.equals(hrac)) {
            sprava = "Vlastnis tuto agenturu";
        } else {
            System.out.println("Tuto agenturu vlastni " + this.majitel.getMeno());
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
     * Metoda z interface IPredatelny ktora vykona predaj agentury
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return v tejto triede vzdy vrati true
     */
    public boolean predaj(Hrac hrac) {
        hrac.pridajPeniaze(CENA);
        this.majitel = null;
        return true;
    }

    /**
     * Vypise na obrazovku poplatky pri vlastneni jednej alebo dvoch agentur
     */
    private void zobrazInfo() {
        System.out.println("Poplatok, pokial hrac vlastni:");
        System.out.println("1 agenturu: " + POPLATOK);
        System.out.println("2 agentury: " + POPLATOK * 2 + "\n");
    }

    /**
     * Vrati konkretnu hodnotu poplatku podla poctu vlastnenych agentur
     * @param pocetVlastnenych pocet vlastnenych angetur danym hracom
     * @return vrati dany poplatok
     */
    private int getPoplatok(int pocetVlastnenych) {
        if (pocetVlastnenych == 1) {
            return POPLATOK;
        } else {
            return POPLATOK * 2;
        }
    }

    /**
     * Vykonava kupu agentury
     * @param hrac konkretny hrac pre ktoreho sa vykonava tato metoda
     * @return vrati spravu typu String na zaklade toho co sa vykonalo
     */
    private String kupa(Hrac hrac) {
        String sprava;
        if (hrac.getPeniaze() >= CENA) {
            hrac.odoberPeniaze(CENA);
            hrac.pridajPolicko(this);
            this.majitel = hrac;
            sprava = "Agentura uspesne zakupena";
        } else {
            sprava = "Na zakupenie tejto agentury nemas dostatok penazi";
        }
        return sprava;
    }

    /**
     * toString ktory vrati zakladne infromacie o agenture
     * @return String v ktorom je nazov agentury a jej cena
     */
    @Override
    public String toString() {
        return super.toString() + ", cena: " + CENA;
    }
}

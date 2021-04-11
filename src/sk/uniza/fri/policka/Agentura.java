package sk.uniza.fri.policka;

import sk.uniza.fri.hraci.IHrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Agentura extends Policko {
    private static final int CENA = 30000;
    private static final int POPLATOK = 15000;
    private IHrac majitel;


    public Agentura(String nazov) {
        super(nazov);
    }

    @Override
    public void vykonaj(IHrac hrac) {
        int volba;
        if (this.majitel == null) {
            do {
                volba = this.zobrazMoznosti();
                switch (volba) {
                    case 1:
                        this.kupa(hrac);
                        break;
                    case 2:
                        System.out.println("Poplatok, pokial hrac vlastni:");
                        System.out.println("1 agenturu: " + POPLATOK);
                        System.out.println("2 agentury: " + POPLATOK * 2 + "\n");
                        break;
                    default:
                }
            } while (volba == 2);
        } else if (this.majitel.equals(hrac)) {
            System.out.println("Vlastnis tuto agenturu");
        } else {
            System.out.println("Tuto agenturu vlastni " + this.majitel.getMeno());
            int poplatok = this.getPoplatok(this.majitel.getPocetVlastnenych(this));
            System.out.println("Zaplatil si mu " + poplatok);
            hrac.odoberPeniaze(poplatok);
            this.majitel.pridajPeniaze(poplatok);
        }
    }

    private int getPoplatok(int pocetVlastnenych) {
        if (pocetVlastnenych == 1) {
            return POPLATOK;
        } else {
            return POPLATOK * 2;
        }
    }

    private void kupa(IHrac hrac) {
        if (hrac.getPeniaze() >= CENA) {
            hrac.odoberPeniaze(CENA);
            hrac.pridajPolicko(this);
            this.majitel = hrac;
            System.out.println("Agentura uspesne zakupena");
        } else {
            System.out.println("Na zakupenie tejto agentury nemas dostatok penazi");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", cena: " + CENA;
    }
}

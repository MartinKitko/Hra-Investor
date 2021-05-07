package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Zisk extends Policko {
    private int aktKarta;

    public Zisk(String nazov) {
        super(nazov);
        this.aktKarta = 1;
    }

    private void dalsiaKrata() {
        if (this.aktKarta + 1 <= 9) {
            this.aktKarta++;
        } else {
            this.aktKarta = 1;
        }
    }

    public String vykonaj(Hrac hrac) {
        String sprava = "";
        switch (this.aktKarta) {
            case 1 -> {
                sprava = "Stryko ti daroval peniaze. Z banky dostanes 20 000";
                hrac.pridajPeniaze(20000);
            }
            case 2 -> {
                sprava = "Vyhral si v sutazi o najlepsieho investora. Z banky dostanes 50 000";
                hrac.pridajPeniaze(50000);
            }
            case 3 -> {
                sprava = "Banky investovali do tvojich podnikov. Z banky dostanec 100 000";
                hrac.pridajPeniaze(100000);
            }
            case 4 -> {
                sprava = "Na vystavbu tvojich podnikov z banky dostanes 50 000";
                hrac.pridajPeniaze(50000);
            }
            case 5 -> sprava = "Vela stastia v dalsej hre";
            case 6 -> {
                sprava = "Tvojim podnikom sa darí. Z banky dostanes 40 000";
                hrac.pridajPeniaze(40000);
            }
            case 7 -> {
                sprava = "Priatel ti poslal peniaze. Z banky dostanes 10 000";
                hrac.pridajPeniaze(10000);
            }
            case 8 -> {
                sprava = "Rodicia ti poslali peniaze. Z banky dostanes 30 000";
                hrac.pridajPeniaze(30000);
            }
            case 9 -> {
                sprava = "Ceny akcii tvojich podnikov prudko vzrastli. Z banky dostanes 40 000";
                hrac.pridajPeniaze(40000);
            }
        }

        this.dalsiaKrata();
        return sprava;
    }

}

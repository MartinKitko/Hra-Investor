package sk.uniza.fri.policka;

import sk.uniza.fri.hraci.IHrac;

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

    @Override
    public void vykonaj(IHrac hrac) {
        switch (this.aktKarta) {
            case 1:
                System.out.println("Stryko ti daroval peniaze. Z banky dostanes 20 000");
                hrac.pridajPeniaze(20000);
                break;
            case 2:
                System.out.println("Vyhral si v sutazi o najlepsieho investora. Z banky dostanes 50 000");
                hrac.pridajPeniaze(50000);
                break;
            case 3:
                System.out.println("Banky investovali do tvojich podnikov. Z banky dostanec 100 000");
                hrac.pridajPeniaze(100000);
                break;
            case 4:
                System.out.println("Na vystavbu tvojich podnikov z banky dostanes 50 000");
                hrac.pridajPeniaze(50000);
                break;
            case 5:
                System.out.println("Vela stastia v dalsej hre");
                break;
            case 6:
                System.out.println("Tvojim podnikom sa darí. Z banky dostanes 40 000");
                hrac.pridajPeniaze(40000);
                break;
            case 7:
                System.out.println("Priatel ti poslal peniaze. Z banky dostanes 10 000");
                hrac.pridajPeniaze(10000);
                break;
            case 8:
                System.out.println("Rodicia ti poslali peniaze. Z banky dostanes 30 000");
                hrac.pridajPeniaze(30000);
                break;
            case 9:
                System.out.println("Ceny akcii tvojich podnikov prudko vzrastli. Z banky dostanes 40 000");
                hrac.pridajPeniaze(40000);
                break;
        }

        this.dalsiaKrata();
    }

}

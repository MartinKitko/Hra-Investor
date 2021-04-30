package sk.uniza.fri;

import sk.uniza.fri.hraci.HracClovek;
import sk.uniza.fri.hraci.IHrac;
import sk.uniza.fri.policka.Policko;
import sk.uniza.fri.policka.PolickoStart;
import sk.uniza.fri.policka.Podnik;
import sk.uniza.fri.policka.Riziko;
import sk.uniza.fri.policka.Preprava;
import sk.uniza.fri.policka.Zisk;
import sk.uniza.fri.policka.Agentura;
import sk.uniza.fri.policka.Odvetvie;
import sk.uniza.fri.policka.Pokuta;
import sk.uniza.fri.policka.Vazenie;
import sk.uniza.fri.policka.Relax;
import sk.uniza.fri.policka.Policia;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class HraciaPlocha {
    private Policko[] zoznamPolicok;

    public HraciaPlocha() {
        this.zoznamPolicok = new Policko[40];
        this.zoznamPolicok[0] = new PolickoStart("Start");
        this.zoznamPolicok[1] = new Podnik("Liehovary", 12000, 1000, 4000, Odvetvie.ALKOHOL);
        this.zoznamPolicok[2] = new Riziko("Riziko");
        this.zoznamPolicok[3] = new Podnik("Pivovary", 12000, 1000, 4000, Odvetvie.ALKOHOL);
        this.zoznamPolicok[4] = new Podnik("Textilne zavody", 20000, 2000, 6000, Odvetvie.TEXTIL);
        this.zoznamPolicok[5] = new Podnik("Obuvnicke zavody", 20000, 2000, 6000, Odvetvie.TEXTIL);
        this.zoznamPolicok[6] = new Preprava("Cestna preprava");
        this.zoznamPolicok[7] = new Podnik("Odevne zavody", 22000, 2000, 7000, Odvetvie.TEXTIL);
        this.zoznamPolicok[8] = new Podnik("Cementarne", 30000, 3000, 9000, Odvetvie.STAVEBNICTVO);
        this.zoznamPolicok[9] = new Podnik("Tehelne", 28000, 3000, 8000, Odvetvie.STAVEBNICTVO);
        this.zoznamPolicok[10] = new Zisk("Zisk");
        this.zoznamPolicok[11] = new Podnik("Vapenky", 28000, 3000, 8000, Odvetvie.STAVEBNICTVO);
        this.zoznamPolicok[12] = new Vazenie("Vazenie");
        this.zoznamPolicok[13] = new Podnik("Plynarne", 36000, 4000, 11000, Odvetvie.PVE);
        this.zoznamPolicok[14] = new Podnik("Vodarne", 36000, 4000, 11000, Odvetvie.PVE);
        this.zoznamPolicok[15] = new Podnik("Pekarne", 48000, 5000, 15000, Odvetvie.JEDLO);
        this.zoznamPolicok[16] = new Preprava("Lodna preprava");
        this.zoznamPolicok[17] = new Podnik("Elektrarne", 39000, 4000, 12000, Odvetvie.PVE);
        this.zoznamPolicok[18] = new Podnik("Konzervarne", 45000, 5000, 14000, Odvetvie.JEDLO);
        this.zoznamPolicok[19] = new Podnik("Mraziarne", 45000, 5000, 14000, Odvetvie.JEDLO);
        this.zoznamPolicok[20] = new Relax("Relax");
        this.zoznamPolicok[21] = new Podnik("Zbrojarske zavody", 54000, 5000, 16000, Odvetvie.STROJARENSTVO);
        this.zoznamPolicok[22] = new Riziko("Riziko");
        this.zoznamPolicok[23] = new Pokuta("Pokuta");
        this.zoznamPolicok[24] = new Podnik("Automobilove zavody", 50000, 5000, 15000, Odvetvie.STROJARENSTVO);
        this.zoznamPolicok[25] = new Podnik("Strojarne", 50000, 5000, 15000, Odvetvie.STROJARENSTVO);
        this.zoznamPolicok[26] = new Preprava("Zeleznicna preprava");
        this.zoznamPolicok[27] = new Podnik("Rafinerie ropy", 59000, 6000, 18000, Odvetvie.RGS);
        this.zoznamPolicok[28] = new Podnik("Gumarne", 59000, 6000, 18000, Odvetvie.RGS);
        this.zoznamPolicok[29] = new Agentura("Informacna agentura");
        this.zoznamPolicok[30] = new Zisk("Zisk");
        this.zoznamPolicok[31] = new Podnik("Sklarne", 63000, 6000, 19000, Odvetvie.RGS);
        this.zoznamPolicok[32] = new Policia("Policia");
        this.zoznamPolicok[33] = new Podnik("Hlinikarne", 69000, 7000, 21000, Odvetvie.KOVY);
        this.zoznamPolicok[34] = new Podnik("Zeleziarne", 74000, 7000, 22000, Odvetvie.KOVY);
        this.zoznamPolicok[35] = new Podnik("Oceliarne", 69000, 7000, 21000, Odvetvie.KOVY);
        this.zoznamPolicok[36] = new Preprava("Letecka preprava");
        this.zoznamPolicok[37] = new Podnik("Bane na zlato", 85000, 9000, 26000, Odvetvie.BANE);
        this.zoznamPolicok[38] = new Agentura("Reklamna agentura");
        this.zoznamPolicok[39] = new Podnik("Bane na diamanty", 90000, 9000, 81000, Odvetvie.BANE);

    }

    public Policko getPolicko(int index) {
        return this.zoznamPolicok[index];
    }

    public void vykonaj(IHrac hrac) {
        this.zoznamPolicok[hrac.getAktPozicia()].vykonaj(hrac); //polymorfizmus
    }

}

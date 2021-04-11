package sk.uniza.fri.hraci;

import sk.uniza.fri.policka.Odvetvie;
import sk.uniza.fri.policka.Policko;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public interface IHrac {
    void posun();
    void pridajPolicko(Policko policko);
    int getPocetVlastnenych(Policko policko);
    int getPocetVlastnenychVOdvetvi(Odvetvie odvetvie);
    String getMeno();
    int getAktPozicia();
    int getPeniaze();
    void nastavPoziciu(int pozicia);
    void pridajPeniaze(int hodnota);
    void odoberPeniaze(int hodnota);
    boolean prehral();
    boolean jeVoVazeni();
    void doVazenia(int pocetKol);
    void odsedelSiKolo();

}

package sk.uniza.fri.policka;

import sk.uniza.fri.Hrac;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Policia extends Policko {

    public Policia(String nazov) {
        super(nazov);
    }

    public String vykonaj(Hrac hrac) {
        hrac.nastavPoziciu(12);
        hrac.doVazenia(3);
        return "Bol si presunuty do vazenia, stojis 3 kola";
    }
}

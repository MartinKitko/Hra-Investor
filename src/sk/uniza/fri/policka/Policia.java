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

    public void vykonaj(Hrac hrac) {
        System.out.println("Bol si presunuty do vazenia, stojis 3 kola");
        hrac.nastavPoziciu(12);
        hrac.doVazenia(3);
    }
}

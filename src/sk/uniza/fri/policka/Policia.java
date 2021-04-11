package sk.uniza.fri.policka;

import sk.uniza.fri.HracClovek;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Policia extends Policko {

    public Policia(String nazov) {
        super(nazov);
    }

    @Override
    public void vykonaj(HracClovek hracClovek) {
        System.out.println("Bol si presunuty do vazenia, stojis 3 kola");
        hracClovek.nastavPoziciu(12);
        hracClovek.doVazenia(3);
    }
}

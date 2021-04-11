package sk.uniza.fri.policka;

import sk.uniza.fri.HracClovek;

import java.util.Scanner;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class Relax extends Policko {

    public Relax(String nazov) {
        super(nazov);
    }

    @Override
    public void vykonaj(HracClovek hracClovek) {
        Scanner sc = new Scanner(System.in);
        if (hracClovek.getPeniaze() > 20000) {
            System.out.println("Zaplat 20 000 alebo pockaj jedno kolo:");
            System.out.println("1 - zaplatim");
            System.out.println("2 - pockam");
            System.out.print("Zadaj svoju volbu: ");
            int volba = sc.nextInt();
            if (volba == 1) {
                System.out.println("Zaplatil si 20 000");
                hracClovek.odoberPeniaze(20000);
            } else {
                System.out.println("Stojis jedno kolo");
                hracClovek.doVazenia(1);
            }
        } else {
            System.out.println("Nemas 20 000 na zaplatenie, stojis jedno kolo");
            hracClovek.doVazenia(1);
        }
    }
}

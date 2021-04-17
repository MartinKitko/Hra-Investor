package sk.uniza.fri;

import sk.uniza.fri.hraci.HracPocitac;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Kitko
 * Date: 1. 4. 2021
 * Time: 17:09
 */
public abstract class HraUI {
    private static Scanner sc;
    private static Hra hra;

    public static void main(String[] args) {
        new GUI();

        /*HraUI.sc = new Scanner(System.in);
        System.out.println("Vitaj v hre Investor");

        int volba;
        do {
            volba = HraUI.hlavneMenu();
            switch (volba) {
                case 1 -> HraUI.novaHra();
                case 2 -> HraUI.nacitajHru();
                case 0 -> System.out.println("Koniec hry");
                default -> System.out.println("Zle zadana moznost");
            }
        } while (volba < 0 || volba > 2);

        while (volba != 0 && !hra.koniecHry()) {
            volba = HraUI.druheMenu();
        }

        System.out.println("Koniec hry");*/

        //HraUI.experiment();
    }

    private static int hlavneMenu() {
        System.out.println("-----------------------");
        System.out.println("Hlavne menu:");
        System.out.println("1 - Nova hra");
        System.out.println("2 - Nacitat hru");
        System.out.println("0 - Koniec");
        System.out.println("-----------------------");
        System.out.print("Zadaj svoju volbu: ");
        while (!sc.hasNextInt()) {
            System.out.print("Je potrebne zadat cislo: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static int druheMenu() {
        if (HraUI.hra.getAktHrac() instanceof HracPocitac) {
            if (!hra.koniecHry()) {
                hra.tah();
                return 1;
            }
        }

        System.out.println("\n" + HraUI.hra.getAktHrac() + " je na tahu");
        System.out.println("1 - Hod kockou");
        System.out.println("2 - Ulozit hru");
        System.out.println("0 - Koniec hry");
        System.out.print("Zadaj svoju volbu: ");
        while (!sc.hasNextInt()) {
            System.out.print("Je potrebne zadat cele cislo od 2 do 6: ");
            sc.next();
        }
        int volba;

        do {
            volba = sc.nextInt();
            switch (volba) {
                case 1 -> {
                    if (!hra.koniecHry()) {
                        hra.tah();
                    }
                }
                // TODO spravit aby sa hra skoncila po konciHry, toto nefunguje
                case 2 -> hra.ulozHru();
                case 0 -> {
                    System.out.print("Naozaj si zelas ukoncit hru? Pokracovat v hre - 1, ukoncit hru - 0: ");
                    volba = sc.nextInt();
                }
                default -> System.out.println("Zle zadana moznost");
            }
        } while (volba < 0 || volba > 2);
        return volba;
    }

    private static void novaHra() {
        int pocet;

        do {
            System.out.print("Zadaj pocet hracov: ");
            while (!sc.hasNextInt()) {
                System.out.print("Je potrebne zadat cele cislo od 2 do 6: ");
                sc.next();
            }
            pocet = sc.nextInt();
            if (pocet < 2) {
                System.out.println("Pre spustenie hry su potrebni aspon dvaja hraci");
            } else if (pocet > 6) {
                System.out.println("Hru nemoze hrat viac ako 6 hracov");
            }
        } while (pocet < 2 || pocet > 6);

        HraUI.hra = new Hra(pocet);
        HraUI.druheMenu();
    }

    private static int experiment() {
        int pocetHracov = 2;
        int pocetHier = 1000;
        int[] pocty = new int[pocetHier];
        for (int i = 0; i < pocetHier; i++) {
            HraUI.hra = new Hra(pocetHracov);
            HraUI.druheMenu();

            while (!hra.koniecHry()) {
                HraUI.druheMenu();
            }

            pocty[i] = hra.getPocetTahov();
        }

        int priemer = 0;
        for (int i = 0; i < pocetHier; i++) {
            System.out.println(pocty[i] + " ");
            priemer += pocty[i];
        }
        System.out.println("Priemerny pocet kol je: " + priemer/pocetHier/pocetHracov);
        return priemer/pocetHier/pocetHracov;
    }

    private static void nacitajHru() {
        System.out.println("Hru zatial nie je mozne nacitat");
    }

}

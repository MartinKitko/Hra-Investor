package sk.uniza.fri.policka;

/**
 * Enum ktory predstavuje odvetvia policok typu podnik
 *
 * @author Martin Kitko
 * @version 18.5.2021
 */
public enum Odvetvie {
    ALKOHOL("alkohol"),
    TEXTIL("textil"),
    STAVEBNICTVO("stavebnictvo"),
    PVE("pve"),
    JEDLO("jedlo"),
    STROJARENSTVO("strojarenstvo"),
    RGS("rgs"),
    KOVY("kovy"),
    BANE("bane");

    private String odvetvie;

    /**
     * Konstruktor triedy Odvetvie
     * @param odvetvie hodnota String daneho odvetvia
     */
    Odvetvie(String odvetvie) {
        this.odvetvie = odvetvie;
    }

    /**
     * toString ktory vrati textovu reprezentaciu poloziek enumu
     * @return String s nazvom polozky enumu
     */
    @Override
    public String toString() {
        return this.odvetvie;
    }
}

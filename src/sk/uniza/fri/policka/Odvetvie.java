package sk.uniza.fri.policka;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
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

    Odvetvie(String odvetvie) {
        this.odvetvie = odvetvie;
    }

    @Override
    public String toString() {
        return this.odvetvie;
    }
}

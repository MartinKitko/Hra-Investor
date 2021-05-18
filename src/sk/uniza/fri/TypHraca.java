package sk.uniza.fri;

/**
 * Enum ktory predstavuje odvetvia policok typu podnik
 *
 * @author Martin Kitko
 * @version 18.5.2021
 */
public enum TypHraca {
    CLOVEK("clovek"),
    POCITAC("pocitac");

    private String typ;

    /**
     * Konstruktor triedy Odvetvie
     * @param typ hodnota String daneho typu hraca
     */
    TypHraca(String typ) {
        this.typ = typ;
    }

    /**
     * toString ktory vrati textovu reprezentaciu poloziek enumu
     * @return String s nazvom polozky enumu
     */
    @Override
    public String toString() {
        return this.typ;
    }
}

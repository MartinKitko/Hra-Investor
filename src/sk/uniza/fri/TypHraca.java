package sk.uniza.fri;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public enum TypHraca {
    CLOVEK("clovek"),
    POCITAC("pocitac");

    private String typ;

    TypHraca(String typ) {
        this.typ = typ;
    }

    @Override
    public String toString() {
        return this.typ;
    }
}

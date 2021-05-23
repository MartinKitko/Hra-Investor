package sk.uniza.fri;

import javax.swing.JTextArea;
import java.io.OutputStream;

/**
 * Trieda VlastnyOutputStream ktora je potomkom triedy OutputStream
 * Sluzi na presunutie textoveho vystupu z terminalu do textoveho pola
 *
 * @author Martin Kitko
 * @version 22.5.2021
 */
public class VlastnyOutputStream extends OutputStream {
    private JTextArea textovePole;

    /**
     * Konstruktor triedy VlastnyOutputStream
     * @param textArea textove pole do ktoreho chceme presunut vystup
     */
    public VlastnyOutputStream(JTextArea textArea) {
        this.textovePole = textArea;
    }

    /**
     * Pripise bajt zadany ako parameter do textoveho pola
     * @param b bajt ktory sa bude zapisovat
     */
    @Override
    public void write(int b) {
        this.textovePole.append(String.valueOf((char)b));
        this.textovePole.setCaretPosition(this.textovePole.getDocument().getLength());
    }
}

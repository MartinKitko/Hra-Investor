package sk.uniza.fri;

import javax.swing.JTextArea;
import java.io.OutputStream;

/**
 * Trieda VlastnyOutputStream ktora je potomkom triedy OutputStream
 * Sluzi na presunutie textoveho vystupu z terminalu do textoveho pola
 *
 * @author Martin Kitko
 * @version 18.5.2021
 */
public class VlastnyOutputStream extends OutputStream {
    private JTextArea textovePole;

    public VlastnyOutputStream(JTextArea textArea) {
        this.textovePole = textArea;
    }

    @Override
    public void write(int b) {
        this.textovePole.append(String.valueOf((char)b));
        this.textovePole.setCaretPosition(this.textovePole.getDocument().getLength());
    }
}

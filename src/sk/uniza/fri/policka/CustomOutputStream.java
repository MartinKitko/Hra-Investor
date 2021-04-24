package sk.uniza.fri.policka;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author
 */
public class CustomOutputStream extends OutputStream {
    private JTextArea textovePole;

    public CustomOutputStream(JTextArea textArea) {
        this.textovePole = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        this.textovePole.append(String.valueOf((char)b));
        this.textovePole.setCaretPosition(this.textovePole.getDocument().getLength());
        this.textovePole.update(this.textovePole.getGraphics());
    }
}

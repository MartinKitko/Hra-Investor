package sk.uniza.fri;

/*import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;*/

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class GUI extends JFrame {
    public GUI() {
        this.setTitle("Investor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);
        this.setVisible(true);

        ImageIcon image = new ImageIcon("src/sk/uniza/fri/logo.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(248, 255, 247));

        JLabel label = new JLabel();
        label.setText("Vitaj v hre Investor");
        this.add(label);

        ImageIcon image2 = new ImageIcon("src/sk/uniza/fri/logo.png");
        Border border = BorderFactory.createLineBorder(Color.green, 2);
        label.setIcon(image2);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        //label.setForeground(new Color(0, 0, 0)); // farba textu
        label.setFont(new Font("Arial", Font.PLAIN, 20));
    }
}

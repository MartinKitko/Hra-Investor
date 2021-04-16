package sk.uniza.fri;

/*import javax.swing.BorderFactory;
import javax.swing.border.Border;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;*/

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class GUI extends JFrame implements ActionListener {
    private JButton novaHra;

    public GUI() {
        this.setTitle("Investor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setMinimumSize(new Dimension(800, 600));
        this.setVisible(true);

        ImageIcon image = new ImageIcon("src/sk/uniza/fri/logo.png");
        this.setIconImage(image.getImage());
        //this.getContentPane().setBackground(new Color(248, 255, 247));

        JLabel label = new JLabel();
        //label.setText("Vitaj v hre Investor");
        this.add(label);

        ImageIcon image2 = new ImageIcon("src/sk/uniza/fri/hraciaPlocha.jpg");
        //Border border = BorderFactory.createLineBorder(Color.black, 2);
        label.setIcon(image2);
        //label.setHorizontalTextPosition(JLabel.CENTER);
        //label.setVerticalTextPosition(JLabel.TOP);
        //label.setForeground(new Color(0, 0, 0)); // farba textu
        //label.setFont(new Font("Arial", Font.PLAIN, 20));
        //label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.LEFT);

        /*JPanel panel = new JPanel();
        panel.setBounds(200, 100, 150, 60);

        this.add(panel);*/


        /*JPanel panel2 = new JPanel();
        panel2.setBounds(200, 10, 100, 40);
        this.add(panel2);*/

        this.novaHra = new JButton();
        this.novaHra.setBounds(620, 200, 150, 60);
        this.novaHra.addActionListener(this);
        this.novaHra.setText("Nová hra");
        this.novaHra.setFont(new Font("Arial", Font.BOLD, 15));
        this.novaHra.setFocusable(false);
        this.novaHra.setBackground(Color.lightGray);
        this.novaHra.setBorder(BorderFactory.createEtchedBorder());
        //this.button.setEnabled(false);
        //this.button.addActionListener(e -> System.out.println("klik"));
        this.add(this.novaHra);

        this.repaint(); // spravit aby toto nebolo treba
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.novaHra) {
            System.out.println("Nova hra");
        }
    }
}

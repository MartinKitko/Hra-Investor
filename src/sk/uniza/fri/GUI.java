package sk.uniza.fri;

/*import javax.swing.BorderFactory;
import javax.swing.border.Border;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;*/

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class GUI extends JFrame implements ActionListener {
    private Hra hra;
    private JButton novaHra;
    private JButton hodKockou;

    public GUI() {
        this.setTitle("Investor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setMinimumSize(new Dimension(900, 675));


        ImageIcon image = new ImageIcon("src/sk/uniza/fri/logo.png");
        this.setIconImage(image.getImage());
        //this.getContentPane().setBackground(new Color(248, 255, 247));

        /*JLabel label = new JLabel();
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

        /*this.novaHra = new JButton("Nová hra");
        this.novaHra.setBounds(620, 200, 150, 60);
        this.novaHra.addActionListener(this);
        //this.novaHra.setText("Nová hra");
        this.novaHra.setFont(new Font("Arial", Font.BOLD, 15));
        this.novaHra.setFocusable(false);
        this.novaHra.setBackground(Color.lightGray);
        this.novaHra.setBorder(BorderFactory.createEtchedBorder());
        //this.button.setEnabled(false);
        //this.button.addActionListener(e -> System.out.println("klik"));
        this.add(this.novaHra);

        this.hodKockou = new JButton("Hod kockou");
        this.hodKockou.setBounds(620, 300, 150, 60);
        this.hodKockou.addActionListener(this);
        this.hodKockou.setFont(new Font("Arial", Font.BOLD, 15));
        this.hodKockou.setFocusable(false);
        this.hodKockou.setBackground(Color.lightGray);
        this.hodKockou.setBorder(BorderFactory.createEtchedBorder());
        this.hodKockou.setEnabled(false);
        this.add(this.hodKockou);*/


        JButton button1 = new JButton("Button1");
        JButton button2 = new JButton("Button2");
        JButton button3 = new JButton("Button3");
        JButton button4 = new JButton("Button4");
        JButton button5 = new JButton("Button5");
        JButton button6 = new JButton("Button6");
        JButton button7 = new JButton("Button7");

        Container hlavnyKontajner = this.getContentPane();
        hlavnyKontajner.setLayout(new BorderLayout());
        hlavnyKontajner.setBackground(Color.GREEN);

        JLabel labelHrac = new JLabel("Hrac:", SwingConstants.LEFT);
        labelHrac.setOpaque(true);

        JTextField textHrac = new JTextField();
        textHrac.setPreferredSize(new Dimension(50, 20));

        JLabel labelPeniaze = new JLabel("Peniaze:", SwingConstants.LEFT);
        labelPeniaze.setOpaque(true);

        JTextField textPeniaze = new JTextField();
        textPeniaze.setPreferredSize(new Dimension(50, 20));

        JPanel hornyPanel = new JPanel();
        hornyPanel.setBackground(Color.ORANGE);
        hornyPanel.setLayout(new FlowLayout(5));
        hlavnyKontajner.add(hornyPanel, BorderLayout.NORTH);
        hornyPanel.add(labelHrac);
        hornyPanel.add(textHrac);
        hornyPanel.add(labelPeniaze);
        hornyPanel.add(textPeniaze);


        JPanel bocnyPanel = new JPanel();
        bocnyPanel.setBackground(Color.CYAN);
        bocnyPanel.setLayout(new FlowLayout(4, 4, 4));

        JPanel gridPanel = new JPanel();
        gridPanel.setBackground(Color.RED);
        gridPanel.setLayout(new GridLayout(3, 1, 5, 40));

        gridPanel.add(button4);
        gridPanel.add(button5);
        gridPanel.add(button6);

        JLabel label = new JLabel("Center Box", SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBorder(new LineBorder(Color.BLACK, 3));

        bocnyPanel.add(gridPanel);
        hlavnyKontajner.add(label);
        hlavnyKontajner.add(bocnyPanel, BorderLayout.EAST);

        /*JPanel spodnyPanel = new JPanel();
        spodnyPanel.setBackground(Color.MAGENTA);
        spodnyPanel.setLayout(new FlowLayout(3));
        spodnyPanel.add(button7);
        hlavnyKontajner.add(spodnyPanel, BorderLayout.SOUTH);*/

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.novaHra) {
            System.out.println("Nova hra");

            int pocetHracov;
            do {
                pocetHracov = Integer.parseInt(JOptionPane.showInputDialog("Zadaj pocet hracov: "));
                if (pocetHracov < 2) {
                    System.out.println("Pre spustenie hry su potrebni aspon dvaja hraci");
                } else if (pocetHracov > 6) {
                    System.out.println("Hru nemoze hrat viac ako 6 hracov");
                }
            } while (pocetHracov < 2 || pocetHracov > 6);
            this.novaHra.setVisible(false);

            this.hra = new Hra(pocetHracov);

            this.hodKockou.setEnabled(true);
        } else if (e.getSource() == this.hodKockou) {
            this.hra.tah();
        }
    }
}

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
    private JButton nacitajHru;
    private JButton koniecHry;
    private JButton hodKockou;
    private JPanel hornyPanel;
    private JPanel bocnyPanel;
    private JPanel gridPanel;
    private JLabel labelHrac;
    private JLabel labelPeniaze;
    private JLabel labelStred;
    private JTextField textHrac;
    private JTextField textPeniaze;


    public GUI() {
        this.setTitle("Investor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setMinimumSize(new Dimension(900, 675));
        this.setVisible(true);


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


        this.novaHra = new JButton("Nova hra");
        this.novaHra.addActionListener(this);
        this.nacitajHru = new JButton("Nacitaj hru");
        this.nacitajHru.addActionListener(this);
        this.koniecHry = new JButton("Koniec hry");
        this.koniecHry.addActionListener(this);
        this.hodKockou = new JButton("Hod kockou");
        this.hodKockou.addActionListener(this);

        Container hlavnyKontajner = this.getContentPane();
        hlavnyKontajner.setLayout(new BorderLayout());
        hlavnyKontajner.setBackground(Color.GREEN);

        this.labelHrac = new JLabel("Hrac:", SwingConstants.LEFT);
        this.labelHrac.setOpaque(true);

        this.textHrac = new JTextField();
        this.textHrac.setPreferredSize(new Dimension(50, 20));

        this.labelPeniaze = new JLabel("Peniaze:", SwingConstants.LEFT);
        this.labelPeniaze.setOpaque(true);

        this.textPeniaze = new JTextField();
        this.textPeniaze.setPreferredSize(new Dimension(50, 20));

        this.hornyPanel = new JPanel();
        this.hornyPanel.setBackground(Color.ORANGE);
        this.hornyPanel.setLayout(new FlowLayout(5));
        hlavnyKontajner.add(this.hornyPanel, BorderLayout.NORTH);
        this.hornyPanel.add(this.labelHrac);
        this.hornyPanel.add(this.textHrac);
        this.hornyPanel.add(this.labelPeniaze);
        this.hornyPanel.add(this.textPeniaze);


        this.bocnyPanel = new JPanel();
        this.bocnyPanel.setBackground(Color.CYAN);
        this.bocnyPanel.setLayout(new FlowLayout(4, 4, 4));

        this.gridPanel = new JPanel();
        this.gridPanel.setBackground(Color.RED);
        this.gridPanel.setLayout(new GridLayout(4, 1, 5, 40));

        this.gridPanel.add(this.novaHra);
        this.gridPanel.add(this.nacitajHru);
        this.gridPanel.add(this.koniecHry);
        this.gridPanel.add(this.hodKockou);
        this.hodKockou.setVisible(false);

        this.labelStred = new JLabel("Center Box", SwingConstants.CENTER);
        this.labelStred.setOpaque(true);
        this.labelStred.setBorder(new LineBorder(Color.BLACK, 3));

        this.bocnyPanel.add(this.gridPanel);
        hlavnyKontajner.add(this.labelStred);
        hlavnyKontajner.add(this.bocnyPanel, BorderLayout.EAST);

        /*JPanel spodnyPanel = new JPanel();
        spodnyPanel.setBackground(Color.MAGENTA);
        spodnyPanel.setLayout(new FlowLayout(3));
        spodnyPanel.add(button7);
        hlavnyKontajner.add(spodnyPanel, BorderLayout.SOUTH);*/

        this.validate();

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
            this.nacitajHru.setVisible(false);
            this.koniecHry.setVisible(false);

            this.hra = new Hra(pocetHracov);

            this.hodKockou.setVisible(true);
        } else if (e.getSource() == this.hodKockou) {
            this.hra.tah();
        }
    }

}
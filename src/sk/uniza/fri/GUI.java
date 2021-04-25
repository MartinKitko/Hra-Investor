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

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import sk.uniza.fri.policka.CustomOutputStream;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.Scanner;


/**
 * 1. 4. 2021 - 17:09
 *
 * @author Martin Kitko
 */
public class GUI extends JFrame implements ActionListener {
    private static GUI instancia;
    private Hra hra;
    private JButton hodKockou;
    private JButton kupitPodnik;
    private JButton zobrazInfo;
    private JButton ziadnaAkcia;
    private JPanel hornyPanel;
    private JPanel bocnyPanel;
    private JPanel gridPanel;
    private JLabel labelHrac;
    private JLabel labelPeniaze;
    private JLabel labelStred;
    private JTextField textHrac;
    private JTextField textPeniaze;
    private JTextArea textovePole;
    private JMenuBar menuBar;
    private JMenu hraMenu;
    private JMenuItem novaHraMenu;
    private JMenuItem nacitajHruMenu;
    private JMenuItem ulozHruMenu;
    private JMenuItem koniecMenu;


    private GUI() {
        this.setTitle("Investor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setMinimumSize(new Dimension(1000, 650));
        this.setVisible(true);


        ImageIcon image = new ImageIcon("src/sk/uniza/fri/logo.png");
        this.setIconImage(image.getImage());
        //this.getContentPane().setBackground(new Color(248, 255, 247));

        this.menuBar = new JMenuBar();
        this.hraMenu = new JMenu("Hra");
        this.novaHraMenu = new JMenuItem("Nova hra");
        this.novaHraMenu.addActionListener(this);
        this.nacitajHruMenu = new JMenuItem("Nacitaj hru");
        this.nacitajHruMenu.addActionListener(this);
        this.ulozHruMenu = new JMenuItem("Uloz hru");
        this.ulozHruMenu.addActionListener(this);
        this.koniecMenu = new JMenuItem("Koniec");
        this.koniecMenu.addActionListener(this);
        this.hraMenu.add(this.novaHraMenu);
        this.hraMenu.add(this.nacitajHruMenu);
        this.hraMenu.add(this.ulozHruMenu);
        this.ulozHruMenu.setVisible(false);
        this.hraMenu.add(this.koniecMenu);

        JMenu helpMenu = new JMenu("Help");
        this.setJMenuBar(this.menuBar);
        this.menuBar.add(this.hraMenu);
        this.menuBar.add(helpMenu);


        this.hodKockou = new JButton("Hod kockou");
        this.hodKockou.addActionListener(this);
        this.hodKockou.setFocusable(false);
        this.hodKockou.setEnabled(false);

        this.kupitPodnik = new JButton("Kupit podnik");
        this.kupitPodnik.addActionListener(this);
        this.kupitPodnik.setFocusable(false);
        this.kupitPodnik.setEnabled(false);

        // TODO tlacitko na zobrazenie vlastnenych policok
        this.zobrazInfo = new JButton("Zobraz info");
        this.zobrazInfo.addActionListener(this);
        this.zobrazInfo.setFocusable(false);
        this.zobrazInfo.setEnabled(false);

        this.ziadnaAkcia = new JButton("Ziadna akcia");
        this.ziadnaAkcia.addActionListener(this);
        this.ziadnaAkcia.setFocusable(false);
        this.ziadnaAkcia.setEnabled(false);


        Container hlavnyKontajner = this.getContentPane();
        hlavnyKontajner.setLayout(new BorderLayout());
        hlavnyKontajner.setBackground(Color.GREEN);


        /*JLayeredPane layeredPane2 = new JLayeredPane();
        //layeredPane2.setBounds(0, 0, 500, 500);
        this.add(layeredPane2);
        JLabel label1 = new JLabel();
        label1.setOpaque(true);
        label1.setBackground(Color.RED);
        label1.setBounds(50, 50, 200, 200);

        JLabel label2 = new JLabel();
        label2.setOpaque(true);
        label2.setBackground(Color.GREEN);
        label2.setBounds(100, 100, 200, 200);

        JLabel label3 = new JLabel();
        label3.setOpaque(true);
        label3.setBackground(Color.BLUE);
        label3.setBounds(150, 150, 200, 200);

        //layeredPane.add(label1, JLayeredPane.DEFAULT_LAYER);
        layeredPane2.add(label1, Integer.valueOf(0));
        layeredPane2.add(label2, Integer.valueOf(2));
        layeredPane2.add(label3, Integer.valueOf(1));
        //layeredPane2.setLayer(label1, Integer.valueOf(3));*/


        /*JPanel hraPanel = new JPanel();
        hraPanel.setBackground(Color.YELLOW);
        hraPanel.setOpaque(true);
        //hlavnyKontajner.add(hraPanel);


        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.ORANGE);
        //hlavnyKontajner.add(menuPanel);
        //layeredPane.add(menuPanel, Integer.valueOf(1));*/


        this.labelHrac = new JLabel("Hrac:", SwingConstants.LEFT);
        //this.labelHrac.setBackground(Color.ORANGE);
        this.labelHrac.setOpaque(true);

        this.textHrac = new JTextField();
        this.textHrac.setPreferredSize(new Dimension(50, 20));
        this.textHrac.setEditable(false);

        this.labelPeniaze = new JLabel("Peniaze:", SwingConstants.LEFT);
        this.labelPeniaze.setOpaque(true);

        this.textPeniaze = new JTextField();
        this.textPeniaze.setPreferredSize(new Dimension(50, 20));
        this.textPeniaze.setEditable(false);

        this.hornyPanel = new JPanel();
        this.hornyPanel.setBackground(Color.ORANGE);
        this.hornyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        hlavnyKontajner.add(this.hornyPanel, BorderLayout.NORTH);
        //hraPanel.add(this.hornyPanel, BorderLayout.NORTH);
        //menuPanel.add(this.hornyPanel, BorderLayout.NORTH);
        this.hornyPanel.add(this.labelHrac);
        this.hornyPanel.add(this.textHrac);
        this.hornyPanel.add(this.labelPeniaze);
        this.hornyPanel.add(this.textPeniaze);

        this.textovePole = new JTextArea();
        this.textovePole.setColumns(20);
        this.textovePole.setLineWrap(true);
        this.textovePole.setWrapStyleWord(true);

        /*JScrollPane scroll = new JScrollPane(this.textovePole);
        getContentPane().add(scroll);*/

        // presunutie vypisovania z terminalu do textoveho pola
        PrintStream printStream = new PrintStream(new CustomOutputStream(this.textovePole));
        System.setOut(printStream);
        System.setErr(printStream);

        this.bocnyPanel = new JPanel();
        this.bocnyPanel.setBackground(Color.CYAN);
        this.bocnyPanel.setLayout(new GridLayout(2, 1, 4, 4));

        JScrollPane scroll = new JScrollPane(this.textovePole);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.gridPanel = new JPanel();
        this.gridPanel.setBackground(Color.RED);
        //this.gridPanel.setLayout(new GridLayout(2, 1, 4, 4));
        this.gridPanel.setLayout(new GridLayout(4, 1, 4, 4));
        //this.gridPanel.add(this.textovePole);
        this.gridPanel.add(this.hodKockou);
        this.gridPanel.add(this.kupitPodnik);
        this.gridPanel.add(this.zobrazInfo);
        this.gridPanel.add(this.ziadnaAkcia);
        this.bocnyPanel.add(scroll);
        this.bocnyPanel.add(this.gridPanel);

        /*JLayeredPane bocnyLayeredPane = new JLayeredPane();
        bocnyLayeredPane.setLayout(new FlowLayout(4, 4, 4));
        this.add(bocnyLayeredPane);
        //bocnyLayeredPane.add(this.bocnyPanel, Integer.valueOf(0));
        //bocnyLayeredPane.add(bocnyPanel2, Integer.valueOf(1));
        bocnyLayeredPane.add(panel1, Integer.valueOf(0));
        bocnyLayeredPane.add(panel2, Integer.valueOf(1));*/

        this.labelStred = new JLabel("", SwingConstants.CENTER);
        this.labelStred.setOpaque(true);
        ImageIcon image2 = new ImageIcon("src/sk/uniza/fri/hraciaPlocha.jpg");
        /*Image imageScaled = image2.getImage();
        Image scaledImage = imageScaled.getScaledInstance(this.labelStred.getWidth(), this.labelStred.getHeight(), Image.SCALE_SMOOTH);
        this.labelStred.setIcon(new ImageIcon(scaledImage));*/
        this.labelStred.setIcon(image2);

        this.bocnyPanel.add(this.gridPanel);
        //hraPanel.add(this.labelStred);
        //menuPanel.add(this.labelStred);
        //menuPanel.add(this.bocnyPanel, BorderLayout.EAST);
        hlavnyKontajner.add(this.labelStred);
        hlavnyKontajner.add(this.bocnyPanel, BorderLayout.EAST);
        //hraPanel.add(this.bocnyPanel, BorderLayout.EAST);
        //hlavnyKontajner.setVisible(false);
        //hraPanel.setVisible(false);
        //menuPanel.setVisible(true);

        //hraPanel.setOpaque(true);
        //hraPanel.setVisible(true);
        //layeredPane.add(hraPanel, Integer.valueOf(0));

        this.validate();

    }

    public static GUI getInstancia() {
        if (GUI.instancia == null) {
            GUI.instancia = new GUI();
        }
        return GUI.instancia;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.novaHraMenu) {
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
            //this.novaHra.setVisible(false);
            //this.nacitajHru.setVisible(false);
            //this.koniecHry.setVisible(false);

            this.hra = new Hra(pocetHracov);
            this.hodKockou.setEnabled(true);
            this.ulozHruMenu.setVisible(true);

            this.textHrac.setText(this.hra.getAktHrac().getMeno());
            this.textPeniaze.setText("" + this.hra.getAktHrac().getPeniaze());
        } else if (e.getSource() == this.nacitajHruMenu) {
            this.nacitajHru();
        } else if (e.getSource() == this.ulozHruMenu) {
            this.ulozHru();
        } else if (e.getSource() == this.koniecMenu) {
            int volba = this.zobrazMoznosti("Naozaj chces ukoncit hru?", "Koniec hry");
            if (volba == 0) {
                System.out.println("Koniec hry");
                System.exit(0);
            }
        } else if (e.getSource() == this.hodKockou) {
            this.hra.tah();
            this.textHrac.setText(this.hra.getAktHrac().getMeno());
            this.textPeniaze.setText("" + this.hra.getAktHrac().getPeniaze());
        }
    }

    public void zobraz(String text) {
        this.textovePole.append(text + "\n");
        this.repaint();
    }

    public int zobrazMoznosti(String sprava, String nazov) {
        //return JOptionPane.showConfirmDialog(null, sprava, nazov, JOptionPane.YES_NO_OPTION);
        Object[] moznosti = {"Ano", "Nie"};
        return JOptionPane.showOptionDialog(null, sprava, nazov, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, moznosti, moznosti[1]);
    }

    private void nacitajHru() {
        String nacitanySuborString = "";
        File nacitanySubor = this.nacitajSubor();
        if (nacitanySubor == null) {
            return;
        }
        try (Scanner citac = new Scanner(nacitanySubor)) {
            nacitanySuborString = citac.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            this.nacitajHru();
        } finally {
            if (!nacitanySuborString.equals("")) {
                YaGson mapper = new YaGson();
                this.hra = mapper.fromJson(nacitanySuborString, Hra.class);
            }
        }
        System.out.println("Hra bola uspesne nacitana!");
    }

    private void ulozHru() {
        PrintWriter zapisovac = null;
        YaGson mapper = new YaGsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
        String json = mapper.toJson(this.hra, Hra.class);
        try {
            zapisovac = new PrintWriter(this.nacitajSubor());
            zapisovac.println(json);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (zapisovac != null) {
                zapisovac.close();
            }
        }
        System.out.println("Hra bola uspesne ulozena!");
    }

    private File nacitajSubor() {
        String nazovSuboru;
        do {
            nazovSuboru = JOptionPane.showInputDialog(null, "Zadaj nazov suboru bez pripony",
                    "Nazov suboru", JOptionPane.QUESTION_MESSAGE);
            if (nazovSuboru == null) {
                return null;
            }
        } while (nazovSuboru.equals(""));
        return new File(nazovSuboru + ".txt");
    }

}

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
        label.setHorizontalAlignment(JLabel.LEFT);*/

/*JPanel panel = new JPanel();
        panel.setBounds(200, 100, 150, 60);
        this.add(panel);*/

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
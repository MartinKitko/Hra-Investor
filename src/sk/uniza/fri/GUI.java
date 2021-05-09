package sk.uniza.fri;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
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
public class GUI implements ActionListener {
    private static GUI instancia;
    private Hra hra;
    private JFrame okno;
    private JButton hodKockou;
    private JButton vlastnenePolicka;
    private JButton predajPolicko;
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
        this.okno = new JFrame("Investor");
        this.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO potvrdenie zavretia po kliknuti na X
        this.okno.setResizable(true);
        this.okno.setMinimumSize(new Dimension(1280, 720));
        this.okno.setVisible(true);

        /*try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }*/

        ImageIcon image = new ImageIcon("obrazky/logo.png");
        this.okno.setIconImage(image.getImage());

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
        this.okno.setJMenuBar(this.menuBar);
        this.menuBar.add(this.hraMenu);
        this.menuBar.add(helpMenu);

        this.hodKockou = new JButton("Hod kockou");
        this.hodKockou.addActionListener(this);
        this.hodKockou.setFocusable(false);
        this.hodKockou.setEnabled(false);

        this.vlastnenePolicka = new JButton("Zobrazit vlastnene policka");
        this.vlastnenePolicka.addActionListener(this);
        this.vlastnenePolicka.setFocusable(false);
        this.vlastnenePolicka.setEnabled(false);

        this.predajPolicko = new JButton("Predaj policko");
        this.predajPolicko.addActionListener(this);
        this.predajPolicko.setFocusable(false);
        this.predajPolicko.setEnabled(false);

        Container hlavnyKontajner = this.okno.getContentPane();
        hlavnyKontajner.setLayout(new BorderLayout());

        this.labelHrac = new JLabel("Hrac: ", SwingConstants.LEFT);
        this.labelHrac.setOpaque(true);
        this.textHrac = new JTextField();
        this.textHrac.setPreferredSize(new Dimension(100, 20));
        this.textHrac.setEditable(false);

        this.labelPeniaze = new JLabel("Peniaze:", SwingConstants.LEFT);
        this.labelPeniaze.setOpaque(true);
        this.textPeniaze = new JTextField();
        this.textPeniaze.setPreferredSize(new Dimension(100, 20));
        this.textPeniaze.setEditable(false);

        this.hornyPanel = new JPanel();
        this.hornyPanel.setLayout(new BoxLayout(this.hornyPanel, BoxLayout.PAGE_AXIS));
        JPanel hracPanel = new JPanel();
        hracPanel.add(this.labelHrac);
        hracPanel.add(this.textHrac);
        hracPanel.setMaximumSize(new Dimension(200, 30));
        this.hornyPanel.add(hracPanel);
        JPanel peniazePanel = new JPanel();
        peniazePanel.add(this.labelPeniaze);
        peniazePanel.add(this.textPeniaze);
        peniazePanel.setMaximumSize(new Dimension(200, 30));
        this.hornyPanel.add(peniazePanel);

        this.textovePole = new JTextArea();
        this.textovePole.setColumns(22);
        this.textovePole.setRows(20);
        this.textovePole.setLineWrap(true);
        this.textovePole.setWrapStyleWord(true);

        this.bocnyPanel = new JPanel();
        this.bocnyPanel.setLayout(new BoxLayout(this.bocnyPanel, BoxLayout.PAGE_AXIS));
        this.bocnyPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        this.bocnyPanel.add(this.hornyPanel);

        JScrollPane scroll = new JScrollPane(this.textovePole);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.gridPanel = new JPanel();
        this.gridPanel.setLayout(new GridLayout(4, 1, 4, 4));
        this.gridPanel.add(this.hodKockou);
        this.gridPanel.add(this.vlastnenePolicka);
        this.gridPanel.add(this.predajPolicko);

        this.bocnyPanel.add(scroll);
        this.bocnyPanel.add(this.gridPanel);

        this.labelStred = new JLabel("", SwingConstants.CENTER);
        this.labelStred.setOpaque(true);
        StretchIcon image2 = new StretchIcon("obrazky/hraciaPlocha.jpg");
        this.labelStred.setIcon(image2);

        this.bocnyPanel.add(this.gridPanel);
        hlavnyKontajner.add(this.labelStred);
        hlavnyKontajner.add(this.bocnyPanel, BorderLayout.EAST);
        this.okno.pack();
        this.okno.validate();
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
            //this.vytvorNovuHru();
            this.experiment();
        } else if (e.getSource() == this.nacitajHruMenu) {
            this.nacitajHru();
        } else if (e.getSource() == this.ulozHruMenu) {
            this.ulozHru();
        } else if (e.getSource() == this.koniecMenu) {
            this.koniecHry();
        } else if (e.getSource() == this.hodKockou) {
            this.vykonajHodKockou();
        } else if (e.getSource() == this.vlastnenePolicka) {
            this.vypisVlastnenePolicka();
        } else if (e.getSource() == this.predajPolicko) {
            this.predajPolicka();
        }
    }

    private void vytvorNovuHru() {
        this.vypisovanieDoTextovehoPola();
        int pocetHracov = 0;

        do {
            String pocetHracovString = JOptionPane.showInputDialog("Zadaj pocet hracov: ");
            if (pocetHracovString == null) {
                return;
            }
            try {
                pocetHracov = Integer.parseInt(pocetHracovString);
                if (pocetHracov < 2) {
                    System.out.println("Pre spustenie hry su potrebni aspon dvaja hraci");
                } else if (pocetHracov > 6) {
                    System.out.println("Hru nemoze hrat viac ako 6 hracov");
                }
            } catch (NumberFormatException e) {
                System.out.println("Je potrebne zadat cislo");
            }
        } while (pocetHracov < 2 || pocetHracov > 6);

        int pocetPocitacov = -1;
        do {
            String pocetPocitacovString = JOptionPane.showInputDialog("Z toho pocitacov: ");
            if (pocetPocitacovString == null) {
                return;
            }
            try {
                pocetPocitacov = Integer.parseInt(pocetPocitacovString);
                if (pocetPocitacov < 0) {
                    System.out.println("Pocet pocitacov nesmie byt zaporny");
                } else if (pocetPocitacov > pocetHracov) {
                    System.out.println("Pocet pocitacov nesmie presiahnut pocet hracov");
                }
            } catch (NumberFormatException e) {
                System.out.println("Je potrebne zadat cislo");
            }
        } while (pocetPocitacov < 0 || pocetPocitacov > pocetHracov);

        System.out.println("Nova hra");

        this.hra = new Hra(pocetHracov, pocetPocitacov);
        this.pripravNaHru();
    }

    private void pripravNaHru() {
        this.hodKockou.setEnabled(true);
        this.vlastnenePolicka.setEnabled(true);
        this.predajPolicko.setEnabled(true);
        this.ulozHruMenu.setVisible(true);

        this.textHrac.setText(this.hra.getAktHrac().getMeno());
        this.textPeniaze.setText("" + this.hra.getAktHrac().getPeniaze());
    }

    private void experiment() {
        int pocetHracov = 2;
        int pocetHier = 100;
        int[] pocty = new int[pocetHier];

        for (int i = 0; i < pocetHier; i++) {
            do {
                this.hra = new Hra(pocetHracov, pocetHracov);
            } while (this.hra.getPocetTahov() > 9999);
            pocty[i] = this.hra.getPocetTahov();
        }

        int sucet = 0;
        for (int i = 0; i < pocetHier; i++) {
            System.out.println(pocty[i] / pocetHracov + " ");
            sucet += pocty[i];
        }
        System.out.println("Priemerny pocet kol je: " + sucet / pocetHier / pocetHracov);
    }

    private void vypisovanieDoTextovehoPola() {
        PrintStream printStream = new PrintStream(new VlastnyOutputStream(this.textovePole));
        System.setOut(printStream);
        System.setErr(printStream);
    }

    private void vykonajHodKockou() {
        if (!this.hra.koniecHry()) {
            this.hra.tah();
            this.textHrac.setText(this.hra.getAktHrac().getMeno());
            this.textPeniaze.setText("" + this.hra.getAktHrac().getPeniaze());
        } else {
            this.hodKockou.setEnabled(false);
        }
    }

    private void vypisVlastnenePolicka() {
        System.out.println("\n" + this.hra.getAktHrac().dajVlastnenePolicka());
    }

    public void predajPolicka() {
        Hrac hrac = this.hra.getAktHrac();
        int cisloPolicka = 0;
        String cisloPolickaString = JOptionPane.showInputDialog("Zadaj cislo policka na predaj: ");
        if (cisloPolickaString == null) {
            return;
        }
        try {
            cisloPolicka = Integer.parseInt(cisloPolickaString);
            if (cisloPolicka > 0 && cisloPolicka <= hrac.getPocetVlastnenych()) {
                System.out.println(hrac.predajPolicko(cisloPolicka));
                this.textPeniaze.setText("" + this.hra.getAktHrac().getPeniaze());
            } else {
                System.out.println("Policko so zadanym cislom neexistuje");
            }
        } catch (NumberFormatException e) {
            System.out.println("Je potrebne zadat cislo");
        }
    }

    public int zobrazMoznosti(String sprava, String nazov, boolean zobrazInfo) {
        String[] moznosti;
        if (zobrazInfo) {
            moznosti = new String[]{"Ano", "Nie", "Zobraz info"};
        } else {
            moznosti = new String[]{"Ano", "Nie"};
        }

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
                this.vypisovanieDoTextovehoPola();
                YaGson mapper = new YaGson();
                this.hra = mapper.fromJson(nacitanySuborString, Hra.class);
            }
        }
        this.pripravNaHru();
        System.out.println("\nHra bola uspesne nacitana!");
    }

    private void ulozHru() {
        PrintWriter zapisovac = null;
        YaGson mapper = new YaGsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .serializeNulls()
                .setVersion(1.0)
                .create();
        String json = mapper.toJson(this.hra, Hra.class);
        File nacitanySubor = this.nacitajSubor();
        if (nacitanySubor == null) {
            return;
        }
        try {
            zapisovac = new PrintWriter(nacitanySubor);
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
        return new File("ulozeneHry/" + nazovSuboru + ".txt");
    }

    private void koniecHry() {
        int volba = this.zobrazMoznosti("Naozaj chces ukoncit hru?", "Koniec hry", false);
        if (volba == 0) {
            System.out.println("Koniec hry");
            System.exit(0);
        }
    }

}
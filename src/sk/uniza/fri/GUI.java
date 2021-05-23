package sk.uniza.fri;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;

/**
 * Trieda GUI s navrhovym vzorom Singleton ktora predstavuje graficke pouzivatelske rozhranie hry
 *
 * @author Martin Kitko
 * @version 23.5.2021
 */
public class GUI implements ActionListener {
    private static GUI instancia;
    private Hra hra;
    private String[] farby;
    private JFrame okno;
    private JButton btnHodKockou;
    private JButton btnVlastnenePolicka;
    private JButton btnPredajPolicko;
    private JLabel[] poleFigurok;
    private JLayeredPane labelStred;
    private JTextField txfHrac;
    private JTextField txfPeniaze;
    private JTextField txfFarba;
    private JTextArea txaTextovePole;
    private JMenuItem novaHraMenu;
    private JMenuItem nacitajHruMenu;
    private JMenuItem ulozHruMenu;
    private JMenuItem koniecMenu;

    /**
     * Metoda main pre spustenie hry ktora vytvori instanciu tejto triedy
     */
    public static void main(String[] args) {
        GUI.getInstancia();
    }

    /**
     * Konstruktor triedy GUI ktory vytvori samotne okno GUI a vsetko v nom potrebne
     */
    private GUI() {
        this.farby = new String[]{"cervena", "zelena", "modra", "zlta", "oranzova", "cierna"};

        this.okno = new JFrame("Investor");
        this.okno.setResizable(true);
        this.okno.setMinimumSize(new Dimension(1280, 720));
        this.okno.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ImageIcon image = new ImageIcon("obrazky/logo.png");
        this.okno.setIconImage(image.getImage());

        this.okno.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                GUI.this.koniecHry();
            }
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu hraMenu = new JMenu("Hra");
        this.novaHraMenu = new JMenuItem("Nova hra");
        this.nacitajHruMenu = new JMenuItem("Nacitaj hru");
        this.ulozHruMenu = new JMenuItem("Uloz hru");
        this.koniecMenu = new JMenuItem("Koniec");
        this.novaHraMenu.addActionListener(this);
        this.nacitajHruMenu.addActionListener(this);
        this.ulozHruMenu.addActionListener(this);
        this.koniecMenu.addActionListener(this);

        hraMenu.add(this.novaHraMenu);
        hraMenu.add(this.nacitajHruMenu);
        hraMenu.add(this.ulozHruMenu);
        hraMenu.add(this.koniecMenu);
        this.ulozHruMenu.setVisible(false);

        this.okno.setJMenuBar(menuBar);
        menuBar.add(hraMenu);

        this.btnHodKockou = new JButton("Hod kockou");
        this.btnHodKockou.addActionListener(this);
        this.btnHodKockou.setFocusable(false);
        this.btnHodKockou.setEnabled(false);

        this.btnVlastnenePolicka = new JButton("Zobrazit vlastnene policka");
        this.btnVlastnenePolicka.addActionListener(this);
        this.btnVlastnenePolicka.setFocusable(false);
        this.btnVlastnenePolicka.setEnabled(false);

        this.btnPredajPolicko = new JButton("Predaj policko");
        this.btnPredajPolicko.addActionListener(this);
        this.btnPredajPolicko.setFocusable(false);
        this.btnPredajPolicko.setEnabled(false);

        Container hlavnyKontajner = this.okno.getContentPane();
        hlavnyKontajner.setLayout(new BorderLayout());

        JLabel labelHrac = new JLabel("Hrac: ", SwingConstants.LEFT);
        labelHrac.setOpaque(true);
        this.txfHrac = new JTextField();
        this.txfHrac.setPreferredSize(new Dimension(100, 20));
        this.txfHrac.setEditable(false);

        JLabel labelFarba = new JLabel("Figurka:", SwingConstants.LEFT);
        labelFarba.setOpaque(true);
        this.txfFarba = new JTextField();
        this.txfFarba.setPreferredSize(new Dimension(100, 20));
        this.txfFarba.setEditable(false);

        JLabel labelPeniaze = new JLabel("Peniaze:", SwingConstants.LEFT);
        labelPeniaze.setOpaque(true);
        this.txfPeniaze = new JTextField();
        this.txfPeniaze.setPreferredSize(new Dimension(100, 20));
        this.txfPeniaze.setEditable(false);

        JPanel pnlHornyPanel = new JPanel();
        pnlHornyPanel.setLayout(new BoxLayout(pnlHornyPanel, BoxLayout.PAGE_AXIS));
        JPanel hracPanel = new JPanel();
        hracPanel.add(labelHrac);
        hracPanel.add(this.txfHrac);
        hracPanel.setMaximumSize(new Dimension(200, 30));
        pnlHornyPanel.add(hracPanel);

        JPanel farbaPanel = new JPanel();
        farbaPanel.add(labelFarba);
        farbaPanel.add(this.txfFarba);
        farbaPanel.setMaximumSize(new Dimension(200, 30));
        pnlHornyPanel.add(farbaPanel);

        JPanel peniazePanel = new JPanel();
        peniazePanel.add(labelPeniaze);
        peniazePanel.add(this.txfPeniaze);
        peniazePanel.setMaximumSize(new Dimension(200, 30));
        pnlHornyPanel.add(peniazePanel);

        this.txaTextovePole = new JTextArea();
        this.txaTextovePole.setColumns(22);
        this.txaTextovePole.setRows(20);
        this.txaTextovePole.setLineWrap(true);
        this.txaTextovePole.setWrapStyleWord(true);
        this.txaTextovePole.setEditable(false);

        JPanel pnlBocnyPanel = new JPanel();
        pnlBocnyPanel.setLayout(new BoxLayout(pnlBocnyPanel, BoxLayout.PAGE_AXIS));
        pnlBocnyPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        pnlBocnyPanel.add(pnlHornyPanel);

        JScrollPane scroll = new JScrollPane(this.txaTextovePole);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel pnlGridPanel = new JPanel();
        pnlGridPanel.setLayout(new GridLayout(3, 1, 4, 4));
        pnlGridPanel.add(this.btnHodKockou);
        pnlGridPanel.add(this.btnVlastnenePolicka);
        pnlGridPanel.add(this.btnPredajPolicko);

        pnlBocnyPanel.add(scroll);
        pnlBocnyPanel.add(pnlGridPanel);

        this.labelStred = new JLayeredPane();
        this.labelStred.setMinimumSize(new Dimension(500, 500));
        this.labelStred.setPreferredSize(new Dimension(500, 500));
        this.labelStred.setOpaque(true);
        this.labelStred.setBounds(0, 0, 1000, 800);

        StretchIcon imgHraciaPlocha = new StretchIcon("obrazky/hraciaPlocha.jpg");
        JLabel hraciaP = new JLabel();
        hraciaP.setBounds(0, 0, this.labelStred.getWidth(), this.labelStred.getHeight());
        hraciaP.setIcon(imgHraciaPlocha);
        this.labelStred.add(hraciaP, 0, 0);
        this.poleFigurok = new JLabel[2];

        this.okno.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                hraciaP.setBounds(0, 0, GUI.this.labelStred.getWidth(), GUI.this.labelStred.getHeight());
                GUI.this.presunVsetkyFigurky();
            }
        });

        pnlBocnyPanel.add(pnlGridPanel);
        hlavnyKontajner.add(this.labelStred, BorderLayout.CENTER);
        hlavnyKontajner.add(pnlBocnyPanel, BorderLayout.EAST);

        this.okno.pack();
        this.okno.setLocationRelativeTo(null);
        this.okno.setVisible(true);
        this.okno.validate();
    }

    /**
     * Vrati instanciu tejto triedy
     * @return instancia triedy GUI
     */
    public static GUI getInstancia() {
        if (GUI.instancia == null) {
            GUI.instancia = new GUI();
        }
        return GUI.instancia;
    }

    /**
     * Vola metoda na zaklade udalosti zadanej ako parameter
     * @param e konkretna udalost
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.novaHraMenu) {
            this.vytvorNovuHru();
            //this.experiment();
        } else if (e.getSource() == this.nacitajHruMenu) {
            this.nacitajHru();
        } else if (e.getSource() == this.ulozHruMenu) {
            this.ulozHru();
        } else if (e.getSource() == this.koniecMenu) {
            this.koniecHry();
        } else if (e.getSource() == this.btnHodKockou) {
            this.vykonajHodKockou();
        } else if (e.getSource() == this.btnVlastnenePolicka) {
            this.vypisVlastnenePolicka();
        } else if (e.getSource() == this.btnPredajPolicko) {
            this.predajPolicka();
        }
    }

    /**
     * Presunie figurku na zadanu poziciu hracej plochy
     * @param figurka index figurky ktoru chceme presunut
     * @param pozicia pozicia na ktoru chceme presunut figurku
     */
    public void presunFigurku(int figurka, int pozicia) {
        this.poleFigurok[figurka].setBounds(GUI.this.getXFigurky(pozicia), GUI.this.getYFigurky(pozicia),
                GUI.this.labelStred.getWidth() / 12,
                GUI.this.labelStred.getHeight() / 12);
    }

    /**
     * Zobrazi dialogove okno s textom zadanym ako parameter
     * @param sprava text ktory sa zobrazi hracovi
     * @param nazov nazov okna ktore sa zobrazi
     * @param zobrazInfo zobrazenie tretieho tlacitka na zobrazenie informacii o podniku
     * @return konkretna celociselna hodnota volby hraca
     */
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

    /**
     * Umozni hracovi predat jedno z jeho vlastnenych policok
     */
    public void predajPolicka() {
        Hrac hrac = this.hra.getAktHrac();
        int cisloPolicka;
        this.vypisVlastnenePolicka();
        String cisloPolickaString = JOptionPane.showInputDialog("Zadaj cislo policka na predaj: ");
        if (cisloPolickaString == null) {
            return;
        }
        try {
            cisloPolicka = Integer.parseInt(cisloPolickaString);
            if (cisloPolicka > 0 && cisloPolicka <= hrac.getPocetVlastnenych()) {
                System.out.println(hrac.predajPolicko(cisloPolicka));
                this.txfPeniaze.setText("" + this.hra.getAktHrac().getPeniaze());
            } else {
                System.out.println("Policko so zadanym cislom neexistuje");
            }
        } catch (NumberFormatException e) {
            System.out.println("Je potrebne zadat cislo");
        }
    }

    /**
     * Vytvori novu hru s poctom hracov ktore zada pouzivatel
     */
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

        this.vytvorFigurky(pocetHracov);
        this.hra = new Hra(pocetHracov, pocetPocitacov);
        this.pripravNaHru();
    }

    /**
     * Pripravi graficke rozhranie na spustenie novej hry
     */
    private void pripravNaHru() {
        this.btnHodKockou.setEnabled(true);
        this.btnVlastnenePolicka.setEnabled(true);
        this.btnPredajPolicko.setEnabled(true);
        this.ulozHruMenu.setVisible(true);

        this.txfHrac.setText(this.hra.getAktHrac().getMeno());
        this.txfPeniaze.setText("" + this.hra.getAktHrac().getPeniaze());
        this.txfFarba.setText("cervena");
    }

    /**
     * Vytvori a inicializuje pole figuriek - JLabel s ikonou
     * @param pocet pocet figuriek ktore chceme vytvorit
     */
    private void vytvorFigurky(int pocet) {
        this.vymazFigurky();
        this.poleFigurok = new JLabel[pocet];
        for (int i = 0; i < pocet; i++) {
            this.poleFigurok[i] = new JLabel();
            this.poleFigurok[i].setBounds(this.getXFigurky(0), this.getYFigurky(0),
                    this.labelStred.getWidth() / 12, this.labelStred.getHeight() / 12);
            this.poleFigurok[i].setIcon(new StretchIcon("obrazky/" + this.farby[i] + "Figurka.png"));
            this.labelStred.add(this.poleFigurok[i], i, 0);
        }
    }

    /**
     * Presunie vsetky figurky na ich aktualnu poziciu
     */
    private void presunVsetkyFigurky() {
        if (this.poleFigurok[0] != null) {
            int[] pozicie = this.hra.getPozicieHracov();
            for (int i = 0; i < this.poleFigurok.length; i++) {
                this.presunFigurku(i, pozicie[i]);
            }
        }
    }

    /**
     * Vykona hod kockou pokial sa hra este neskoncila
     */
    private void vykonajHodKockou() {
        if (!this.hra.koniecHry()) {
            this.hra.tah();
            this.txfHrac.setText(this.hra.getAktHrac().getMeno());
            this.txfPeniaze.setText("" + this.hra.getAktHrac().getPeniaze());
            this.txfFarba.setText("" + this.farby[this.hra.getCisloAktHraca()]);
        } else {
            this.btnHodKockou.setEnabled(false);
        }
    }

    /**
     * Vymaze (skryje) zobrazene figurky pokial uz nejake existuju
     */
    private void vymazFigurky() {
        if (this.poleFigurok[0] != null) {
            for (JLabel jLabel : this.poleFigurok) {
                jLabel.setVisible(false);
            }
        }
    }

    /**
     * Vrati novu X-ovu suradnicu figurky podla zadanej pozicie na hracej ploche
     * @param pozicia pozicia na hracej ploche na ktoru chceme figurku posunut
     * @return nova X-ova suradnica figurky
     */
    private int getXFigurky(double pozicia) {
        int sirka = this.labelStred.getWidth();
        int vyska = this.labelStred.getHeight();
        int sirkaObrazka = (int)(vyska * 1.4079646 * 0.91);
        double nasobok;

        if (pozicia < 12) {
            nasobok = pozicia / 13;
        } else if (pozicia <= 20) {
            nasobok = 0.95;
        } else if (pozicia < 32) {
            nasobok = 1 - (pozicia - 19) / 13;
        } else {
            nasobok = -0.05;
        }

        if (sirka / (double)vyska > 1.4079646) {
            return (int)(((sirka - sirkaObrazka) / 2) + sirkaObrazka * nasobok);
        } else {
            return (int)(sirka * nasobok * 0.91 + 40);
        }
    }

    /**
     * Vrati novu Y-ovu suradnicu figurky podla zadanej pozicie na hracej ploche
     * @param pozicia pozicia na hracej ploche na ktoru chceme figurku posunut
     * @return nova Y-ova suradnica figurky
     */
    private int getYFigurky(double pozicia) {
        int sirka = this.labelStred.getWidth();
        int vyska = this.labelStred.getHeight();
        int vyskaObrazka = (int)(sirka / 1.4079646 * 0.91);
        double nasobok;

        if (pozicia <= 12) {
            nasobok = 0.9;
        } else if (pozicia < 20) {
            nasobok = (1 - (pozicia - 10) / 11);
        } else if (pozicia <= 32) {
            nasobok = 0.02;
        } else {
            nasobok = (pozicia - 31) / 11;
        }

        if (sirka / (double)vyska > 1.4079646) {
            return (int)(vyska * nasobok);
        } else {
            return (int)(((vyska - vyskaObrazka) / 2) + vyskaObrazka * nasobok);
        }
    }

    /**
     * Odohra 100 hier s dvomi hracmi, vypise pocty kol v jednotlivych hrach a priemerny pocet kol
     */
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

    /**
     * Presunie vypisovania z terminalu do textoveho pola
     */
    private void vypisovanieDoTextovehoPola() {
        PrintStream printStream = new PrintStream(new VlastnyOutputStream(this.txaTextovePole));
        System.setOut(printStream);
        System.setErr(printStream);
    }

    /**
     * Vypis na obrazovku vlastnene policka aktualneho hraca
     */
    private void vypisVlastnenePolicka() {
        System.out.println("\n" + this.hra.getAktHrac().dajVlastnenePolicka());
    }

    /**
     * Nacita hru zo suboru
     */
    private void nacitajHru() {
        this.vypisovanieDoTextovehoPola();
        this.hra = SpravcaSuborov.nacitajHru();
        if (this.hra != null) {
            this.vytvorFigurky(this.hra.getPocetHracov());
            this.pripravNaHru();
            this.presunVsetkyFigurky();
            System.out.println("\nHra bola uspesne nacitana!");
        }
    }

    /**
     * Ulozi hru do suboru
     */
    private void ulozHru() {
        SpravcaSuborov.ulozHru(this.hra);
    }

    /**
     * Zobrazi potvrdenie o ukonceni hry a v pripade suhlasu hru ukonci
     */
    private void koniecHry() {
        int volba = this.zobrazMoznosti("Naozaj chces ukoncit hru?", "Koniec hry", false);
        if (volba == 0) {
            System.exit(0);
        }
    }

}
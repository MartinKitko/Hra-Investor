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
        this.setMinimumSize(new Dimension(1050, 650));
        this.setVisible(true);

        ImageIcon image = new ImageIcon("obrazky/logo.png");
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

        // presunutie vypisovania z terminalu do textoveho pola
        PrintStream printStream = new PrintStream(new VlastnyOutputStream(this.textovePole));
        System.setOut(printStream);
        System.setErr(printStream);

        this.bocnyPanel = new JPanel();
        //this.bocnyPanel.setBackground(Color.CYAN);
        //this.bocnyPanel.setLayout(new GridLayout(2, 1, 4, 4));
        this.bocnyPanel.setLayout(new BoxLayout(this.bocnyPanel, BoxLayout.PAGE_AXIS));
        this.bocnyPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        this.bocnyPanel.add(this.hornyPanel);

        JScrollPane scroll = new JScrollPane(this.textovePole);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.gridPanel = new JPanel();
        //this.gridPanel.setBackground(Color.RED);
        this.gridPanel.setLayout(new GridLayout(4, 1, 4, 4));
        this.gridPanel.add(this.hodKockou);
        /*this.gridPanel.add(this.kupitPodnik);
        this.gridPanel.add(this.zobrazInfo);
        this.gridPanel.add(this.ziadnaAkcia);*/
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
        ImageIcon image2 = new ImageIcon("obrazky/hraciaPlocha.jpg");
        /*Image imageScaled = image2.getImage();
        Image scaledImage = imageScaled.getScaledInstance(this.labelStred.getWidth(), this.labelStred.getHeight(), Image.SCALE_SMOOTH);
        this.labelStred.setIcon(new ImageIcon(scaledImage));*/
        this.labelStred.setIcon(image2);

        this.bocnyPanel.add(this.gridPanel);
        hlavnyKontajner.add(this.labelStred);
        hlavnyKontajner.add(this.bocnyPanel, BorderLayout.EAST);

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
            this.vytvorNovuHru();
        } else if (e.getSource() == this.nacitajHruMenu) {
            this.nacitajHru();
        } else if (e.getSource() == this.ulozHruMenu) {
            this.ulozHru();
        } else if (e.getSource() == this.koniecMenu) {
            this.koniecHry();
        } else if (e.getSource() == this.hodKockou) {
            this.vykonajHodKockou();
        }
    }

    public void klikniHodKockou() {
        this.hodKockou.doClick();
    }

    private void vytvorNovuHru() {
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

        int pocetPocitacov = 0;
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
        this.hodKockou.setEnabled(true);
        this.ulozHruMenu.setVisible(true);

        this.textHrac.setText(this.hra.getAktHrac().getMeno());
        this.textPeniaze.setText("" + this.hra.getAktHrac().getPeniaze());
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
                YaGson mapper = new YaGson();
                this.hra = mapper.fromJson(nacitanySuborString, Hra.class);
            }
        }
        this.hodKockou.setEnabled(true);
        System.out.println("Hra bola uspesne nacitana!");
    }

    private void ulozHru() {
        PrintWriter zapisovac = null;
        YaGson mapper = new YaGsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
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

    /*ImageIcon image2 = new ImageIcon("obrazky/hraciaPlocha.jpg");
    //Border border = BorderFactory.createLineBorder(Color.black, 2);
    label.setIcon(image2);
    //label.setHorizontalTextPosition(JLabel.CENTER);
    //label.setVerticalTextPosition(JLabel.TOP);
    //label.setForeground(new Color(0, 0, 0)); // farba textu
    //label.setFont(new Font("Arial", Font.PLAIN, 20));
    //label.setBorder(border);
    label.setVerticalAlignment(JLabel.CENTER);
    label.setHorizontalAlignment(JLabel.LEFT);

    this.novaHra.setFont(new Font("Arial", Font.BOLD, 15));
    this.novaHra.setBackground(Color.lightGray);
    this.novaHra.setBorder(BorderFactory.createEtchedBorder());
    this.button.addActionListener(e -> System.out.println("klik"));

    this.hodKockou.setFont(new Font("Arial", Font.BOLD, 15));
    this.hodKockou.setBackground(Color.lightGray);*/
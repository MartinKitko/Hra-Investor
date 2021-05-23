package sk.uniza.fri;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Scanner;

/**
 * Trieda SpravcaSuborov ktora sluzi na ukladanie hry a nacitanie hry zo suboru
 *
 * @author Martin Kitko
 * @version 23.5.2021
 */
public abstract class SpravcaSuborov {

    /**
     * Nacita hru zo suboru
     */
    public static Hra nacitajHru() {
        String nacitanySuborString = "";

        File priecinok = new File("ulozeneHry");
        File[] zoznamSuborov = priecinok.listFiles();
        System.out.println("Ulozene hry:");
        for (int i = 0; i < Objects.requireNonNull(zoznamSuborov).length; i++) {
            if (zoznamSuborov[i].isFile()) {
                System.out.println(zoznamSuborov[i].getName());
            }
        }

        File nacitanySubor = nacitajSubor();
        Hra hra = null;

        if (nacitanySubor == null) {
            return null;
        }

        try (Scanner citac = new Scanner(nacitanySubor)) {
            nacitanySuborString = citac.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("Hra so zadanym nazvom neexistuje");
        }
        if (!nacitanySuborString.equals("")) {
            YaGson mapper = new YaGson();
            hra = mapper.fromJson(nacitanySuborString, Hra.class);
        }
        return hra;
    }

    /**
     * Ulozi hru do suboru
     */
    public static void ulozHru(Hra hra) {
        PrintWriter zapisovac = null;
        YaGson mapper = new YaGsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .serializeNulls()
                .setVersion(1.0)
                .create();
        String json = mapper.toJson(hra, Hra.class);
        File nacitanySubor = nacitajSubor();
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

    /**
     * Vrati nacitany subor pre nacitanie alebo ulozenie hry
     * @return nacitany subor
     */
    private static File nacitajSubor() {
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
}

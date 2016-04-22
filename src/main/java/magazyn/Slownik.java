/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazyn;

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lombok.*;

/**
 *
 * @author xxbar
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Slownik {

    public enum Typ {
        B, N
    }

    private Integer GRUPA;
    private Integer PODGRUPA;
    private Integer RODZAJ;
    private Typ TYP;
    private String OPIS;
    private Integer NR_ODPADU;

    @Getter
    private static String[] propTym = {"GRUPA", "PODGRUPA", "RODZAJ", "TYP", "OPIS", "NR_ODPADU"};
    @Getter
    private static String[] colTym = {"GRUPA", "PODGRUPA", "RODZAJ", "TYP", "OPIS", "NR_ODPADU"};
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public Slownik(String GRUPA, String PODGRUPA, String RODZAJ, String TYP, String OPIS, String NR_ODPADU) {
        this.GRUPA = Integer.parseInt(GRUPA.replaceAll("[^\\d.]", ""));
        this.PODGRUPA = Integer.parseInt(PODGRUPA.replaceAll("[^\\d.]", ""));
        this.RODZAJ = Integer.parseInt(RODZAJ.replaceAll("[^\\d.]", ""));
        switch (TYP) {
            case "B":
                this.TYP = Typ.B;
                break;
            case "N":
                this.TYP = Typ.N;
                break;
            default:
                this.TYP = null;
        }
        this.OPIS = OPIS;
        this.NR_ODPADU = Integer.parseInt(NR_ODPADU.replaceAll("[^\\d.]", ""));

    }

    public Slownik(String row) {
        Scanner scan = new Scanner(row).useDelimiter(";");
        GRUPA = scan.hasNextInt() ? scan.nextInt() : null;
        if (GRUPA == null) {
            scan.next();
        }
        PODGRUPA = scan.hasNextInt() ? scan.nextInt() : null;
        if (PODGRUPA == null) {
            scan.next();
        }
        RODZAJ = scan.hasNextInt() ? scan.nextInt() : null;
        if (RODZAJ == null) {
            scan.next();
        }

        switch (scan.hasNext() ? scan.next() : "") {
            case "B":
                this.TYP = Typ.B;
                break;
            case "N":
                this.TYP = Typ.N;
                break;
            default:
                this.TYP = null;
        }

        if (this.TYP == null) {
            scan.next();
        }

        OPIS = scan.hasNext() ? scan.next() : null;
        if (OPIS == null) {
            scan.next();
        }
        NR_ODPADU = scan.hasNextInt() ? scan.nextInt() : null;

    }

 

    public static List<Slownik> Open(Path path) {

        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
            String firstLine = lines.remove(0);
            return lines.stream().map(row -> new Slownik(row)).collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println("Błąd odczytu pliku: \n" + ex);
            return null;
        }
    }

}

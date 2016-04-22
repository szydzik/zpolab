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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author xxbar
 */
public class Magazynp {

    public Integer NR_MAG;
    private NumerKarty NR_KARTY;
    private Integer NR_ODPADU;
    private Integer NR_KLIENTA;
    private Integer FIRMA;
    private String JEDN;
    private Double MASA;
    private java.sql.Date DATAD;
    private static String[] propTym = {"NR_MAG", "NR_KARTY", "NR_ODPADU", "NR_KLIENTA", "FIRMA", "JEDN", "MASA", "DATAD"};
    private static String[] colTym = {"NR_MAG", "NR_KARTY", "NR_ODPADU", "NR_KLIENTA", "FIRMA", "JEDN", "MASA", "DATAD"};
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
//    private String DATAD;

    public Magazynp() {
        this.NR_MAG = null;
        this.NR_KARTY = null;
        this.NR_ODPADU = null;
        this.NR_KLIENTA = null;
        this.FIRMA = null;
        this.JEDN = null;
        this.MASA = null;
        this.DATAD = null;
    }

    
    
    public Magazynp(Integer nr_mag, NumerKarty nr_karty, Integer nr_odpadu, Integer nr_klienta, Integer firma, String jedn, double masa, Date datad) {
        this.NR_MAG = nr_mag;
        this.NR_KARTY = nr_karty;
        this.NR_ODPADU = nr_odpadu;
        this.NR_KLIENTA = nr_klienta;
        this.FIRMA = firma;
        this.JEDN = jedn;
        this.MASA = masa;
        this.DATAD = datad;
    }

    public Magazynp(String NR_MAG, String NR_KARTY, String NR_ODPADU, String NR_KLIENTA, String FIRMA, String JEDN, String MASA, String datad) {
        this.NR_MAG = Integer.parseInt(NR_MAG.replaceAll("[^\\d.]", ""));

        String[] fields1 = NR_KARTY.split("/");
        this.NR_KARTY = new NumerKarty(Integer.parseInt(fields1[0]), Integer.parseInt(fields1[1]), Integer.parseInt(fields1[2]));

        this.NR_ODPADU = Integer.parseInt(NR_ODPADU.replaceAll("[^\\d.]", ""));
        this.NR_KLIENTA = Integer.parseInt(NR_KLIENTA.replaceAll("[^\\d.]", ""));
        this.FIRMA = Integer.parseInt(FIRMA.replaceAll("[^\\d.]", ""));
        this.JEDN = JEDN;
        this.MASA = Double.parseDouble(MASA.replaceAll("[^\\d.]", ""));
        try {
            this.DATAD = new Date(DATE_FORMAT.parse(datad).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Magazynp.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Błąd: " + ex);
        }

    }

    public Magazynp(String row) {

        Scanner scan = new Scanner(row).useDelimiter(";");

        NR_MAG = scan.hasNextInt() ? scan.nextInt() : null;
        if (NR_MAG == null) {
            scan.next();
        }

        NR_KARTY = new NumerKarty(scan.hasNext() ? scan.next() : null);
        if (NR_KARTY == null) {
            scan.next();
        }

        NR_ODPADU = scan.hasNextInt() ? scan.nextInt() : null;
        if (NR_ODPADU == null) {
            scan.next();
        }

        NR_KLIENTA = scan.hasNextInt() ? scan.nextInt() : null;
        if (NR_KLIENTA == null) {
            scan.next();
        }

        FIRMA = scan.hasNextInt() ? scan.nextInt() : null;
        if (FIRMA == null) {
            scan.next();
        }

        JEDN = scan.hasNext() ? scan.next() : null;
        if (JEDN == null) {
            scan.next();
        }

        this.MASA = scan.hasNext() ? Double.parseDouble(scan.next().replaceAll("[^\\d.]", "")) : null;
        if (MASA == null) {
            scan.next();
        }

        try {
            
             this.DATAD = scan.hasNext() ? new Date(DATE_FORMAT.parse(scan.next()).getTime()) : null;
                    
//            this.DATAD = new Date(DATE_FORMAT.parse(scan.hasNext() ? scan.next() : null).getTime());
        } catch (ParseException ex) {
            this.DATAD = null;
            System.out.println("Błąd: " + ex);
        }
    }

    public Integer getNR_MAG() {
        return NR_MAG;
    }

    public void setNR_MAG(Integer nr_mag) {
        this.NR_MAG = nr_mag;
    }

    public NumerKarty getNR_KARTY() {
        return NR_KARTY;
    }

    public void setNR_KARTY(NumerKarty NR_KARTY) {
        this.NR_KARTY = NR_KARTY;
    }

    public Integer getNR_ODPADU() {
        return NR_ODPADU;
    }

    public void setNR_ODPADU(Integer NR_ODPADU) {
        this.NR_ODPADU = NR_ODPADU;
    }

    public Integer getNR_KLIENTA() {
        return NR_KLIENTA;
    }

    public void setNR_KLIENTA(Integer NR_KLIENTA) {
        this.NR_KLIENTA = NR_KLIENTA;
    }

    public Integer getFIRMA() {
        return FIRMA;
    }

    public void setFIRMA(Integer FIRMA) {
        this.FIRMA = FIRMA;
    }

    public String getJEDN() {
        return JEDN;
    }

    public void setJEDN(String JEDN) {
        this.JEDN = JEDN;
    }

    public double getMASA() {
        return MASA;
    }

    public void setMASA(double MASA) {
        this.MASA = MASA;
    }

    public Date getDATAD() {
        return DATAD;
    }

    public void setDATAD(Date DATAD) {
        this.DATAD = DATAD;
    }

    public static String[] getPropTym() {
        return propTym;
    }

    public static void setPropTym(String[] propTym) {
        Magazynp.propTym = propTym;
    }

    public static String[] getColTym() {
        return colTym;
    }

    public static void setColTym(String[] colTym) {
        Magazynp.colTym = colTym;
    }

    @Override
    public String toString() {
        return "Magazynp{" + "NR_MAG=" + NR_MAG + ", NR_KARTY=" + NR_KARTY + ", NR_ODPADU=" + NR_ODPADU + ", NR_KLIENTA=" + NR_KLIENTA + ", FIRMA=" + FIRMA + ", JEDN=" + JEDN + ", MASA=" + MASA + ", DATAD=" + DATAD + '}';
    }

    public static List<Magazynp> Open(Path path) {
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
            String firstLine = lines.remove(0);
            return lines.stream().map(row -> new Magazynp(row)).collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println("Błąd odczytu pliku: \n" + ex);
            return null;
        }
    }
}

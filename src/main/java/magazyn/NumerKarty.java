/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazyn;

import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author xxbar
 */
public class NumerKarty implements Comparable<NumerKarty> {

    private Integer NUMER;
    private Integer FIRMA;
    private Integer ROK;

    public NumerKarty() {
        this.NUMER = null;
        this.FIRMA = null;
        this.ROK = null;
    }

    public NumerKarty(Integer NUMER, Integer FIRMA, Integer ROK) {
        this.NUMER = NUMER;
        this.FIRMA = FIRMA;
        this.ROK = ROK;
    }

    public NumerKarty(String s) {
        Scanner scan = new Scanner(s).useDelimiter("/");
        NUMER = scan.hasNextInt() ? scan.nextInt() : null;
        if (NUMER == null) {
            scan.next();
        }
        FIRMA = scan.hasNextInt() ? scan.nextInt() : null;
        if (FIRMA == null) {
            scan.next();
        }
        ROK = scan.hasNextInt() ? scan.nextInt() : null;
    }

    public Integer getNUMER() {
        return NUMER;
    }

    public void setNUMER(Integer NUMER) {
        this.NUMER = NUMER;
    }

    public Integer getFIRMA() {
        return FIRMA;
    }

    public void setFIRMA(Integer FIRMA) {
        this.FIRMA = FIRMA;
    }

    public Integer getROK() {
        return ROK;
    }

    public void setROK(Integer ROK) {
        this.ROK = ROK;
    }

    @Override
    public String toString() {
        return NUMER + "/" + FIRMA + "/" + ROK;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final NumerKarty other = (NumerKarty) obj;
        
        if (!Objects.equals(this.NUMER, other.NUMER)) {
            return false;
        }
        if (!Objects.equals(this.FIRMA, other.FIRMA)) {
            return false;
        }
        return Objects.equals(this.ROK, other.ROK);
    }

    @Override
    public int compareTo(NumerKarty t) {

        if (ROK.compareTo(t.getROK()) != 0) {
            return ROK.compareTo(t.getROK());
        }

        if (FIRMA.compareTo(t.getFIRMA()) != 0) {
            return FIRMA.compareTo(t.getFIRMA());
        }

        if (NUMER.compareTo(t.getNUMER()) != 0) {
            return NUMER.compareTo(t.getNUMER());
        }

        return 0;

    }

}

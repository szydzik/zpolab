/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazyn;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author xxbar
 * @param <T>
 */
public class DataModel<T> extends AbstractTableModel {

    private List<T> rows;

    private Class<T> clazz;
    
    private String[] colNames;
    private String[] propNames;

    public static class Accessors {

        public Method read; //getter
        public Method write;//seter
    }
    private Map<String, Accessors> accMap = new HashMap<>();

    public DataModel() {
        
    }
    
    public DataModel(List<T> objsList, String[] colNames, String[] propNames) {

        this.rows = objsList;
        this.colNames = new String[colNames.length];
        System.arraycopy(colNames, 0, this.colNames, 0, colNames.length);

        this.propNames = new String[propNames.length];
        System.arraycopy(propNames, 0, this.propNames, 0, propNames.length);

        //Inicjacja mapy akcerorow. Na razie gettery i settery sa rowne null
        for (String propName : propNames) {
            accMap.put(propName, new Accessors());
        }

        Class klas = objsList.get(0).getClass();

        try {
            PropertyDescriptor[] pd = Introspector.getBeanInfo(klas, klas.getSuperclass()).getPropertyDescriptors();
            for (PropertyDescriptor desc : pd) {
                String name = desc.getName();
//                System.out.println("desc.getName()=" + desc.getName());
                Accessors acc = accMap.get(name);
                // uwzgledniamy tylko wlasciwosci przekazane w tablicy propNames
                if (acc != null) {
                    acc.read = desc.getReadMethod();
//                    System.out.println(" acc.read=" + acc.read);
                    acc.write = desc.getWriteMethod();
//                    System.out.println("acc.write=" + acc.write);
                }
            }

            fireTableDataChanged();

        } catch (IntrospectionException exc) {
            System.out.println("Bląd: \n" + exc);
        }
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        T obj = rows.get(rowIndex);//ziarno
        Object retVal = null;
        // Wywolanie gettera przez reffleksje
        try {

            Method getter = accMap.get(propNames[columnIndex]).read;

            retVal = getter.invoke(obj);

        } catch (java.lang.NullPointerException xx) {
            System.out.println("Brak gettera dla " + propNames[columnIndex]);
            return (Object) " ";
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exc) {
            System.out.println("Błąd: \n" + exc);
        }

        return retVal;

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        try {
            if (getValueAt(0, columnIndex).getClass() != null) {
                return getValueAt(0, columnIndex).getClass();
            } else {
                return (new Object()).getClass();
            }
        } catch (java.lang.NullPointerException ex) {
            System.out.println("java.lang.NullPointerException  columnIndex=" + columnIndex);
            return (new Object()).getClass();

        }

    }

    @Override
    public String getColumnName(int columnIndex) {
        return colNames[columnIndex];

    }

    public void add(T obj) {
        rows.add(obj);
        fireTableDataChanged();
    }

    public T getRow(int row) {
        return rows.get(row);
    }

    public void setRow(T obj, int row) {
        rows.set(row, obj);
        fireTableDataChanged();
    }

    public T remove(int row) {
        if (row < 0 || row >= rows.size()) {
            return null;
        }
        T temp = rows.remove(row);
        fireTableDataChanged();
        return temp;
    }

    public List<T> getRows() {
        return rows;
    }
    

        


}

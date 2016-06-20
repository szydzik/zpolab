/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Bartek
 */
public class Verifier extends InputVerifier {

    Boolean allowZero;
    String message;
    Boolean valueToReturn;

    public Verifier(Boolean allowZero) {
        this.allowZero = allowZero;
        this.message = new String();
        this.valueToReturn = false;
    }

    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        try {
            Double d = Double.parseDouble(text);
//            valid(input);
            if (!allowZero) {
                if (d == 0) {
                    invalid(input);
                } else {
                    valid(input);
                }
            } else {
                valid(input);
            }
        } catch (NumberFormatException e) {
            System.out.println("błąd: " + e);
            invalid(input);
//            return false;
        }

        return valueToReturn;
    }

    private void valid(JComponent input) {
        input.setBackground(Color.green);
        valueToReturn = true;
    }

    private void invalid(JComponent input) {
        input.setBackground(Color.red);
        valueToReturn = false;
    }

    public boolean validacja(javax.swing.JTextField textField, javax.swing.JLabel label) {
        if (this.verify(textField)) {
            textField.setBackground(Color.green);
            label.setText("");

            if ((Double.parseDouble(textField.getText()) > 1000) || (Double.parseDouble(textField.getText()) < -30)) {
                textField.setBackground(Color.yellow);

                return false;
            }

            if (Double.parseDouble(textField.getText()) == 0) {
                textField.setBackground(Color.yellow);
                label.setText("Nie mozna dzielić przez 0");
                return false;
            }

        } else if (textField.getText().isEmpty()) {
            textField.setBackground(Color.red);
            label.setText("Pole nie może być puste !");
            return false;
        } else {
            textField.setBackground(Color.red);
            label.setText("Zła wartość");
            return false;
        }
        return true;
    }

}

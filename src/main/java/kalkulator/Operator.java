/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

/**
 *
 * @author Bartek
 */
//public class Operator{
public enum Operator {

    PLUS("+"), MINUS("-"), TIMES("*"), DIVIDE("/");
    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    
    @Override
    public String toString() {
        return symbol;
    }
}

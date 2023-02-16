/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.JFrame;

/**
 * Pokretanje kalkulatora.
 * @author Ivana
 */
public class App {
    /**
     * Poziv konstrukcije kalkulatora.
     * @param args argumenti 
     */
    public static void main(String args[]){
        CalculatorFrame okvir=new CalculatorFrame();
        okvir.setSize(800, 600);
        okvir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okvir.setVisible(true);
    }
}

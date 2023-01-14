/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Ivana
 */
public class CalculatorFrame extends JFrame{
    public CalculatorFrame(){
        setTitle("Kalkulator");
        CalculatorGUI spremnik = new CalculatorGUI();
        spremnik.setSize(800,400);
        spremnik.setPreferredSize(new Dimension(800,400));
        add(spremnik);
        pack();
    }
}

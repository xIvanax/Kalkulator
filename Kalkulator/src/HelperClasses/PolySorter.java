/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HelperClasses;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;
/**
 *
 * @author Ivana
 */
public class PolySorter {
    JPanel parent;
    
    public PolySorter(JPanel parent){
        this.parent=parent;
    }
    
    public void sortPolynomial(ArrayList<String> poly){
        Collections.sort(poly, new KomparatorClanova(parent));
    }
}

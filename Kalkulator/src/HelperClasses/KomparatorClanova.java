/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HelperClasses;

import HelperClasses.PolyOps;
import java.util.Comparator;
import javax.swing.JPanel;
/**
 * Omogućuje usporedbu članova polinoma (reprezentiranih u obliku String-a) na temelju njihovog eksponenta.
 * @author Ivana
 */
public class KomparatorClanova implements Comparator<String>{
    JPanel parent;
    
    public KomparatorClanova(JPanel parent){
        this.parent=parent;
    }

    @Override
    public int compare(String o1, String o2) {
        if(PolyOps.coefAndExp(o1,parent)[1]<PolyOps.coefAndExp(o2, parent)[1])
            return -1;
        else if(PolyOps.coefAndExp(o1,parent)[1]>PolyOps.coefAndExp(o2, parent)[1])
            return 1;
        else
            return 0;
    }
}

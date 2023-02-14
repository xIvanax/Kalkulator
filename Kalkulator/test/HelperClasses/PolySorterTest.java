/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package HelperClasses;

import java.util.ArrayList;
import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dorotea
 */
public class PolySorterTest {
    
    public PolySorterTest() {
    }

    /**
     * Test of sortPolynomial method, of class PolySorter.
     */
    @Test
    public void testSortPolynomial(){
        JPanel parent=new JPanel();
        PolyOps poly=new PolyOps();
        PolySorter ps=new PolySorter(parent);
        ArrayList<String> p=poly.dohvati("1+3*x^4+x^7-2*x^3");
        ps.sortPolynomial(p);
        ArrayList<String> expected=new ArrayList<>();
        expected.add("1");
        expected.add("-2*x^3");
        expected.add("3*x^4");
        expected.add("x^7");
        assertEquals(expected,p);
    }
    
}

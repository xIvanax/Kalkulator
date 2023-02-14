/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Grapher.Expressions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dorotea
 */
public class QuotientTest {
    
    public QuotientTest() {
    }

    /**
     * Test of getValue method, of class Quotient.
     */
    @Test
    public void testGetValue() {
        Quantity q1=new Number(8);
        Quantity q2=new Number(2);
        Quotient result=new Quotient(q1,q2);
        double expected=4;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}

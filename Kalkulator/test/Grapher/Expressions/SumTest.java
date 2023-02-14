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
public class SumTest {
    
    public SumTest() {
    }

    /**
     * Test of getValue method, of class Sum.
     */
    @Test
    public void testGetValue() {
        Quantity q1=new Number(2);
        Quantity q2=new Number(8);
        Sum result=new Sum(q1,q2);
        double expected=10;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}

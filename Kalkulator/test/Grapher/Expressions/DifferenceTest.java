/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Grapher.Expressions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorot
 */
public class DifferenceTest {
    
    public DifferenceTest() {
    }

    /**
     * Test of getValue method, of class Difference.
     */
    @Test
    public void testGetValue() {
        Quantity q1=new Number(7),q2=new Number(2);
        Difference result=new Difference(q1,q2);
        double expected=5;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}

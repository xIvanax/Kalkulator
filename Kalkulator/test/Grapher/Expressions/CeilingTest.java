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
public class CeilingTest {
    
    public CeilingTest() {
    }

    /**
     * Test of getValue method, of class Ceiling.
     */
    @Test
    public void testGetValue() {
        Quantity q=new Number(0.5);
        Ceiling result=new Ceiling(q);
        double expected=1;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}

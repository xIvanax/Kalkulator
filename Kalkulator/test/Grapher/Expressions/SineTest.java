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
public class SineTest {
    
    public SineTest() {
    }

    /**
     * Test of getValue method, of class Sine.
     */
    @Test
    public void testGetValue() {
        Quantity q=new Number(Math.PI);
        Sine result=new Sine(q);
        double expected=0;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}

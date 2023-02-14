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
public class ArcctanTest {
    
    public ArcctanTest() {
    }

    /**
     * Test of getValue method, of class Arcctan.
     */
    @Test
    public void testGetValue() {
        Quantity q=new Number(0);
        Arcctan result=new Arcctan(q);
        double expected=Math.PI/2-Math.atan(0);
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}

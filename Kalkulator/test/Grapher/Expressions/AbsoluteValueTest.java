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
public class AbsoluteValueTest {
    
    public AbsoluteValueTest() {
    }

    /**
     * Test of getValue method, of class AbsoluteValue.
     */
    @Test
    public void testGetValue() {
        Quantity q=new Number(-15.6);
        AbsoluteValue result=new AbsoluteValue(q);
        double expected=15.6;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}

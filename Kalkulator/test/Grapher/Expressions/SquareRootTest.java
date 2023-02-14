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
public class SquareRootTest {
    
    public SquareRootTest() {
    }

    /**
     * Test of getValue method, of class SquareRoot.
     */
    @Test
    public void testGetValue() {
        Quantity q=new Number(81);
        SquareRoot result=new SquareRoot(q);
        double expected=9;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
    
}

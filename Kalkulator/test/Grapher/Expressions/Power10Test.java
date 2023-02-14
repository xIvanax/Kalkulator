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
public class Power10Test {
    
    public Power10Test() {
    }

    /**
     * Test of getValue method, of class Power10.
     */
    @Test
    public void testGetValue(){
        Quantity q=new Number(2);
        Power10 result=new Power10(q);
        double expected=100;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}

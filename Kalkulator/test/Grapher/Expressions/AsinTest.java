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
public class AsinTest {
    
    public AsinTest() {
    }

    /**
     * Test of getValue method, of class Asin.
     */
    @Test
    public void testGetValue(){
        Quantity q=new Number(1);
        Asin result=new Asin(q);
        double expected=Double.POSITIVE_INFINITY;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}
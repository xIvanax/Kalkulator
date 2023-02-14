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
public class PowerTest {
    
    public PowerTest(){}

    /**
     * Test of getValue method, of class Power.
     */
    @Test
    public void testGetValue(){
        Quantity broj=new Number(2);
        Quantity eksponent=new Number(5);
        Power result=new Power(broj,eksponent);
        double expected=32;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}
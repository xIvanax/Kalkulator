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
public class AcosTest {
    
    public AcosTest() {
    }

    /**
     * Test of getValue method, of class Acos.
     */
    @Test
    public void testGetValue() {
        Quantity q=new Number(0.5);
        Acos result=new Acos(q);
        double expected=Math.PI/3;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}

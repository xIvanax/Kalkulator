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
public class Natural_logTest {
    
    public Natural_logTest() {
    }

    /**
     * Test of getValue method, of class Natural_log.
     */
    @Test
    public void testGetValue(){
        Quantity q=new Number(Math.E);
        Natural_log result=new Natural_log(q);
        double expected=1;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}


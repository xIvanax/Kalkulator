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
public class NthRootTest {
    
    public NthRootTest() {
    }

    /**
     * Test of getValue method, of class NthRoot.
     */
    @Test
    public void testGetValue(){
        Quantity eksponent=new Number(3);
        Quantity korijen=new Number(8);
        NthRoot result=new NthRoot(korijen,eksponent);
        double expected=2;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}


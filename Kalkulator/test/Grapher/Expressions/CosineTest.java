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
public class CosineTest {
    
    public CosineTest() {
    }

    /**
     * Test of getValue method, of class Cosine.
     */
    @Test
    public void testGetValue(){
        Quantity q=new Number(0);
        Cosine result=new Cosine(q);
        double expected=1;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}
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
public class ProductTest {
    
    public ProductTest() {
    }

    /**
     * Test of getValue method, of class Product.
     */
    @Test
    public void testGetValue() {
        Quantity q1=new Number(2);
        Quantity q2=new Number(8);
        Product result=new Product(q1,q2);
        double expected=16;
        assertEquals(expected,result.getValue(),0.000001);
    }
    
}

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
public class FunctionTest {
    
    public FunctionTest() {
    }

    /**
     * Test of evaluateAt method, of class Function.
     */
    @Test
    public void testEvaluateAt(){
        Quantity root=new Number(0);
        Variable x=new Variable();
        Function f=new Function(root,x);
        double result=f.evaluateAt(2);
        double expected=0;
        assertEquals(expected,result,0.000001);
        
    }
    
}

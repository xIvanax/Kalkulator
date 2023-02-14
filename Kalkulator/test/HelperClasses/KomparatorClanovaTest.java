/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package HelperClasses;

import java.util.ArrayList;
import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dorotea
 */
public class KomparatorClanovaTest {
    
    public KomparatorClanovaTest() {
    }

    /**
     * Test of compare method, of class KomparatorClanova.
     */
    @Test
    public void testCompare(){
        JPanel parent=new JPanel();
        KomparatorClanova kc=new KomparatorClanova(parent);
        String p1="3*x^5";
        String p2="2*x";
        int result=kc.compare(p1, p2);
        int expected=1;
        assertEquals(expected,result);
    }
    
}

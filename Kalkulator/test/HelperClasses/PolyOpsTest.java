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
public class PolyOpsTest {
    
    public PolyOpsTest() {}
    
    /**
     * Test of scanFromRight method, of class PolyOps.
     */
    @Test
    public void testScanFromRight() {
        PolyOps po=new PolyOps();
        int result=po.scanFromRight("x+3*x^4-5", '-');
        int expected=7;
        assertEquals(expected,result);
    }

    /**
     * Test of dohvati method, of class PolyOps.
     */
    @Test
    public void testDohvati(){
        PolyOps po=new PolyOps();
        ArrayList<String> result=po.dohvati("3*x^4-2*x^3+1");
        ArrayList<String> expected=new ArrayList<>();
        expected.add("1");
        expected.add("-2*x^3");
        expected.add("3*x^4");
        assertEquals(expected,result);
    }

    /**
     * Test of polyMulti method, of class PolyOps.
     */
    @Test
    public void testPolyMulti() {
        PolyOps poly1=new PolyOps();
        PolyOps poly2=new PolyOps();
        PolyOps po=new PolyOps();
        JPanel parent=new JPanel();
        ArrayList<String> p1=poly1.dohvati("3*x^4-2*x^3+1");
        ArrayList<String> p2=poly2.dohvati("x+1");
        ArrayList<String> result=po.polyMulti(p1, p2,parent);
        ArrayList<String> expected=new ArrayList<>();
        expected.add("1.0");
        expected.add("1.0*x^1.0");
        expected.add("-2.0*x^3.0");
        expected.add("1.0*x^4.0");
        expected.add("3.0*x^5.0");
        assertEquals(expected,result);
    }

    /**
     * Test of polyAdd method, of class PolyOps.
     */
    @Test
    public void testPolyAdd() {
        PolyOps poly1=new PolyOps();
        PolyOps poly2=new PolyOps();
        PolyOps po=new PolyOps();
        JPanel parent=new JPanel();
        ArrayList<String> p1=poly1.dohvati("3*x^4-2*x^3+1");
        ArrayList<String> p2=poly2.dohvati("x+1");
        ArrayList<String> result=po.polyAdd(p1, p2,parent);
        ArrayList<String> expected=new ArrayList<>();
        expected.add("2.0");
        expected.add("1.0*x^1.0");
        expected.add("-2.0*x^3.0");
        expected.add("3.0*x^4.0");
        assertEquals(expected,result);
    }

    /**
     * Test of coefAndExp method, of class PolyOps.
     */
    @Test
    public void testCoefAndExp(){
        PolyOps po=new PolyOps();
        JPanel parent=new JPanel();
        double[] result=po.coefAndExp("3*x^4",parent);
        double[] expected={3,4};
        assertEquals(expected[0],result[0],0.000001);
        assertEquals(expected[1],result[1],0.000001);
    }

    /**
     * Test of Der method, of class PolyOps.
     */
    @Test
    public void testDer(){
        PolyOps po=new PolyOps();
        JPanel parent=new JPanel();
        String s="3*x^4";
        String result=po.Der(s,parent);
        String expected="12.0*x^3.0";
        assertEquals(expected,result);
    }

    /**
     * Test of deriviraj method, of class PolyOps.
     */
    @Test
    public void testUrediDeriviraj(){
        PolyOps po=new PolyOps();
        JPanel parent=new JPanel();
        String s="3*x^4+2*x^3+x+1";
        String result=po.urediDeriviraj(s,parent);
        String expected="12.0*x^3.0+6.0*x^2.0+1.0";
        assertEquals(expected,result);
    }

    /**
     * Test of combineLikeTerms method, of class PolyOps.
     */
    @Test
    public void testCombineLikeTerms(){
        PolyOps po=new PolyOps();
        String s="3*x^4+2*x^3+x+x+x+x+x^4";
        ArrayList<String> poly=po.dohvati(s);
        JPanel parent=new JPanel();
        ArrayList<String> result=po.combineLikeTerms(poly, parent);
        ArrayList<String> expected=new ArrayList<>();
        expected.add("4.0*x^1.0");
        expected.add("2.0*x^3.0");
        expected.add("4.0*x^4.0");
        assertEquals(expected,result); 
    }

    /**
     * Test of maxDegree method, of class PolyOps.
     */
    @Test
    public void testMaxDegree(){
        PolyOps po=new PolyOps();
        String s="3*x^4+2*x^3+x+x+x+x+x^4";
        ArrayList<String> poly=po.dohvati(s);
        JPanel parent=new JPanel();
        double result=po.maxDegree(poly, parent);
        double expected=4.0;
        assertEquals(expected,result,0.000001); 
    }
    /**
     * Test of uredi method, of class PolyOps.
     */
    @Test
    public void testPozivUredi(){
        PolyOps po=new PolyOps();
        String s="(3+x^3)-(x+x)";
        JPanel parent=new JPanel();
        String result=po.pozivUredi(s,parent);
        String expected="3+x^3-x-x";
        assertEquals(expected,result);
    }

    /**
     * Test of starFormat method, of class PolyOps.
     */
    @Test
    public void testStarFormat(){
        PolyOps po=new PolyOps();
        String s="2x+3x^2";
        JPanel parent=new JPanel();
        ArrayList<String> list=po.dohvati(s);
        ArrayList<String> result=po.starFormat(list);
        ArrayList<String> expected=new ArrayList<>();
        expected.add("3*x^2");
        expected.add("2*x");
        assertEquals(expected,result);
    }

    /**
     * Test of polyPot method, of class PolyOps.
     */
    @Test
    public void testPolyPot() {
        PolyOps po=new PolyOps();
        JPanel parent=new JPanel();
        String s="x+1";
        //String s="3*x-2";
        ArrayList<String> list=po.dohvati(s);
        ArrayList<String> result=po.polyPot(list,3,parent);
        ArrayList<String> expected=new ArrayList<>();
        expected.add("1.0");
        expected.add("3.0*x^1.0");
        expected.add("3.0*x^2.0");
        expected.add("1.0*x^3.0");
//        expected.add("-8.0");
//        expected.add("36.0*x^1.0");
//        expected.add("-54.0*x^2.0");
//        expected.add("27.0*x^3.0");
        System.out.println("Rezultat je: "+result);
        assertEquals(expected,result);
    }
}

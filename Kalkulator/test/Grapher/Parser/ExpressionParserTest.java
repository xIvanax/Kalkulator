/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Grapher.Parser;

import Grapher.Expressions.Function;
import Grapher.Expressions.Quantity;
import static Grapher.Parser.TokenType.ARCSINE;
import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertArrayEquals;
/**
 *
 * @author Dorotea
 */
public class ExpressionParserTest {
    
    public ExpressionParserTest() {}
    
    @Test
    public void tokenizeTest(){
        ExpressionParser ep=new ExpressionParser(new JPanel());
        System.out.println("Test funkcije tokenize");
        String s="7*ln(x)+tan(x)-3x";
        TokenString expected=new TokenString();
        expected.addToken(new Token(TokenType.NUMBER));
        expected.addToken(new Token(TokenType.TIMES));
        expected.addToken(new Token(TokenType.NATURAL_LOG));
        expected.addToken(new Token(TokenType.OPEN_PARENTHESES));
        expected.addToken(new Token(TokenType.X));
        expected.addToken(new Token(TokenType.CLOSE_PARENTHESES));
        expected.addToken(new Token(TokenType.PLUS));
        expected.addToken(new Token(TokenType.TANGENT));
        expected.addToken(new Token(TokenType.OPEN_PARENTHESES));
        expected.addToken(new Token(TokenType.X));
        expected.addToken(new Token(TokenType.CLOSE_PARENTHESES));
        expected.addToken(new Token(TokenType.MINUS));
        expected.addToken(new Token(TokenType.X));
        expected.addToken(new Token(TokenType.NUMBER));
        TokenString result=ep.tokenize(s);
        assertEquals(expected.getLength(),result.getLength());
        for(int i=0; i<expected.getLength(); i++){
            assertEquals(expected.tokenAt(i),expected.tokenAt(i));
        }  
    }
    
    /**
    * @Dorotea 
    * prvo testiramo funkciju u kojoj pretrazujemo lokaciju samo jednog tipa tokena
    * npr. otvorenu zagradu ili +
    */
    @Test
    public void scanFromRightTest(){
        ExpressionParser ep=new ExpressionParser(new JPanel());
        System.out.println("Test funkcije scanFromRight");
        String s="7*ln(x)+tan(x)-3x";
        TokenString ts=ep.tokenize(s);
        int result=ep.scanFromRight(ts, TokenType.TIMES);
        int expected=1;
        assertEquals(expected,result);
    }
    
    /**
    * @Dorotea
    * testiramo scanFromRight koja u ovom slucaju prima polje tokena
    */
    @Test
    public void scanFromRight(){
        ExpressionParser ep=new ExpressionParser(new JPanel());
        System.out.println("Test funkcije scanFromRight");
        String s="sin(x)+cos(x)+x";
        TokenString ts=ep.tokenize(s);
        int result=ep.scanFromRight(ts, TokenType.FUNCTIONS);
        /**
        * jer je sin 0, ( je 1, x je 2, = je 3, + je 4 te je cos 5
        * a pretrazujemo s desna na lijevo
        */
        int expected=5;
        assertEquals(expected,result);
    }
    
    @Test
    public void getTokenTypeByNameTest(){
        ExpressionParser ep=new ExpressionParser(new JPanel());
        System.out.println("Test funkcije scanFromRight");
        String s="asin";
        TokenType result=ep.getTokenTypeByName(s);
        TokenType expected=ARCSINE;
        assertEquals(expected,result);
    }
    
    @Test
    public void parseTest(){
        ExpressionParser ep=new ExpressionParser(new JPanel());
        System.out.println("Test funkcije doOrderOfOperations");
        String s="sin(x)+cos(x)+x";
        Function result=ep.parse(s);
        
        System.out.println(result);
        
    }

}
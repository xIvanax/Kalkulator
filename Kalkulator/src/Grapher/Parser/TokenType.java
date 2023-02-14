/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Parser;

/**
 * Klasa svim mogućnostima kalkulatora pridružuje enum tip.
 * @author Ivana
 */
public enum TokenType {
	OPEN_PARENTHESES("("),
	CLOSE_PARENTHESES(")"),
	PLUS("+"),
	MINUS("-"),
	TIMES("*"),
	DIVIDED_BY("/"),
	RAISED_TO("^"),
	SINE("sin"),
	COSINE("cos"),
	TANGENT("tan"),
	COTANGENT("cot"),
	CEILING("ceil"),
	FLOOR("floor"),
	LOG("log"),
	NTHROOT("^(1/"),
	ABSOLUTE_VALUE("abs"),
	X("x"),
	NUMBER(""),
        PI("π"),
        E("e"),
        SQUARE("square"),
        FRACTION("fraction"),
        ARCSINE("asin"),
        ARCCOSINE("acos"),
        ARCTAN("atan"),
        ARCCTAN("arcctan"),
        NATURAL_LOG("ln"),
        POWER10("power10"),
        POWER("^");
	
	public static final TokenType[] FUNCTIONS={
			SINE, COSINE, TANGENT, COTANGENT,
			CEILING, FLOOR, LOG, ABSOLUTE_VALUE, NTHROOT, SQUARE,
                        FRACTION, ARCSINE, ARCCOSINE, ARCTAN, ARCCTAN, NATURAL_LOG, POWER10, POWER};
	
	public final String name;
	
	private TokenType(String name){
		this.name=name;
	}
}

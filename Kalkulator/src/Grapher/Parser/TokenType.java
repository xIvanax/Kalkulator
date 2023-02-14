/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Parser;

/**
 * poanta ove klase je da svim mogućnostima kalkulatora pridružujemo enum tip,
 * dodala sam sve funkcije koje je @Dorotea navela među gumbima
 * @author ivana
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
	SQUARE_ROOT("sqrt"),
	ABSOLUTE_VALUE("abs"),
	COMMA(","), //mislim da nam ni ovo ne treba
	X("x"),
	NUMBER(""),
        PI("π"),
        E("e"),
        SQUARE("square"),
        FRACTION("fraction"),//odnosi se na 1/x
        ARCSINE("asin"),
        ARCCOSINE("acos"),
        ARCTAN("atan"),
        ARCCTAN("arcctan"),
        NATURAL_LOG("ln"),
        POWER10("power10"),
        POWER("^");
	
	public static final TokenType[] FUNCTIONS={
			SINE, COSINE, TANGENT, COTANGENT, SQUARE_ROOT,
			CEILING, FLOOR, LOG, ABSOLUTE_VALUE, NTHROOT, SQUARE,
                        FRACTION, ARCSINE, ARCCOSINE, ARCTAN, ARCCTAN, NATURAL_LOG, POWER10, POWER};
	
	public final String name;
	
	private TokenType(String name){
		this.name=name;
	}
}

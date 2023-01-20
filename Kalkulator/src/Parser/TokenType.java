/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parser;

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
	SECANT("sec"),
	COSECANT("csc"),
	CEILING("ceil"),
	FLOOR("floor"),
	LOG("log"),
	//MODULO("%"), maknula jer nisam sigurna jel se na nekim kalkulatorima gleda kao postotak ili bas kao modulo
	NTHROOT("nthroot"),
	SQUARE_ROOT("sqrt"),
	ABSOLUTE_VALUE("abs"),
	COMMA(","),
	X("x"),
	Y("x"),
	//Z("z"), ovo sam makla jer mislim da ne treba
	NUMBER(""),
        PI("π"),
        E("e"),
        SQUARE("square"),
        FRACTION("fraction"),//odnosi se na 1/x
        ARCSINE("arcsin"),
        ARCCOSINE("arccos"),
        NATURAL_LOG("ln");
	
	public static final TokenType[] FUNCTIONS = {
			SINE, COSINE, TANGENT, COTANGENT, SECANT, COSECANT, SQUARE_ROOT,
			CEILING, FLOOR, LOG, /*MODULO,*/ ABSOLUTE_VALUE, NTHROOT, SQUARE,
                        FRACTION, ARCSINE, ARCCOSINE, NATURAL_LOG
			};
	
	public final String name;
	
	private TokenType(String name) {
		this.name = name;
	}
}

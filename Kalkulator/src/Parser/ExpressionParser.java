/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parser;

import Expressions.Function;
import Expressions.Quantity;
import Expressions.Variable;
import static java.lang.Double.NaN;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivana
 */
public class ExpressionParser {
	
	private Error error;
	private Variable x;
	private Variable y;
	//private Variable z;
	
	public ExpressionParser() {
		error = new Error();
		x = new Variable();
		y = new Variable();
		//z = new Variable();
	}
	
	public Function parse(String expr) {
		TokenString tokens = tokenize(expr);
		if (tokens != null) {
			checkParentheses(tokens);
			substituteUnaryMinus(tokens);
			double root = doOrderOfOperations(tokens);
			if (root == NaN) {
				error.makeError("Parsing of the function \"" + expr + "\" failed.");
				return null;
			}
			return new Function(root, x.num, y.num);
		}
		
		error.makeError("Parsing of the function \"" + expr + "\" failed.");
		return null;
	}
	
	private TokenString tokenize(String expr) {
		TokenString tkString = new TokenString();
		
		String name = "";
		String number = "";
		int numDecimals = 0;
		for (int i = 0; i < expr.length(); i++) {
			char currentChar = expr.charAt(i);
			boolean special = false;
			
			if (Character.isAlphabetic(currentChar)) {
				if (currentChar == 'x') {
					tkString.addToken(new Token(TokenType.X));
				} else if (currentChar == 'y') {
					tkString.addToken(new Token(TokenType.Y));
				} /*else if (currentChar == 'z') {
					tkString.addToken(new Token(TokenType.Z));
				}*/ else {
					name += currentChar;
				}
				special = true;
			} else if (name.length() > 0) {
				TokenType type = getTokenTypeByName(name);
				if (type == null) {
					error.makeError("The function name " + name + " is not valid!");
					return null;
				}
				tkString.addToken(new Token(type));
				name = "";
			}
			
			if (Character.isDigit(currentChar) || currentChar == '.') {
				if (currentChar == '.') {
					if (numDecimals == 0) number += currentChar;
					numDecimals++;
				} else {
					number += currentChar;
				}
				special = true;
			} else if (number.length() > 0) {
				tkString.addToken(new Token(TokenType.NUMBER, number));
				number = "";
				numDecimals = 0;
			}
			
			if (!(special || currentChar == ' ')) {
				if (currentChar == '(') tkString.addToken(new Token(TokenType.OPEN_PARENTHESES));
				else if (currentChar == ')') tkString.addToken(new Token(TokenType.CLOSE_PARENTHESES));
				else if (currentChar == ',') tkString.addToken(new Token(TokenType.COMMA));
				else if (currentChar == '+') tkString.addToken(new Token(TokenType.PLUS));
				else if (currentChar == '-') tkString.addToken(new Token(TokenType.MINUS));
				else if (currentChar == '*') tkString.addToken(new Token(TokenType.TIMES));
				else if (currentChar == '/') tkString.addToken(new Token(TokenType.DIVIDED_BY));
				else if (currentChar == '^') tkString.addToken(new Token(TokenType.RAISED_TO));
				//else if (currentChar == '%') tkString.addToken(new Token(TokenType.MODULO));
				else {
					error.makeError("The character '" + currentChar + "' is not allowed!");
					return null;
				}
			}
		}
		if (name.length() > 0) {
			TokenType type = getTokenTypeByName(name);
			if (type == null) {
				error.makeError("The function name '" + name + "' is not valid!");
				return null;
			}
			tkString.addToken(new Token(type));
			name = "";
		}
		if (number.length() > 0) {
			tkString.addToken(new Token(TokenType.NUMBER, number));
			number = "";
			numDecimals = 0;
		}
		
		return tkString;
	}
	
	private double doOrderOfOperations(TokenString tokens) {
		/*
		 * Order of operations in reverse:
		 * Addition, Subtraction, Division, Multiplication, Modulo, Exponentiation, Function, Parentheses, Variables, Numbers
		 * All from right to left 
		 */
		int location = 0;	// Location of some operator
                
                //javlja grešku za sljedeću liniju, al ne kužim zaš sam ret ne može bit double,
                //zas bi morao bit quantity?
		//Quantity ret = new Number(0.0);
		double ret = 0.0;
                
		location = scanFromRight(tokens, TokenType.PLUS);
		if (location != -1) {
			TokenString left = tokens.split(0, location);
			TokenString right = tokens.split(location + 1, tokens.getLength());
			//ret = new Sum(doOrderOfOperations(left), doOrderOfOperations(right));
                        ret = doOrderOfOperations(left) + doOrderOfOperations(right);
		} else {
			location = scanFromRight(tokens, TokenType.MINUS);
			if (location != -1) {
				TokenString left = tokens.split(0, location);
				TokenString right = tokens.split(location + 1, tokens.getLength());
				ret = doOrderOfOperations(left) - doOrderOfOperations(right);
			} else {
				location = scanFromRight(tokens, TokenType.DIVIDED_BY);
				if (location != -1) {
					TokenString left = tokens.split(0, location);
					TokenString right = tokens.split(location + 1, tokens.getLength());
					ret = doOrderOfOperations(left) / doOrderOfOperations(right);
				} else {
					location = scanFromRight(tokens, TokenType.TIMES);
					if (location != -1) {
						TokenString left = tokens.split(0, location);
						TokenString right = tokens.split(location + 1, tokens.getLength());
						ret = doOrderOfOperations(left) * doOrderOfOperations(right);
					} /*else {
						location = scanFromRight(tokens, TokenType.MODULO);
						if (location != -1) {
							TokenString left = tokens.split(0, location);
							TokenString right = tokens.split(location + 1, tokens.getLength());
							ret = new Modulo(doOrderOfOperations(left), doOrderOfOperations(right));
						}*/ else {
							location = scanFromRight(tokens, TokenType.RAISED_TO);
							if (location != -1) {
								TokenString left = tokens.split(0, location);
								TokenString right = tokens.split(location + 1, tokens.getLength());
								ret = Math.pow(doOrderOfOperations(left), doOrderOfOperations(right));
							} else {
								location = scanFromRight(tokens, TokenType.FUNCTIONS);
								if (location != -1) {
									int endParams = getFunctionParamsEnd(tokens, location + 2);
									if (endParams != -1) {
										TokenString paramString = tokens.split(location + 2, endParams);
										ret = parseFunctionParams(paramString, tokens.tokenAt(location).type);
									}
								} else if (tokens.getLength() >= 2 && tokens.tokenAt(tokens.getLength() - 1).type == TokenType.CLOSE_PARENTHESES
										&& tokens.tokenAt(0).type == TokenType.OPEN_PARENTHESES) {
									TokenString inParentheses = tokens.split(1, tokens.getLength() - 1);
									ret = doOrderOfOperations(inParentheses);
								} else {
									location = scanFromRight(tokens, TokenType.X);
									if (location != -1) {
										ret = x.num;
									} else {
										location = scanFromRight(tokens, TokenType.Y);
										if (location != -1) {
											ret = y.num;
										} /*else {
											location = scanFromRight(tokens, TokenType.Z);
											if (location != -1) {
												ret = z;
											}*/ else {
												location = scanFromRight(tokens, TokenType.NUMBER);
												if (location != -1) {
													ret = Double.parseDouble(tokens.tokenAt(location).data);
												}
											}
										}
									}
								}
							}
						}
					}
				}
		return ret;
	}

	private double parseFunctionParams(TokenString paramString, TokenType type) {
		List<TokenString> params = new ArrayList<>();
		int start = 0;
		for (int i = 0; i < paramString.getLength(); i++) {
			Token t = paramString.tokenAt(i);
			if (t.type == TokenType.COMMA) {
				params.add(paramString.split(start, i));
				start = i + 1;
			}
		}
		params.add(paramString.split(start, paramString.getLength()));
		
		//if (params.size() == 0) return null;
		
		if (params.size() == 1) {
			double param1 = doOrderOfOperations(params.get(0));
			switch (type) {
			case ABSOLUTE_VALUE:
				return Math.abs(param1);
			case CEILING:
				return Math.ceil(param1);
			case FLOOR:
				return Math.floor(param1);
			case SINE:
				return Math.sin(param1);
			case COSINE:
				return Math.cos(param1);
			case TANGENT:
				return Math.tan(param1);
			case COTANGENT:
				return Math.atan(param1);
			case SECANT:
				return 1/Math.atan(param1);
			case COSECANT:
				return 1/Math.tan(param1);
			case SQUARE_ROOT:
				return Math.sqrt(param1);
                        case ARCSINE:
                                return Math.asin(param1);
                        case ARCCOSINE:
                                return Math.acos(param1);
                        case FRACTION:
                            if(param1==0){
                                error.makeError("Illegal expression (dividing by zero).");
                                return NaN;
                            }
                            else
                                return 1/param1;
                        case SQUARE:
                            return Math.pow(param1, 2);
                        case NATURAL_LOG:
                            return Math.log(param1);
			default:
				return NaN;
			}
		} else if (params.size() == 2) {
			double param1 = doOrderOfOperations(params.get(0));
			double param2 = doOrderOfOperations(params.get(1));
			switch (type) {
			case NTHROOT:
                            if(param2==0){
                                error.makeError("Illegal expression (dividing by zero).");
                                return NaN;
                            }
                            else
				return Math.pow(param1, 1/param2);
			case LOG:
                            if(param2==1){
                                error.makeError("Illegal expression (dividing by zero).");
                                return NaN;
                            }
                            else
				return Math.log(param1) / Math.log(param2);
			default:
				return NaN;
			}
		}
		return NaN;
	}

	private int getFunctionParamsEnd(TokenString tokens, int location) {
		int openParentheses = 0;
		for (int i = location; i < tokens.getLength(); i++) {
			Token t = tokens.tokenAt(i);
			if (t.type == TokenType.OPEN_PARENTHESES) {
				openParentheses++;
			} else if (t.type == TokenType.CLOSE_PARENTHESES) {
				if (openParentheses == 0) {
					return i;
				}
				openParentheses--;
			}
		}
		return -1;
	}

	private int scanFromRight(TokenString tokens, TokenType type) {
		int openParentheses = 0;
		for (int i = tokens.getLength() - 1; i >= 0; i--) {
			Token t = tokens.tokenAt(i);
			if (t.type == TokenType.CLOSE_PARENTHESES) {
				openParentheses++;
			} else if (t.type == TokenType.OPEN_PARENTHESES) {
				openParentheses--;
			} else if (t.type == type && openParentheses == 0) {
				return i;
			}
		}
		return -1;
	}
	
	private int scanFromRight(TokenString tokens, TokenType[] types) {
		int openParentheses = 0;
		for (int i = tokens.getLength() - 1; i >= 0; i--) {
			Token t = tokens.tokenAt(i);
			if (t.type == TokenType.CLOSE_PARENTHESES) {
				openParentheses++;
			} else if (t.type == TokenType.OPEN_PARENTHESES) {
				openParentheses--;
			} else {
				if (openParentheses == 0) {
					for (int j = 0; j < types.length; j++) {
						if (t.type == types[j]) {
							return i;
						}
					}
				}
			}
		}
		return -1;
	}
	
	private void substituteUnaryMinus(TokenString tokens) {
		Token prev = null;
		for (int i = 0; i < tokens.getLength(); i++) {
			Token t = tokens.tokenAt(i);
			if (t.type == TokenType.MINUS) {
				if (prev == null || !(prev.type == TokenType.NUMBER || prev.type == TokenType.X || prev.type == TokenType.CLOSE_PARENTHESES)) {
					// Ex: -x becomes (0-1)*x
					tokens.remove(i);
					tokens.insert(i, new Token(TokenType.TIMES));
					tokens.insert(i, new Token(TokenType.CLOSE_PARENTHESES));
					tokens.insert(i, new Token(TokenType.NUMBER, "1"));
					tokens.insert(i, new Token(TokenType.MINUS));
					tokens.insert(i, new Token(TokenType.NUMBER, "0"));
					tokens.insert(i, new Token(TokenType.OPEN_PARENTHESES));
					i += 6;
				}
			}
			prev = t;
		}
	}

	private void checkParentheses(TokenString tokens) {
		// Test for correct number of parentheses
		int openParentheses = 0;
		for (int i = 0; i < tokens.getLength(); i++) {
			Token t = tokens.tokenAt(i);
			if (t.type == TokenType.OPEN_PARENTHESES) {
				openParentheses++;
			} else if (t.type == TokenType.CLOSE_PARENTHESES) {
				openParentheses--;
			}
			if (openParentheses < 0) {
				error.makeError("You closed too many parentheses!");
			}
		}
		if (openParentheses > 0) {
			error.makeError("You did not close enough parentheses!");
		}
	}


	private TokenType getTokenTypeByName(String name) {
		TokenType[] values = TokenType.FUNCTIONS;
		for (TokenType v : values) {
			if (v.name.equals(name)) return v;
		}
		return null;
	}
}

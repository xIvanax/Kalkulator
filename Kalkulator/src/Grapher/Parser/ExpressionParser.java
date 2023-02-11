/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Parser;

import Grapher.Expressions.AbsoluteValue;
import Grapher.Expressions.Acos;
import Grapher.Expressions.Arcctan;
import Grapher.Expressions.Ceiling;
import Grapher.Expressions.Cosecant;
import Grapher.Expressions.Cosine;
import Grapher.Expressions.Cotangent;
import Grapher.Expressions.Difference;
import Grapher.Expressions.Floor;
import Grapher.Parser.Error;
import Grapher.Expressions.Function;
import Grapher.Expressions.Log;
import Grapher.Expressions.Modulo;
import Grapher.Expressions.NthRoot;
import Grapher.Expressions.Quantity;
import Grapher.Expressions.Variable;
import Grapher.Expressions.Asin;
import Grapher.Expressions.Atan;
import Grapher.Expressions.Natural_log;
import java.util.ArrayList;
import java.util.List;
import Grapher.Expressions.Number;
import Grapher.Expressions.Power;
import Grapher.Expressions.Power10;
import Grapher.Expressions.Product;
import Grapher.Expressions.Quotient;
import Grapher.Expressions.Secant;
import Grapher.Expressions.Sine;
import Grapher.Expressions.SquareRoot;
import Grapher.Expressions.Sum;
import Grapher.Expressions.Tangent;
import static Grapher.Parser.TokenType.LOG;
/**
 *
 * @author ivana
 */
public class ExpressionParser {
    
	private Error error;
	private Variable x;
        //mislim da nam sljedece dvije varijable nisu potrebne
	private Variable y;
	private Variable z;
	
	public ExpressionParser(){
		error=new Error();
		x=new Variable();
		y=new Variable();
		z=new Variable();
        }
	
        /**
        * @author Dorotea
        * Sljedeća funkcija uneseni izraz(expr) raščlanjuje na manje blokove(tokene) radi lakše manipulacije
        */
	public TokenString tokenize(String expr){
		TokenString tkString=new TokenString();
		
		String name="";
		String number="";
		int numDecimals=0;//ako je broj decimalan onda pamtimo koliko decimala ima
		for(int i=0; i<expr.length(); i++) {
			char currentChar=expr.charAt(i);
			boolean special=false;//provjera je li znak 'specijalan', 0 oznacava da je specijalan
                        //1 da nije (tj alfa-numericki je) 
			
                        /**
                        * @author Dorotea
                        * Za svaki char određujemo koji je tip tokena(je li slovo, broj, neki specijalni znak)
                        */
			if(Character.isAlphabetic(currentChar)) {
				if(currentChar=='x'){
                                    tkString.addToken(new Token(TokenType.X));
				}else if(currentChar=='y'){
                                    tkString.addToken(new Token(TokenType.Y));
				}else if(currentChar=='z'){
                                    tkString.addToken(new Token(TokenType.Z));
				}else{
                                    name+=currentChar;
				}
				special=true;//dakle znak nije 'specijalan'
                                
			}else if(name.length()>0){
				TokenType type=getTokenTypeByName(name);
				if(type==null) {
                                    error.makeError("The function name "+name+" is not valid!");
                                    return null;
				}
				tkString.addToken(new Token(type));
				name="";
			}
			
			if(Character.isDigit(currentChar) || currentChar=='.'){
				if(currentChar=='.'){
                                    if(numDecimals==0)
                                        number+=currentChar;
                                    numDecimals++;
				}else{
                                    number+=currentChar;
				}
				special = true;
			}else if(number.length()>0){
				tkString.addToken(new Token(TokenType.NUMBER,number));
				number = "";
				numDecimals = 0;
			}
			
			if(!special && !(currentChar==' ')){
				if(currentChar=='(')
                                    tkString.addToken(new Token(TokenType.OPEN_PARENTHESES));
				else if(currentChar==')')
                                    tkString.addToken(new Token(TokenType.CLOSE_PARENTHESES));
				else if(currentChar==',')
                                    tkString.addToken(new Token(TokenType.COMMA));
				else if(currentChar=='+')
                                    tkString.addToken(new Token(TokenType.PLUS));
				else if(currentChar=='-')
                                    tkString.addToken(new Token(TokenType.MINUS));
				else if(currentChar=='*')
                                    tkString.addToken(new Token(TokenType.TIMES));
				else if(currentChar=='/')
                                    tkString.addToken(new Token(TokenType.DIVIDED_BY));
				else if(currentChar=='^')
                                    tkString.addToken(new Token(TokenType.RAISED_TO));
				else if(currentChar=='%')
                                    tkString.addToken(new Token(TokenType.MODULO));
				else{
                                    error.makeError("The character '"+currentChar+"' is not allowed!");
                                    return null;
				}
			}
		}
                
                /**
                * @author Dorotea
                * Kada dođemo do kraja expr, moguće je da nismo pohranili neki broj ili znak
                * pa to dodatno provjeravamo i radimo
                */
                
		if(name.length()>0){
                    TokenType type=getTokenTypeByName(name);
                    if(type==null){
                    	error.makeError("The function name '"+name+"' is not valid!");
			return null;
                    }
                    tkString.addToken(new Token(type));
                    name="";
		}
                
		if(number.length()>0){
                    tkString.addToken(new Token(TokenType.NUMBER, number));
                    number = "";
                    numDecimals = 0;
		}
		
		return tkString;
	}
        
	public Function parse(String expr){
		TokenString tokens=tokenize(expr);
		if(tokens!=null){
			checkParentheses(tokens);
			substituteUnaryMinus(tokens);
			Quantity root=doOrderOfOperations(tokens);
			if(root==null){
                            error.makeError("Parsing of the function \"" + expr + "\" failed.");
                            return null;
			}
			return new Function(root, x, y, z);
		}
		error.makeError("Parsing of the function \""+expr+"\" failed.");
		return null;
	}
        
        /**
         * @author Dorotea
         * String u kojemu je zapisana funkcija pretrazujemo s desna na lijevo.
         * @param tokens TokenString tj. lista tokena
         * @param type tip tokena
         * @return lokacija prvog tokena tipa type
         */
        public int scanFromRight(TokenString tokens, TokenType type){
            int openParentheses=0;
            for(int i=tokens.getLength()-1; i>=0; i--){
                Token t=tokens.tokenAt(i);
                if(t.type==TokenType.CLOSE_PARENTHESES){
                       openParentheses++;
                }else if(t.type==TokenType.OPEN_PARENTHESES){
                    openParentheses--;
                }else if(t.type==type && openParentheses==0){
                    return i;
                }
            }
            return -1;
	}
        
        public int scanFromRight(TokenString tokens, TokenType[] types) {
            int openParentheses=0;
            for(int i=tokens.getLength()-1; i>=0; i--){
                Token t=tokens.tokenAt(i);
                if(t.type==TokenType.CLOSE_PARENTHESES){
                    openParentheses++;
                }else if(t.type==TokenType.OPEN_PARENTHESES){
                    openParentheses--;
                }else{
                    if(openParentheses==0){
                        for(int j=0; j<types.length; j++){
                            if(t.type==types[j]){
                                return i;
                            }
                        }
                    }
                }
            }
            return -1;
	}
        
        private TokenType getTokenTypeByName(String name) {
            TokenType[] values=TokenType.FUNCTIONS;
            for(TokenType v:values){
                if(v.name.equals(name))
                    return v;
            }
            return null;
	}
	
	private Quantity doOrderOfOperations(TokenString tokens){
		/**
                * @author Dorotea
		* Redosljed računskih operacija promatramo u smjeru obrnutom od uobičajnog
		* Zbrajanje, oduzimanje, djeljenje, množenje, potenciranje, je li neka funkcija(npr lnx), zagrade, varijable(npr. x), brojevi 
		**/
		int location=0;	// lokacija željenog operatora
		Quantity ret=new Number(0.0);
		
		location=scanFromRight(tokens,TokenType.PLUS);
		if(location!=-1){//dakle, ako postoji token toga tipa u tokens
                    /*Uzimamo sve što se nalazi lijevo i sve što se nalazi desno od traženog tokena*/
                    TokenString left=tokens.split(0,location);
                    TokenString right=tokens.split(location+1,tokens.getLength());
                    ret=new Sum(doOrderOfOperations(left),doOrderOfOperations(right));
		}else{
                    location=scanFromRight(tokens,TokenType.MINUS);
                    if(location!=-1){
			TokenString left=tokens.split(0,location);
			TokenString right=tokens.split(location+1,tokens.getLength());
			ret=new Difference(doOrderOfOperations(left),doOrderOfOperations(right));
                    }else{
			location=scanFromRight(tokens,TokenType.DIVIDED_BY);
			if(location!=-1){
                            TokenString left=tokens.split(0,location);
                            TokenString right=tokens.split(location+1,tokens.getLength());
                            ret=new Quotient(doOrderOfOperations(left),doOrderOfOperations(right));
			}else{
                            location=scanFromRight(tokens,TokenType.TIMES);
                            if(location!=-1){
				TokenString left=tokens.split(0,location);
				TokenString right=tokens.split(location+1,tokens.getLength());
				ret=new Product(doOrderOfOperations(left),doOrderOfOperations(right));
                            }else{
                                location=scanFromRight(tokens,TokenType.MODULO);
                                if(location!=-1) {
				TokenString left=tokens.split(0,location);
				TokenString right=tokens.split(location+1,tokens.getLength());
				ret=new Modulo(doOrderOfOperations(left),doOrderOfOperations(right));
                                }else{
                                    location=scanFromRight(tokens,TokenType.RAISED_TO);
                                    if(location!=-1) {
					TokenString left=tokens.split(0,location);
                                        TokenString right=tokens.split(location+1,tokens.getLength());
					ret=new Power(doOrderOfOperations(left),doOrderOfOperations(right));
                                    }else{
					location=scanFromRight(tokens,TokenType.FUNCTIONS);
					if(location!=-1){
                                            int endParams=getFunctionParamsEnd(tokens,location+2);
                                            if(endParams!=-1){
						TokenString paramString=tokens.split(location+2,endParams);
						ret=parseFunctionParams(paramString,tokens.tokenAt(location).type);
                                            }
					}else if(tokens.getLength()>=2 && tokens.tokenAt(tokens.getLength()-1).type==TokenType.CLOSE_PARENTHESES
						&& tokens.tokenAt(0).type==TokenType.OPEN_PARENTHESES){
                                            TokenString inParentheses=tokens.split(1, tokens.getLength()-1);
                                            ret=doOrderOfOperations(inParentheses);
					}else{
                                            location=scanFromRight(tokens,TokenType.X);
                                            if(location!=-1){
						ret=x;
                                            }else{
						location=scanFromRight(tokens, TokenType.Y);
						if(location!=-1){
                                                    ret = y;
						}else{
                                                    location=scanFromRight(tokens,TokenType.Z);
                                                    if(location!=-1){
							ret=z;
                                                    }else{
                                                        location=scanFromRight(tokens,TokenType.NUMBER);
							if(location!=-1) {
                                                            ret=new Number(Double.parseDouble(tokens.tokenAt(location).data));
							}
                                                    }
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

    private Quantity parseFunctionParams(TokenString paramString, TokenType type){
	List<TokenString> params=new ArrayList<>();
	int start=0;
	for(int i=0; i<paramString.getLength(); i++){
            Token t=paramString.tokenAt(i);
            if(t.type==TokenType.COMMA){
		params.add(paramString.split(start,i));
		start=i+1;
            }
	}
	params.add(paramString.split(start,paramString.getLength()));
		
	if(params.size()==0) return null;
		
	if(params.size()==1){
            Quantity param1=doOrderOfOperations(params.get(0));
            switch(type){
		case ABSOLUTE_VALUE:
                    return new AbsoluteValue(param1);
		case CEILING:
                    return new Ceiling(param1);
		case FLOOR:
                    return new Floor(param1);
		case SINE:
                    return new Sine(param1);
		case COSINE:
                    return new Cosine(param1);
                case TANGENT:
                    return new Tangent(param1);
		case COTANGENT:
                    return new Cotangent(param1);
		case SECANT:
                    return new Secant(param1);
                case COSECANT:
                    return new Cosecant(param1);
		case SQUARE_ROOT:
                    return new SquareRoot(param1);
                case ARCSINE:
                    return new Asin(param1);
                case ARCCOSINE:
                    return new Acos(param1);
                case ARCTAN:
                    return new Atan(param1);
                case ARCCTAN:
                    return new Arcctan(param1);
                case NATURAL_LOG:
                    return new Natural_log(param1);
                case POWER10:
                    return new Power10(param1);
                case LOG:
                    return new Log(param1);
		default:
                    return null;
            }
	}else if(params.size()==2){
            Quantity param1 = doOrderOfOperations(params.get(0));
            Quantity param2 = doOrderOfOperations(params.get(1));
            switch (type) {
                case NTHROOT:
                    return new NthRoot(param1,param2);
                case POWER:
                    return new Power(param1,param2);
		default:
                    return null;
            }
	}
	return null;
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
        
        /**
        * @author Dorotea
        * Sljedeća funkcija provjerava nalazi li se u TokenString-u jednak broj otvorenih i zatvorenih operacija
        */
	private void checkParentheses(TokenString tokens){
            int openParentheses=0;
            for(int i=0; i<tokens.getLength(); i++){
                Token t=tokens.tokenAt(i);
		if(t.type==TokenType.OPEN_PARENTHESES){
                    openParentheses++;
		}else if(t.type==TokenType.CLOSE_PARENTHESES){
                    openParentheses--;
		}
                
		if(openParentheses<0){
                    error.makeError("Ima previše zatvorenih zagrada!");
		}
            }
            
            if (openParentheses > 0) {
		error.makeError("Niste zatvorili sve zagrade!");
            }
	}
}

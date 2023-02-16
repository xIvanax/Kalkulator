/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Parser;

import Grapher.Expressions.AbsoluteValue;
import Grapher.Expressions.Acos;
import Grapher.Expressions.Arcctan;
import Grapher.Expressions.Ceiling;
import Grapher.Expressions.Cosine;
import Grapher.Expressions.Cotangent;
import Grapher.Expressions.Difference;
import Grapher.Expressions.Floor;
import Grapher.Expressions.Function;
import Grapher.Expressions.Log;
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
import Grapher.Expressions.Sine;
import Grapher.Expressions.Sum;
import Grapher.Expressions.Tangent;
import static Grapher.Parser.TokenType.LOG;
import javax.swing.JPanel;
/**
 * Klasa odgovorna za parsiranje izraza.
 * @author ivana
 */
public class ExpressionParser {
    
	private Error error;
	private Variable x;
        public JPanel parent;
	/**
         * Konstruktor bez parametara koji inicijalizira varijable error i x.
         * @author Ivana
         * @param parent inicijalizacija prozora na koji će se zakačiti poruka o grešci
         */
	public ExpressionParser(JPanel parent){
                this.parent=parent;
		error=new Error(parent);
		x=new Variable();
        }
        /**
         * Metoda uneseni izraz(expr) raščlanjuje na manje blokove(tokene) radi lakše manipulacije.
         * Primjer: x+3-10^(x) pretvara u X PLUS NUMBER 3 MINUS NUMBER 10 RAISED_TO OPEN_PARENTHESES X CLOSE_PARENTHESES
         * @param expr String koji raščlanjujemo na token-e.
         * @return TokenString 
         * @author Dorotea
         */
	public TokenString tokenize(String expr){
		TokenString tkString=new TokenString();
		
		String name="";
		String number="";
		int numDecimals=0;
		for(int i=0; i<expr.length(); i++) {
			char currentChar=expr.charAt(i);
                        /**
                         * Provjera je li znak 'specijalan', 0 oznacava da je specijalan,
                         * 1 da nije (tj alfa-numericki je).
                         * @author Dorotea
                         */
			boolean special=false;
                        /**
                        * Za svaki char određujemo koji je tip tokena(je li slovo, broj, neki specijalni znak)
                        * @author Dorotea
                        */
			if(Character.isAlphabetic(currentChar)) {
				if(currentChar=='x'){
                                    tkString.addToken(new Token(TokenType.X));
				}else{
                                    name+=currentChar;
				}
				special=true;
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
				else{
                                    error.makeError("The character '"+currentChar+"' is not allowed!");
                                    return null;
				}
			}
		}
                /**
                * Kada dođemo do kraja expr, moguće je da nismo pohranili neki broj ili znak
                * pa to dodatno provjeravamo i radimo
                * @author Dorotea
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
        /**
         * Metoda za parseanje String-a, odnosno prepoznavanje funkcije iz String-a.
         * @param expr String koji parse-amo
         * @return Function dobiven iz String-a expr
         * @author Ivana
         */
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
			return new Function(root, x);
		}
		error.makeError("Parsing of the function \""+expr+"\" failed.");
		return null;
	}
        
        /**
         * String u kojemu je zapisana funkcija pretrazujemo s desna na lijevo, kontroliramo broj zagrada i vražamo indeks token-a tipa Type.
         * @param tokens TokenString tj. lista tokena
         * @param type tip tokena
         * @return lokacija prvog tokena tipa type
         * @author Dorotea
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
        /**
         * Analogno kao drugi scanFromRight, samo što sada imamo polje TokenType-ova umjesto jednog TokenType-a.
         * @param tokens TokenString tj. lista tokena
         * @param types polje tipova token-a
         * @return indeks nekog od tipova iz polja types kojeg smo našli u TokenString-u tokens
         * @author Ivana
         */
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
        /**
         * Vraća tip token-a na temelju njegovog imena.
         * @param name ime tokena
         * @return tip tokena
         * @author Ivana
         */
        public TokenType getTokenTypeByName(String name) {
            TokenType[] values=TokenType.FUNCTIONS;
            for(TokenType v:values){
                if(v.name.equals(name))
                    return v;
            }
            return null;
	}
	/**
         * Redosljed računskih operacija promatramo u smjeru suprotnom od uobičajenog, dakle:
         * zbrajanje, oduzimanje, dijeljenje, množenje, potenciranje, funkcija, zagrade, varijable, brojevi.
         * @param tokens TokenString, odnosno lista tokena
         * @return Quantity, odnosno rezultat provednih operacija
         * @author Dorotea
         */
	public Quantity doOrderOfOperations(TokenString tokens){
		int location=0;
		Quantity ret=new Number(0.0);
		
		location=scanFromRight(tokens,TokenType.PLUS);
		if(location!=-1){
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
            return ret;
	}
    /**
     * Vraca vrijednost promatrane funkcije u ovisnosti o parametrima
     * @param paramString polje koje sadrzi parametre promatrane funkcije
     * @param type tip funkcije
     * @return vraca vrijednost funkcije u danim parametrima
     * @author Dorotea
     */
    private Quantity parseFunctionParams(TokenString paramString, TokenType type){
	List<TokenString> params=new ArrayList<>();
	int start=0;
	for(int i=0; i<paramString.getLength(); i++){
            Token t=paramString.tokenAt(i);
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
        /**
         * Vraća "kraj" funkcije
         * @param tokens TokenString tj. lista tokena
         * @param location lokacija od koje započinjemo pretragu
         * @return -1 ako nađe još neku otvorenu zagrade prije zatvorene, inače vraća indeks zadnje zagrade
         * @author Ivana
         */
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
	/**
         * Izbacuje unarni minus i zamjenjuje ga na način da npr. -x postane (0-1)*x.
         * @param tokens TokenString
         * @author Ivana
         */
	private void substituteUnaryMinus(TokenString tokens) {
		Token prev = null;
		for (int i = 0; i < tokens.getLength(); i++) {
			Token t = tokens.tokenAt(i);
			if (t.type == TokenType.MINUS) {
				if (prev == null || !(prev.type == TokenType.NUMBER || prev.type == TokenType.X || prev.type == TokenType.CLOSE_PARENTHESES)) {
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
         * Provjerava nalazi li se u TokenString-u jednak broj otvorenih i zatvorenih zagrada.
         * @param tokens TokenString tj. lisat tokena
         * @author Dorotea
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

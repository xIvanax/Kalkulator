/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Parser;

/**
 * Prilikom unosa funkcije, pohranjujemo unesene tokene kao String (TokenString) kojim,
 * nakon pritiska na gumb eval ili draw, moramo moći manipulirati kako bismo mogli nacrtati funkciju ili ju evaluirati u određenoj točki.
 * Jednostavija manipulacija unesenim Stringom omogućena je sljdećom klasom.
 * @author ivana
 */
import Grapher.Parser.Token;
import java.util.ArrayList;
import java.util.List;

public class TokenString{
	private List<Token> tokens;
	
	public TokenString(){
		tokens=new ArrayList<>();
	}
	
	private TokenString(List<Token> tokens) {
		this.tokens=tokens;
	}
	
	public void addToken(Token token){
		tokens.add(token);
	}
	
	public Token tokenAt(int i){
		return tokens.get(i);
	}
	
	public int getLength(){
		return tokens.size();
	}
	
	public TokenString split(int start, int stop){
		start=Math.max(0,start);
		stop=Math.min(tokens.size(),stop);
		
		List<Token> subList = new ArrayList<>();
		for (int i=start; i<stop; i++) {
			subList.add(tokens.get(i));
		}
		return new TokenString(subList);
	}
	
	public void insert(int i, Token token){
		tokens.add(i,token);
	}
	
	public Token remove(int i){
		return tokens.remove(i);
	}
	
	@Override
	public String toString(){
		String line="";
		for(Token t:tokens){
			line+=t.toString();
			if(t.data.length()>0)
                            line+="<"+t.data+">";
			line+=" ";
		}
		return line;
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Parser;

/**
 * Prilikom unosa funkcije, pohranjujemo unesene tokene kao String (TokenString) kojim,
 * nakon pritiska na gumb eval ili draw, moramo moći manipulirati kako bismo mogli nacrtati funkciju ili 
 * ju evaluirati u određenoj točki.
 * @author Ivana
 */
import java.util.ArrayList;
import java.util.List;

public class TokenString{
	private List<Token> tokens;
	/**
         * Konstruktor bez parametara, samo incijalizira varijablu tokens.
         * @Ivana
         */
	public TokenString(){
		tokens=new ArrayList<>();
	}
	/**
         * Inicijalizira varijablu this.tokens na parametar tokens.
         * @param tokens lista token-a
         * @Ivana
         */
	private TokenString(List<Token> tokens) {
		this.tokens=tokens;
	}
	/**
         * Dodaje parametar token u listu tokens.
         * @param token Token koji dodajemo u tokens
         * @Ivana
         */
	public void addToken(Token token){
		tokens.add(token);
	}
	/**
         * Vraća token iz liste tokens na indeksu i.
         * @param i indeks token-a
         * @return token
         * @Ivana
         */
	public Token tokenAt(int i){
		return tokens.get(i);
	}
	/**
         * Vraća duljinu liste tokens.
         * @return duljina liste tokens
         * @Ivana
         */
	public int getLength(){
		return tokens.size();
	}
	/**
         * Vraća listu elemenata liste tokens između indeksa start i stop.
         * @param start početni indeks
         * @param stop krajnji indeks
         * @return TokenString tj. lista elemenata između početnog i krajnjeg indeksa.
         * @Ivana
         */
	public TokenString split(int start, int stop){
		start=Math.max(0,start);
		stop=Math.min(tokens.size(),stop);
		
		List<Token> subList = new ArrayList<>();
		for (int i=start; i<stop; i++) {
			subList.add(tokens.get(i));
		}
		return new TokenString(subList);
	}
	/**
         * Ubacuje token na indeks i u listu tokens.
         * @param i indeks 
         * @param token token koji želimo ubaciti
         * @Ivana
         */
	public void insert(int i, Token token){
		tokens.add(i,token);
	}
	/**
         * Briše token iz liste tokens na indeksu i.
         * @param i indeks
         * @return token koji smo izbacili
         * @Ivana
         */
	public Token remove(int i){
		return tokens.remove(i);
	}
	/**
         * Overridea metodu toString
         * @return string reprezentacija token-a.
         * @Ivana
         */
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

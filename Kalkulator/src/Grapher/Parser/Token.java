/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Parser;

/**
 * Tokeni napravljeni na temelju TokenTypes zasnovanih na mogućnostima koje nude gumbi.
 * @author Ivana
 */
public class Token{
	public final TokenType type;
	public final String data;
	/**
         * Konstruktor koji inicijalizira varijable type i data.
         * @param type TokenType
         * @param data String
         * @author Ivana
         */
	public Token(TokenType type, String data){
		this.type=type;
		this.data=data;
	}
	/**
         * Konstruktor koji inicijalizira varijable type i data.
         * @param type TokenType
         * @author Ivana
         */
	public Token(TokenType type){
		this(type,"");
	}
	/**
         * Overridea metodu toString.
         * @return string reprezentacija varijable type
         * @author Ivana
         */
	@Override
	public String toString(){
		return type.toString();
	}
}

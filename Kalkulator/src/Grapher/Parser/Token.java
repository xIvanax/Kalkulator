/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Parser;

/**
 * Tokeni napravljeni na temelju TokenTypes zasnovanih na mogućnostima koje nude gumbi
 * deklarirani od strane @Dorotea u GraphingGUI
 * @author ivana
 */
public class Token {
	public final TokenType type;
	public final String data;
	
	public Token(TokenType type, String data) {
		this.type = type;
		this.data = data;
	}
	
	public Token(TokenType type) {
		this(type, "");
	}
	
	@Override
	public String toString() {
		return type.toString();
	}
}

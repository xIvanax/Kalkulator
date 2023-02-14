/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Klasa koja reprezentira varijablu x.
 * @author Ivana
 */
public class Variable extends Number {
        /**
         * Konstruktor bez parametara koji inicijalizira vrijednost na 0.0 (nasljeđeno iz klase Number).
         * @author Ivana
         */
	public Variable() {
		super(0.0);
	}
        /**
         * Postavlja vrijednost varijable na num.
         * @param num double
         * @author Ivana
         */
	public void set(double num) {
		this.num = num;
	}
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Kosinus, nasljeđuje Unary.
 * @author Ivana
 */
public class Cosine extends Unary {
	/**
         * Konstruktor koji prima parametar q
         * @param q Quantity
         * @author Ivana
         */
	public Cosine(Quantity q) {
		super(q);
	}
        /**
         * Vraća rezultat unarne operacije
         * @return rezultt unarne operacije
         * @author Ivana
         */
	@Override
	public double getValue() {
		double val = realValue(q);
		return Math.cos(val);
	}
}

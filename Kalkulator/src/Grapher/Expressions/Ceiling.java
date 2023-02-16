/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Cjelobrojno zaokruživanje na gore, nasljeđuje Unary.
 * @author Ivana
 */
public class Ceiling extends Unary {
	/**
         * Konstruktor koji prima parametar q
         * @param q Quantity
         * @author Ivana
         */
	public Ceiling(Quantity q) {
		super(q);
	}
        /**
         * Vraća vrijednost zaokruživanja.
         * @return double vrijednost zaokruživanja
         * @author Ivana
         */
	@Override
	public double getValue() {
		double val=realValue(q);
		return Math.ceil(val);
	}
}

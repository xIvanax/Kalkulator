/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Kotangens, nasljeđuje Unary.
 * @author Ivana
 */
public class Cotangent extends Unary {
	/**
         * Konstruktor koji prima parametar q
         * @param q Quantity
         * @author Ivana
         */
	public Cotangent(Quantity q) {
		super(q);
	}
        /**
         * Vraža vrijednost kotangensa.
         * @return vrijednost kotangensa
         * @author Ivana
         */
	@Override
	public double getValue() {
		double val = realValue(q);
		return 1.0 / Math.tan(val);
	}
}

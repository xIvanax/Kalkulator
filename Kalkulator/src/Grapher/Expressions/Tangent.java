/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Tangens, nasljeđuje Unary.
 * @author Ivana
 */
public class Tangent extends Unary {
	/**
         * Konstruktor koji prima parametar q
         * @param q Quantity
         * @author Ivana
         */
	public Tangent(Quantity q) {
		super(q);
	}
        /**
         * Vraća vrijednost tangensa.
         * @return vrijednost
         * @author Ivana
         */
	@Override
	public double getValue() {
		double val = realValue(q);
		return Math.tan(val);
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 *  Apsolutna vrijednost, nasljeđuje Unary.
 * @author Ivana
 */
public class AbsoluteValue extends Unary {
	/**
         * Konstruktor koji prima parametar q
         * @param q Quantity
         * @author Ivana
         */
	public AbsoluteValue(Quantity q) {
		super(q);
	}
        /**
         * Vraća apsolutnu vrijednost.
         * @return apsolutna vrijednost
         * @author Ivana
         */
	@Override
	public double getValue() {
		double val = realValue(q);
		return Math.abs(val);
	}
}

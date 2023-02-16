/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Cjelobrojno zaokruživanje na dolje, nasljeđuje Unary.
 * @author Ivana
 */
public class Floor extends Unary {
	/**
         * Konsturktor koji prima parametar q
         * @param q Quantity
         * @author Ivana
         */
	public Floor(Quantity q) {
		super(q);
	}
        /**
         * Vraća vrijednost zaokruživanja
         * @return vrijednost zaokruživanja
         * @author Ivana
         */
	@Override
	public double getValue() {
		double val = realValue(q);
		return Math.floor(val);
	}
}

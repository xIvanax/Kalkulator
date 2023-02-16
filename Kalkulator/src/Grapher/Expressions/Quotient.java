/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Dijeljenje, nasljeđuje Binary.
 * @author Ivana
 */
public class Quotient extends Binary {
	/**
         * Inicijalizacija operanada
         * @param q1 prvi operand
         * @param q2 drugi operand
         * @author Ivana
         */
	public Quotient(Quantity q1, Quantity q2) {
		super(q1, q2);
	}
        /**
         * Vraća vrijednost dijeljenja.
         * @return vrijednost dijeljenja
         * @author Ivana
         */
	@Override
	public double getValue() {
		double val1 = realValue(q1);
		double val2 = realValue(q2);
		return val1 / val2;
	}
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Potencija, nasljeđuje Binary.
 * @author Ivana
 */
public class Power extends Binary {
	/**
         * Inicijalizacija operanada
         * @param x prvi operand
         * @param exp drugi operand
         * @author Ivana
         */
	public Power(Quantity x, Quantity exp) {
		super(x, exp);
	}
        /**
         * Vraća vrijednost potenciranja
         * @return vrijednost potenciranja
         * @author Ivana
         */
	@Override
	public double getValue() {
		double val1 = realValue(q1);
		double val2 = realValue(q2);
		return Math.pow(val1, val2);
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Umnožak, nasljeđuje Binary.
 * @author Ivana
 */
public class Product extends Binary {
	/**
         * Inicijalizacija operanada
         * @param q1 prvi operand
         * @param q2 drugi operand
         * @author Ivana
         */
	public Product(Quantity q1, Quantity q2) {
		super(q1, q2);
	}
        /**
         * Vraća vrijednost umnoška.
         * @return vrijednost umnoška
         * @author Ivana
         */
	@Override
	public double getValue() {
		double val1 = realValue(q1);
		double val2 = realValue(q2);
		return val1 * val2;
	}
}

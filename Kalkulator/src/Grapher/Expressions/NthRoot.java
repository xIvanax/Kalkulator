/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * N-ti korijen, nasljeđuje Binary.
 * @author Ivana
 */
public class NthRoot extends Binary {
        /**
         * Inicijalizira operande
         * @param q prvi operand
         * @param root drugi operand
         * @author Ivana
         */
	public NthRoot(Quantity q, Quantity root) {
		super(q, root);
	}
	/**
         * Vraća vrijednost n-tog korijena
         * @return vrijednost n-tog korijena
         * @author Ivana
         */
	@Override
	public double getValue() {
		double val1 = realValue(q1);
		double val2 = realValue(q2);
		return Math.pow(val1, 1.0 / val2);
	}

}

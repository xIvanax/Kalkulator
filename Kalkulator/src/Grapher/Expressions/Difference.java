/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Oduzimanje, nasljeđuje Binary.
 * @author Ivana
 */
public class Difference extends Binary {
	
	public Difference(Quantity q1, Quantity q2) {
		super(q1, q2);
	}

	@Override
	public double getValue() {
		double val1 = realValue(q1);
		double val2 = realValue(q2);
		return val1 - val2;
	}
}

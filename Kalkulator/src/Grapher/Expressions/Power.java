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
	
	public Power(Quantity x, Quantity exp) {
		super(x, exp);
	}

	@Override
	public double getValue() {
		double val1 = realValue(q1);
		double val2 = realValue(q2);
		return Math.pow(val1, val2);
	}
}

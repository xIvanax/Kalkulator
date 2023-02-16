/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Sinus, nasljeđuje Unary.
 * @author Ivana
 */
public class Sine extends Unary {
    /**
     * Inicijalizacija operanda q
     * @param q Quantity
     * @author Ivana
     */
	public Sine(Quantity q) {
		super(q);
	}
    /**
     * Vraća vrijednost sinusa
     * @return vrijednost sinusa
     * @author Ivana
     */
	@Override
	public double getValue() {
		double val = realValue(q);
		return Math.sin(val);
	}
}

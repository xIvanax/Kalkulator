/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Logaritam, nasljeđuje Unary.
 * @author Ivana
 */
public class Log extends Unary {
        /**
         * KOnstruktor koji prima parametar x
         * @param x Quantity
         * @author Ivana
         */
	public Log(Quantity x) {
		super(x);
	}
        /**
         * Vraća vrijednost logaritma
         * @return vrijednost logaritma
         * @author Ivana
         */
	@Override
	public double getValue() {
		double val = realValue(q);
		return Math.log(val);
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 *
 * @author Ivana
 */
public class Log extends Unary {
	public Log(Quantity x) {
		super(x);
	}

	@Override
	public double getValue() {
		double val = realValue(q);
		return Math.log(val);
	}
}

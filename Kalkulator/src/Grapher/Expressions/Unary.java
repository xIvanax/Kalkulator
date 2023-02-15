/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Klasa unarnih operacija, nasljeđuje Quantity.
 * @author Ivana
 */
public abstract class Unary extends Quantity {

	protected Quantity q;
	
	public Unary(Quantity q) {
		this.q = q;
	}
	
	@Override
	public abstract double getValue();
}

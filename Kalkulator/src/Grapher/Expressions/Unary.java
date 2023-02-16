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
       /**
        * Inicijalizacija operanda q
        * @param q Quantity
        * @author Ivana
        */
	public Unary(Quantity q) {
		this.q = q;
	}
	/**
         * Apstraktna funckija za dohvaćanje vrijednosti
         * @return double
         * @author Ivana
         */
	@Override
	public abstract double getValue();
}

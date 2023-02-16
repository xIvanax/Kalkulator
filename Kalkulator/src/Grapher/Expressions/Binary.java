/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Apstraktna klasa za binarne operacije, nasljeđuje Quantity.
 * @author Ivana
 */
public abstract class Binary extends Quantity {

	Quantity q1;
	Quantity q2;
	/**
         * Inicijalziacija prvog i drugog izraza u binarnoj operaciji.
         * @param q1 prvi operand
         * @param q2 drugi operand
         * @author Ivana
         */
	public Binary(Quantity q1, Quantity q2) {
		this.q1 = q1;
		this.q2 = q2;
	}
	/**
         * Apstraktna funkcija getValue
         * @return double vrijednost evaluacije
         */
	@Override
	public abstract double getValue();
}

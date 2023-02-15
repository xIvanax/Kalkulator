/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Apstraktna klasa koju nasljeđuju Unary, Binary, Variable i Number; važna je zbog svoje metode realValue.
 * @author ivana
 */
public abstract class Quantity {
	public abstract double getValue();
	/**
         * Vraća vrijednost q ako nije null, Double.NaN inače.
         * @param q Quantity
         * @return double vrijednost q
         */
	public static double realValue(Quantity q) {
            return q!=null ? q.getValue() : Double.NaN;
	}
}

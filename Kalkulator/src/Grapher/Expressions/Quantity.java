/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 *
 * @author ivana
 */
public abstract class Quantity {
	public abstract double getValue();
	
	public static double realValue(Quantity q) {
            return q!=null ? q.getValue() : Double.NaN;
	}
}

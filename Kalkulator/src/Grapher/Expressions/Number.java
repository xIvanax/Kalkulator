/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Broj, nasljeđuje Quantity.
 * @author Ivana
 */
public class Number extends Quantity {
	
	protected double num;
	/**
         * KOnstruktor koji inicijalizira num
         * @param num double
         * @author Ivana
         */
	public Number(double num){
            this.num = num;
	}
        /**
         * Vraća vrijednost num-a
         * @return num
         * @author Ivana
         */
	@Override
	public double getValue(){
            return num;
	}
}

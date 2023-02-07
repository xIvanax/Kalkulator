/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 *
 * @author Ivana
 */
public class Number extends Quantity {
	
	protected double num;
	
	public Number(double num){
            this.num = num;
	}

	@Override
	public double getValue(){
            return num;
	}
}

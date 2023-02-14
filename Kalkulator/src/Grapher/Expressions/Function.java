/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 *
 * @author ivana
 */
public class Function {
	private Quantity root;
	private Variable x;
	
	public Function(Quantity root, Variable x) {
		this.root=root;
		this.x=x;
	}
	
	public double evaluateAt(double x){
		this.x.set(x);
		return root.getValue();
	}
}

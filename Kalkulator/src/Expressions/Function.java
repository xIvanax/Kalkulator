/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expressions;

/**
 *
 * @author ivana
 */
public class Function {
	private double root;
	private Variable x = new Variable();
	private Variable y = new Variable();
	//private Variable z;
	
	public Function(double root, double x, double y/*, double z*/) {
		this.root = root;
		this.x.set(x);
		this.y.set(y);
		//this.z = z;
	}
	
	public double evaluateAt(double x, double y, double z) {
		this.x.set(x);
		this.y.set(y);
		//this.z.set(z);
		return root;
	}
}

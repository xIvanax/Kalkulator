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
	private Variable y;
	private Variable z;
	
	public Function(Quantity root, Variable x, Variable y, Variable z) {
		this.root = root;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double evaluateAt(double x, double y, double z) {
		this.x.set(x);
		this.y.set(y);
		this.z.set(z);
		return root.getValue();
	}
}

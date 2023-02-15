/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Klasa koja reprezentira funkciju (u smislu f(x)).
 * @author Ivana
 */
public class Function {
	private Quantity root;
	private Variable x;
	/**
         * Konstruktor koji inicijalizira varijable root i x.
         * @param root
         * @param x 
         */
	public Function(Quantity root, Variable x) {
		this.root=root;
		this.x=x;
	}
	/**
         * Evaluira funkciju u točki x.
         * @param x double točka evaluacije
         * @return evaluirana vrijednost
         * @author Ivana
         */
	public double evaluateAt(double x){
		this.x.set(x);
		return root.getValue();
	}
}

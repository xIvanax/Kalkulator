/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Arkus kotangens, nasljeđuje Unary.
 * @author Dorotea
 */
public class Arcctan extends Unary{
        /**
         * Konstruktor koji prima parametar q
         * @param q Quantity
         * @author Ivana
         */
    public Arcctan(Quantity q){
        super(q);
    }
    /**
     * Vraća vrijednost kotangensa
     * @return vrijednost kotangensa
     * @author Ivana
     */
    @Override
    public double getValue() {
        double val=realValue(q);
        return Math.PI/2-Math.atan(val);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Arkus tangens, nasljeđuje Unary.
 * @author Dorotea
 */
public class Atan extends Unary{
        /**
         * Konstruktor koji prima parametar q
         * @param q Quantity
         * @author Ivana
         */
    public Atan(Quantity q){
        super(q);
    }
    /**
     * Vraća vrijednost arkus tangensa.
     * @return vrijednost arkus tangensa
     * @author Ivana
     */
    @Override
    public double getValue(){
        double val=realValue(q);
        return Math.atan(val);
    }  
}

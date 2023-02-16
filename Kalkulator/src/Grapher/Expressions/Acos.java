/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Arkus kosinus, nasljeđuje Unary.
 * @author Dorotea
 */
public class Acos extends Unary{
        /**
         * Konstruktor koji prima parametar q
         * @param q Quantity
         * @author Ivana
         */
    public Acos(Quantity q){
        super(q);
    }
    /**
     * Vraća vrijednost arkus kosinusa
     * @return vrijednost arkus kosinusa
     * @author Ivana
     */
    @Override
    public double getValue() {
        double val=realValue(q);
        double v=Math.acos(val);
        if(v>0 && v<Math.PI){
            return v;
        }else{
            return Double.POSITIVE_INFINITY;
        }
    }  
}

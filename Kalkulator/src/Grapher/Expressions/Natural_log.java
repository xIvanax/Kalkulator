/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Prirodni logaritam, nasljeđuje Unary.
 * @author Dorotea
 */
public class Natural_log extends Unary{
         /**
         * KOnstruktor koji prima parametar x
         * @param q Quantity
         * @author Ivana
         */
    public Natural_log(Quantity q){
        super(q);
    }
        /**
         * Vraća vrijednost logaritma
         * @return vrijednost prirodnog logaritma
         * @author Ivana
         */
    @Override
    public double getValue() {
        double val=realValue(q);
        return Math.log(val);
    }
    
}

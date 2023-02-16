/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Potencija broja 10, nasljeđuje Unary.
 * @author Dorotea
 */
public class Power10 extends Unary{
    /**
     * Inicijalizacija operanda q
     * @param q Quantity
     * @author Ivana
     */
    public Power10(Quantity q){
        super(q);
    }
    /**
     * Vraća vrijednost potencije broja 10
     * @return vrijednost potencije broja 10
     * @author Ivana
     */
    @Override
    public double getValue() {
       double val=realValue(q);
       return Math.pow(10, val);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 *
 * @author Dorotea
 */
public class Power10 extends Unary{

    public Power10(Quantity q){
        super(q);
    }
    
    @Override
    public double getValue() {
       double val=realValue(q);
       return Math.pow(10, val);
    }
    
}

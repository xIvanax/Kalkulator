/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 *
 * @author Dorotea
 */
public class Arcctan extends Unary{
    public Arcctan(Quantity q){
        super(q);
    }
    
    @Override
    public double getValue() {
        double val=realValue(q);
        return Math.PI/2-Math.atan(val);
    }
}

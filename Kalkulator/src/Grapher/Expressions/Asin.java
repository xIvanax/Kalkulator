/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 * Arkus sinus, nasljeđuje Unary.
 * @author Dorotea
 */
public class Asin extends Unary{

    public Asin(Quantity q) {
        super(q);
    }

    @Override
    public double getValue() {
        double val=realValue(q);
        double v=Math.asin(val);
        if(v>-1 && v<1){
            return v;
        }else{
            return Double.POSITIVE_INFINITY;
        }
    }    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Expressions;

/**
 *
 * @author Dorotea
 */
public class Acos extends Unary{

    public Acos(Quantity q){
        super(q);
    }
    
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

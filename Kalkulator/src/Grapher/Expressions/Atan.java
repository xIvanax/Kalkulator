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

    public Atan(Quantity q){
        super(q);
    }
    
    @Override
    public double getValue(){
        double val=realValue(q);
        return Math.atan(val);
    }  
}

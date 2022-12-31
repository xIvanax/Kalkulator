/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kalkulator;

import KalkulatorSucelja.CalcModel;
import KalkulatorSucelja.CalcValueListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author ivana
 */
public class MyLabel extends JLabel{
    private CalcModel model;
    
    private CalcValueListener listener = new CalcValueListener(){
        @Override
        public void valueChanged(CalcModel m){
            setText(model.toString());
        }
    };
    
    private MyLabel(CalcModel m){
        model = m;
    }
    
    public static MyLabel createMyLabel(CalcModel m){
        MyLabel label = new MyLabel(m);
        label.setBackground(Color.lightGray);
        label.setText(m.toString());
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return label;
    }
}

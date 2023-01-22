/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher;

import Grapher.DrawFunctionScreen;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ivana
 */
public class Main {
	
	public static void main(String[] args) {
		DrawFunctionScreen window = new DrawFunctionScreen();
		//for finding out keycodes
               /* for(int i = 0; i < 1000000; ++i) {                                                    
            String text = java.awt.event.KeyEvent.getKeyText(i);                              
            if(!text.contains("Unknown keyCode: ")) {                                         
                System.out.println("" + i + " -- " + text);                                   
            }                                                                                 
        } */
		JFrame frame = new JFrame("Function Grapher");
                //isprobavanje mogućih izgleda kalkulatora koji bi mogli olakšati integraciju 
                /*
                JPanel unos=new JPanel();
        
        unos.setLayout(new BorderLayout());
        
        JTextField ekran = new JTextField();
        ekran.setText("");
        ekran.setSize(600, 100);
        ekran.setPreferredSize(new Dimension(800,100));
        ekran.setEnabled(true);
        ekran.setFont(ekran.getFont().deriveFont(Font.BOLD, 28f));
        unos.add(ekran, BorderLayout.NORTH);
        frame.add(unos, BorderLayout.SOUTH);*/
        
		frame.add(window);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(window).start();
	}
}

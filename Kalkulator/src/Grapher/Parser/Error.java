/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Parser;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Klasa koja igra ulogu proizvoljnog error-a.
 * @author ivana
 */
public class Error {
    JPanel parent;
	public Error(){
            parent = new JPanel();
        }
	public Error(JPanel parent) {
            this.parent=parent;
        }
	/**
         * Funkcija ispisuje proizvoljnu poruku error-a.
         * @param msg poruka koju želimo prikazati kao error
         * @author Ivana
         */
	public void makeError(String msg){
		JOptionPane.showMessageDialog(parent, msg, "Greška", JOptionPane.ERROR_MESSAGE);
	}
}

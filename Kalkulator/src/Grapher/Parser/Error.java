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
    /**
     * Prazan konstruktor
     * @author Ivana
     */
	public Error(){
            parent = new JPanel();
        }
        /**
         * Inicijalizacija prozora na koji će se "zakačiti" poruka o grešci
         * @param parent JPanel
         * @author Ivana
         */
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

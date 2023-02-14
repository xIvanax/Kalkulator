/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grapher.Parser;

/**
 * Klasa koja igra ulogu proizvoljnog error-a.
 * @author ivana
 */
public class Error {
	/**
         * Prazan konstruktor.
         * @author Ivana
         */
	public Error() {}
	/**
         * Funkcija ispisuje proizvoljnu poruku error-a.
         * @param msg poruka koju želimo prikazati kao error
         * @author Ivana
         */
	public void makeError(String msg){
		System.out.println(msg);
	}
}

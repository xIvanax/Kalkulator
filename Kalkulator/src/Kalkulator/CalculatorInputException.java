/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kalkulator;

/**
 *Iznimka koja signalizira da je korisnik probao napraviti nedozvoljeni unos u kalkulator (npr. broj već ima jednu decimalnu točku, a korisnik pokušao dodati još jednu)
 * @author ivana
 */
public class CalculatorInputException extends RuntimeException {
    /**
     * prazni konstruktor
     */
    public CalculatorInputException(){}
    /**
     * konstruktor koji vrća odgovorajuću poruku ovisno o tipu greške
     * @param poruka 
     */
    public CalculatorInputException(String poruka){
        super(poruka);
    }
}

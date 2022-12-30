/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package KalkulatorSucelja;

import Kalkulator.CalculatorInputException;

/**
 *
 * @author ivana
 */
public interface CalcModel {
    /**
     * 
     * @return vrijednost pohranjena u kalkulatoru
     */
    public double getValue();
    /**
     * upisivanje broja u kalkulator
     * @param value broj koji treba upisati
     */
    public void setValue(double value);
    /**
     * soft delete, tj. čistimo samo unos
     */
    public void clear();
    /**
     * hard delete, tj. čistimo sve varijable i uklanjamo aktivni operand
     */
    public void clearAll();
    /**
     * negativan broj postaje pozitivan i obrnuto
     */
    public void swapSign();
    /**
     * umetanje decimalne točke i provjera ima li broj već decimalnu točku
     * @throws CalculatorInputException ako broj već ima decimalnu točku
     */
    public void insertDecimalPoint();
    /**
     * upisivanje nove znamenke i prilagodba formata s obzirom na to je li prvi uneseni broj 0 ili ne
     * @param digit znamenka koju treba dodati
     * @throws CalculatorInputException ako bi novonastali broj postao prevelik
     */
    public void insertDigit(int digit);
    /**
     * postavi activeOperand na operand
     * @param operand novi aktivni operand
     */
    public void setActiveOperand(double operand);
    /**
     * dohvaćanje aktivnog operanda (ukoliko je validan)
     * @return aktivni operand
     */
    public double getActiveOperand();
    /**
     * uklanjanje aktivnog operanda
     */
    public void clearActiveOperand();
    /**
     * Vraca tekst koji treba pokazati na zaslonu
     * @return tekst za prikaz na zaslonu kalkulatora
     */
    String toString();
}
